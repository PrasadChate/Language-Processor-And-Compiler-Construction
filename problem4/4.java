
// Write a program to generateMNT, MDT & Intermediate code of a two-pass Macro processor
// INPUT/CODE
// LOAD J
// STORE M
// MACRO EST
// LOAD e
// ADD d
// MEND
// LOAD S
// MACRO SUB4 ABC
// LOAD U
// STORE ABC
// MEND
// LOAD P
// ADD V
// MACRO ADD7 P4, P5, P6
// LOAD P5
// SUB4 XYZ
// SUB 8
// SUB 2
// STORE P4
// STORE P6
// MEND
// EST
// ADD7 C4, C5, C6
// SUB4 z
// END
import java.util.*;

class MacroProcessor {
    private List<String> inputCode;
    private Map<String, List<String>> MDT;
    private Map<String, Integer> MNT;
    private List<String> intermediateCode;

    public MacroProcessor(List<String> inputCode) {
        this.inputCode = inputCode;
        MDT = new LinkedHashMap<>();
        MNT = new LinkedHashMap<>();
        intermediateCode = new ArrayList<>();
    }

    public void process() {
        buildMDTandMNT();

        for (String line : inputCode) {
            if (line.startsWith("MACRO")) {
                continue; // Skip MACRO definitions
            }

            boolean isMacroCall = false;
            for (String macro : MNT.keySet()) {
                if (line.contains(macro)) {
                    isMacroCall = true;
                    String macroName = macro;
                    String[] parts = line.split("\\s+", 2); // Split at most once
                    String[] actualParams = parts.length > 1 ? parts[1].split(",") : new String[0];
                    List<String> macroDefinition = MDT.get(macro);
                    for (String macroLine : macroDefinition) {
                        String replacedLine = replaceParams(macroLine, macroName, actualParams);
                        intermediateCode.add(replacedLine);
                    }
                }
            }
            if (!isMacroCall)
                intermediateCode.add(line);
        }

        // Output MNT
        System.out.println("Macro Name Table (MNT):");
        System.out.println(String.format("| %-10s | %-10s |", "Macro Name", "Index"));
        for (Map.Entry<String, Integer> entry : MNT.entrySet()) {
            System.out.println(String.format("| %-10s | %-10d |", entry.getKey(), entry.getValue()));
        }
        System.out.println();

        // Output MDT
        System.out.println("Macro Definition Table (MDT):");
        System.out.println(String.format("| %-10s | %-20s |", "Macro Name", "Definition"));
        for (Map.Entry<String, List<String>> entry : MDT.entrySet()) {
            System.out.println(String.format("| %-10s | %-20s |", entry.getKey(), String.join("\n", entry.getValue())));
            System.out.println("|           |                     |");
        }
        System.out.println();

        // Output intermediate code
        System.out.println("Intermediate Code:");
        int locationCounter = 200; // Start location
        for (String line : intermediateCode) {
            if (line.startsWith("LOAD") || line.startsWith("STORE")) {
                System.out.println("(IS,01) " + line);
                System.out.println("(AD,01) (C," + locationCounter + ")");
                locationCounter++;
            } else if (line.startsWith("ADD")) {
                String[] parts = line.split("\\s+");
                System.out.println("(IS,02) " + parts[1]);
                if (parts.length > 2) {
                    System.out.println("(L," + parts[2] + ")");
                }
                if (parts.length > 3) {
                    System.out.println("(C," + parts[3] + ")");
                }
            } else if (line.startsWith("SUB")) {
                String[] parts = line.split("\\s+");
                System.out.println("(IS,03) " + parts[1]);
                if (parts.length > 2) {
                    System.out.println("(L," + parts[2] + ")");
                }
                if (parts.length > 3) {
                    System.out.println("(C," + parts[3] + ")");
                }
            }
        }
        System.out.println("(AD,02)");
    }

    private void buildMDTandMNT() {
        int index = 0;
        while (index < inputCode.size()) {
            String line = inputCode.get(index);
            if (line.startsWith("MACRO")) {
                String macroName = line.split("\\s+")[1];
                List<String> macroDefinition = new ArrayList<>();
                index++;
                while (!inputCode.get(index).equals("MEND")) {
                    macroDefinition.add(inputCode.get(index));
                    index++;
                }
                MNT.put(macroName, MDT.size());
                MDT.put(macroName, macroDefinition);
            }
            index++;
        }
    }

    private String replaceParams(String macroLine, String macroName, String[] actualParams) {
        StringBuilder replacedLine = new StringBuilder();
        for (int i = 0; i < actualParams.length; i++) {
            replacedLine.append(" " + macroLine.replace(macroName + (i + 1), actualParams[i]));
        }
        return replacedLine.toString().trim();
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> inputCode = new ArrayList<>();
        inputCode.add("LOAD J");
        inputCode.add("STORE M");
        inputCode.add("MACRO EST");
        inputCode.add("LOAD e");
        inputCode.add("ADD d");
        inputCode.add("MEND");
        inputCode.add("LOAD S");
        inputCode.add("MACRO SUB4 ABC");
        inputCode.add("LOAD U");
        inputCode.add("STORE ABC");
        inputCode.add("MEND");
        inputCode.add("LOAD P");
        inputCode.add("ADD V");
        inputCode.add("MACRO ADD7 P4, P5, P6");
        inputCode.add("LOAD P5");
        inputCode.add("SUB4 XYZ");
        inputCode.add("SUB 8");
        inputCode.add("SUB 2");
        inputCode.add("STORE P4");
        inputCode.add("STORE P6");
        inputCode.add("MEND");
        inputCode.add("EST");
        inputCode.add("ADD7 C4, C5, C6");
        inputCode.add("SUB4 z");
        inputCode.add("END");

        MacroProcessor processor = new MacroProcessor(inputCode);
        processor.process();
    }
}
