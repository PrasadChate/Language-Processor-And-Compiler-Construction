
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
    private List<String[]> literalTable;
    private List<Integer> poolTable;
    private int locationCounter;
    private int poolStart;

    public Assembler() {
        symbolTable = new HashMap<>();
        literalTable = new ArrayList<>();
        poolTable = new ArrayList<>();
        locationCounter = 0;
        poolStart = 0;
    }

    public void firstPass(List<String> sourceCode) {
        for (String line : sourceCode) {
            line = line.trim();
            if (line.startsWith("START")) {
                locationCounter = Integer.parseInt(line.split("\\s+")[1]);
            } else if (line.startsWith("END")) {
                processLTORG();
                break;
            } else if (!line.isEmpty()) {
                if (line.contains("='")) {
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
        if (!line.startsWith("DS") && !line.startsWith("LTORG")) {
            locationCounter++;
        }
    }

    public void processLabel(String line) {
        String[] parts = line.split("\\s+");
        if (parts.length > 2 && parts[1].equals("DS")) {
            symbolTable.put(parts[0], locationCounter);
        } else if (parts.length > 1 && !parts[0].equals("MOVER") && !parts[0].equals("MOVEM") && !parts[0].equals("ADD") && !parts[0].equals("SUB") && !parts[0].equals("COMP") && !parts[0].equals("BC") && !parts[0].equals("READ") && !parts[0].equals("PRINT") && !parts[0].equals("STOP")) {
            symbolTable.put(parts[0], locationCounter);
        }
    }

    public void processLiteral(String line) {
        String literal = line.split("='")[1].split("'")[0];
        literalTable.add(new String[]{literal, ""});
    }

    public void processLTORG() {
        for (int i = poolStart; i < literalTable.size(); i++) {
            String[] literal = literalTable.get(i);
            literal[1] = String.valueOf(locationCounter++);
        }
        poolTable.add(poolStart);
        poolStart = literalTable.size();
    }

    public void generateTables() {
        System.out.println("Symbol Table:");
        System.out.println("Label\tAddress");
        for (Map.Entry<String, Integer> entry : symbolTable.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }

        System.out.println("\nLiteral Table:");
        System.out.println("Literal\tAddress");
        for (String[] literal : literalTable) {
            System.out.println(literal[0] + "\t" + literal[1]);
        }

        System.out.println("\nPool Table:");
        for (int address : poolTable) {
            System.out.println(address);
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
