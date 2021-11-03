import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Assembler 
{
    public static void main(String[] args) 
    {
        String number = args[0];
        System.out.println(args[0]);
        String inputAssemblyFilePath = "./sample_data/test_assembler/asm_files/prog"+number+".asm";
        // String outputBinaryFilePath = "./sample_data/test_assembler/asm_to_bin/prog"+number+"bin.txt";
        String outputBinaryFilePath = "./sample_data/test_assembler/asm_to_bin_hex_files/prog"+number+"bin_hex.txt";

        HashMap<String, Instruction> instructionNameToBinaryMap = new HashMap<>();
        Scanner sc;
        try
        {
            sc = new Scanner(new File("./data/r_format_instructions.txt"));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String[] words = line.split(" ");
                Instruction instr = new RFormatInstruction(words[0], words[1], words[2]);
                instructionNameToBinaryMap.put(words[0], instr);
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        try
        {
            sc = new Scanner(new File("./data/i_format_instructions.txt"));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String[] words = line.split(" ");
                Instruction instr = new IFormatInstruction(words[0], words[1]);
                instructionNameToBinaryMap.put(words[0], instr);
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        try
        {
            sc = new Scanner(new File("./data/j_format_instructions.txt"));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String[] words = line.split(" ");
                Instruction instr = new JFormatInstruction(words[0], words[1]);
                instructionNameToBinaryMap.put(words[0], instr);
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        HashMap<String, String> registerNameToBinaryMap = new HashMap<>();
        try
        {
            sc = new Scanner(new File("./data/registers.txt"));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String[] words = line.split(" ");
                registerNameToBinaryMap.put("$"+words[0], words[1]);
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        try
        {
            sc = new Scanner(new File(inputAssemblyFilePath));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String[] words = line.split(" ");

                String instructionName = words[0];
                Instruction instr = instructionNameToBinaryMap.get(instructionName);
                // System.out.println(instr.getOpcode());
                // System.out.println(Arrays.toString(registersOrConstants));
                String correspondingMachineCode = instr.getMachineCode(line, registerNameToBinaryMap);
                writeToFile(outputBinaryFilePath, correspondingMachineCode);
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String fileName, String textToBeAppended)
    {
        try 
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));

            long decimal = Long.parseLong(textToBeAppended, 2);
            String hexaform = Long.toString(decimal, 16);
            
            out.write(textToBeAppended +": "+ hexaform + "\n");
            out.close();
        }
        catch (IOException e) 
        {
            System.out.println("exception occured" + e);
        }
    }
}