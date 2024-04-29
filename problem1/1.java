// Write a program to generate Symbol & Literal table of a two-pass Assembler for the given Assembly language source code.
//             START 300
// READ M
//                   READ N
//            MOVER AREG, =’51'
//                 MOVER BREG, =’61’
//             ADD AREG, BREG
// LOOP        MOVER CREG, M  
//                 ADD  CREG, ='11'
// COMP CREG, N
//                 BC LT, LOOP
// NEXT        SUB AREG, ='11'
// COMP AREG, N 
// BC  GT, NEXT
// STOP
// M                DS        1
// N                DS        1
// END

import java.util.*;

class Assembler {
    private Map<String, Integer> symbolTable;
    private Map<String, Integer> literalTable;
    private int locationCounter;
    private List<String> code;

    public Assembler() {
        symbolTable = new HashMap<>();
        literalTable = new HashMap<>();
        locationCounter = 0;
        code = new ArrayList<>();
    }

    public void firstPass(List<String> sourceCode) {
        for (String line : sourceCode) {
            line = line.trim();
            if (line.startsWith("START")) {
                locationCounter = Integer.parseInt(line.split("\\s+")[1]);
            } else if (line.startsWith("END")) {
                processLTORGorEND();
                break;
            } else if (line.startsWith("LTORG")) {
                processLTORGorEND();
            } else if (!line.isEmpty()) {
                if (line.startsWith("=") || line.contains("='")) {
                    processLiteral(line);
                } else {
                    processLabel(line);
                }
                processInstruction(line);
            }
        }
    }

    public void processInstruction(String line) {
        code.add(line);
        locationCounter++;
    }

    public void processLabel(String line) {
        String[] parts = line.split("\\s+");
        String label = parts[0];
        symbolTable.put(label, locationCounter);
    }

    public void processLiteral(String line) {
        String literal;
        if (line.contains("='")) {
            literal = line.split("='")[1].split("'")[0];
        } else {
            literal = line.split(",")[1].trim();
        }
        literalTable.put(literal, null);
    }

    public void processLTORGorEND() {
        for (String literal : literalTable.keySet()) {
            if (literalTable.get(literal) == null) {
                literalTable.put(literal, locationCounter);
                locationCounter++;
            }
        }
    }

    public List<String> secondPass() {
        List<String> assembledCode = new ArrayList<>();
        for (String line : code) {
            if (line.startsWith("DS")) {
                assembledCode.add(assembleDS(line));
            } else {
                assembledCode.add(assembleInstruction(line));
            }
        }
        return assembledCode;
    }

    public String assembleInstruction(String line) {
        String[] parts = line.split(",");
        String mnemonic = parts[0].trim();
        StringBuilder assembledLine = new StringBuilder(mnemonic);
        for (int i = 1; i < parts.length; i++) {
            String operand = parts[i].trim();
            if (operand.startsWith("=")) {
                assembledLine.append(" ").append(literalTable.get(operand.substring(1)));
            } else if (symbolTable.containsKey(operand)) {
                assembledLine.append(" ").append(symbolTable.get(operand));
            } else {
                assembledLine.append(" ").append(operand);
            }
        }
        return assembledLine.toString();
    }

    public String assembleDS(String line) {
        String[] parts = line.split("\\s+");
        String label = parts[0];
        String ds = parts[1];
        String size = parts[2];
        return String.format("%s %s %s, %d", label, ds, size, locationCounter);
    }

    public void generateTables() {
        System.out.println("Symbol Table:");
        System.out.println("Label\tAddress");
        for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

        System.out.println("\nLiteral Table:");
        System.out.println("Literal\tAddress");
        for (Map.Entry<String, Integer> entry : literalTable.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    public void assemble(List<String> sourceCode) {
        firstPass(sourceCode);
        generateTables();
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> sourceCode = Arrays.asList(
                "START 300",
                "READ M",
                "READ N",
                "MOVER AREG, ='51'",
                "MOVER BREG, ='61'",
                "ADD AREG, BREG",
                "LOOP MOVER CREG, M",
                "ADD CREG, ='11'",
                "COMP CREG, N",
                "BC LT, LOOP",
                "NEXT SUB AREG, ='11'",
                "COMP AREG, N",
                "BC GT, NEXT",
                "STOP",
                "M DS 1",
                "N DS 1",
                "END");

        Assembler assembler = new Assembler();
        assembler.assemble(sourceCode);
    }
}
