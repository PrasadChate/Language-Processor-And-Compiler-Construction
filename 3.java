
// Write a program to generate Symbol , Literal table &Pool table of a two-pass Assembler for the given Assembly language source code.
// INPUT/CODE
// START 100
// READ A
// MOVER AREG, ='1'
// MOVEM AREG, B
// MOVER BREG, ='6'
// ADD AREG, BREG
// COMP AREG, A
// BC GT, LAST
// LTORG
// NEXT SUB AREG, ='1'
// MOVER CREG, B
// ADD CREG, ='8'
// MOVEM CREG, B
// PRINT B
// LAST STOP
// A DS 1
// B DS 1
// END
import java.util.*;

class Assembler {
    private Map<String, Integer> symbolTable;
    private Map<String, Integer> literalTable;
    private List<Integer> poolTable;
    private int locationCounter;
    private List<String> code;

    public Assembler() {
        symbolTable = new HashMap<>();
        literalTable = new HashMap<>();
        poolTable = new ArrayList<>();
        locationCounter = 0;
        code = new ArrayList<>();
    }

    public void firstPass(List<String> sourceCode) {
        for (String line : sourceCode) {
            line = line.trim();
            if (line.startsWith("START")) {
                locationCounter = Integer.parseInt(line.split("\\s+")[1]);
            } else if (line.startsWith("END")) {
                break;
            } else if (!line.isEmpty()) {
                if (line.startsWith("=") || line.startsWith("'")) {
                    processLiteral(line);
                } else if (line.startsWith("LTORG")) {
                    processLTORG();
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
        String literal = line.split(",")[1].trim();
        if (!literalTable.containsKey(literal)) {
            literalTable.put(literal, literalTable.size() + 1);
            poolTable.add(locationCounter);
        }
    }

    public void processLTORG() {
        for (Map.Entry<String, Integer> entry : literalTable.entrySet()) {
            if (poolTable.contains(entry.getValue())) {
                poolTable.remove(entry.getValue());
                literalTable.put(entry.getKey(), locationCounter);
                locationCounter++;
            }
        }
    }

    public List<String> secondPass() {
        List<String> assembledCode = new ArrayList<>();
        for (String line : code) {
            if (!line.startsWith("LTORG")) {
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

        System.out.println("\nPool Table:");
        System.out.println("Pool Address");
        for (Integer address : poolTable) {
            System.out.println(address);
        }
    }

    public void assemble(List<String> sourceCode) {
        firstPass(sourceCode);
        List<String> assembledCode = secondPass();
        generateTables();
        System.out.println("\nAssembled Code:");
        for (String line : assembledCode) {
            System.out.println(line);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> sourceCode = Arrays.asList(
                "START 100",
                "READ A",
                "MOVER AREG, ='1'",
                "MOVEM AREG, B",
                "MOVER BREG, ='6'",
                "ADD AREG, BREG",
                "COMP AREG, A",
                "BC GT, LAST",
                "LTORG",
                "NEXT SUB AREG, ='1'",
                "MOVER CREG, B",
                "ADD CREG, ='8'",
                "MOVEM CREG, B",
                "PRINT B",
                "LAST STOP",
                "A DS 1",
                "B DS 1",
                "END");

        Assembler assembler = new Assembler();
        assembler.assemble(sourceCode);
    }
}
