import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Assembler {   
    public static void main(String[] args) 
    {
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
            sc = new Scanner(new File("./sample_data/samp2.asm"));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String[] words = line.split(" ");

                String instructionName = words[0];
                Instruction instr = instructionNameToBinaryMap.get(instructionName);
                // System.out.println(instr.getOpcode());
                // System.out.println(Arrays.toString(registersOrConstants));
                instr.getMachineCode(line, registerNameToBinaryMap);
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
}