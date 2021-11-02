import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Deassembler 
{
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
                instructionNameToBinaryMap.put(words[2], instr);
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
                instructionNameToBinaryMap.put(words[1], instr);
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

        String filePath = "ip.txt";
        try
        {
            sc = new Scanner(new File(filePath));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String firstFiveBits = line.substring(0,6);
                if(firstFiveBits.equals("000000"))
                {
                    // R format instruction
                    String funct = line.substring(line.length()-6, line.length());
                    Instruction correspondingInstruction = instructionNameToBinaryMap.get(funct);

                    String instructionName = correspondingInstruction.getName();

                    if(instructionName.equals("sll") || instructionName.equals("srl"))
                    {

                    }
                    else
                    {
                        String firstSourceRegister = line.substring(6, 11);
                        String secondSourceRegister = line.substring(11, 16);
                        String destinationRegister = line.substring(16, 21);
                    }

                }
                else
                {
                    Instruction correspondingInstruction = instructionNameToBinaryMap.get(firstFiveBits);

                    String instructionName = correspondingInstruction.getName();
                    if(instructionName.equals("addi") || instructionName.equals("andi") || instructionName.equals("ori") || instructionName.equals("beq") ||
                        instructionName.equals("bne") || instructionName.equals("slti") || instructionName.equals("sltiu"))
                    {

                    }
                    else
                    {
                        
                    }
                }
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

    }

    
}
