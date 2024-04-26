
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
    private List<String> intermediateCode;
    private List<String> mnt;
    private List<String> mdt;

    public MacroProcessor(List<String> inputCode) {
        this.inputCode = inputCode;
        intermediateCode = new ArrayList<>();
        mnt = new ArrayList<>();
        mdt = new ArrayList<>();
    }

    public void firstPass() {
        for (int i = 0; i < inputCode.size(); i++) {
            String line = inputCode.get(i);
            if (line.startsWith("MACRO")) {
                String macroName = line.split("\\s+")[1];
                mnt.add(macroName + "\t" + mdt.size());
                StringBuilder mdtEntry = new StringBuilder(macroName + "\t");
                i++; // skip the macro definition line
                while (!inputCode.get(i).equals("MEND")) {
                    mdtEntry.append(inputCode.get(i)).append("\n");
                    i++;
                }
                mdt.add(mdtEntry.toString());
            }
        }
    }

    public void secondPass() {
        for (String line : inputCode) {
            if (line.startsWith("MACRO")) {
                String macroName = line.split("\\s+")[1];
                int index = mnt.indexOf(macroName + "\t0");
                if (index != -1) {
                    String macroArgs = line.substring(line.indexOf(macroName) + macroName.length()).trim();
                    String mdtEntry = mdt.get(index);
                    if (!macroArgs.isEmpty()) {
                        String[] args = macroArgs.split(",");
                        for (int i = 0; i < args.length; i++) {
                            mdtEntry = mdtEntry.replaceAll("\\b" + args[i].trim() + "\\b", "ARG" + (i + 1));
                        }
                    }
                    intermediateCode.add(mdtEntry);
                } else {
                    intermediateCode.add(line); // Macro not found, add original line
                }
            } else {
                intermediateCode.add(line);
            }
        }
    }

    public void printIntermediateCode() {
        System.out.println("Intermediate Code:");
        for (String line : intermediateCode) {
            System.out.println(line);
        }
    }

    public void printMNT() {
        System.out.println("\nMacro Name Table (MNT):");
        for (String entry : mnt) {
            System.out.println(entry);
        }
    }

    public void printMDT() {
        System.out.println("\nMacro Definition Table (MDT):");
        for (String entry : mdt) {
            System.out.println(entry);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<String> inputCode = Arrays.asList(
                "LOAD J",
                "STORE M",
                "MACRO EST",
                "LOAD e",
                "ADD d",
                "MEND",
                "LOAD S",
                "MACRO SUB4 ABC",
                "LOAD U",
                "STORE ABC",
                "MEND",
                "LOAD P",
                "ADD V",
                "MACRO ADD7 P4, P5, P6",
                "LOAD P5",
                "SUB4 XYZ",
                "SUB 8",
                "SUB 2",
                "STORE P4",
                "STORE P6",
                "MEND",
                "EST",
                "ADD7 C4, C5, C6",
                "SUB4 z",
                "END");

        MacroProcessor processor = new MacroProcessor(inputCode);
        processor.firstPass();
        processor.secondPass();

        processor.printMNT();
        processor.printMDT();
        processor.printIntermediateCode();
    }
}
