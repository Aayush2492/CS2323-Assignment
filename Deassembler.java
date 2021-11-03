import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                registerNameToBinaryMap.put(words[1], "$"+words[0]);
            }      
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        String filePath = "bin.txt";
        try
        {
            sc = new Scanner(new File(filePath));

            while(sc.hasNextLine()) 
            {
                String line = sc.nextLine();
                String firstFiveBits = line.substring(0,6);
                String correspondingAssemblyCode = "";
                if(firstFiveBits.equals("000000"))
                {
                    // R format instruction
                    String funct = line.substring(line.length()-6, line.length());
                    Instruction correspondingInstruction = instructionNameToBinaryMap.get(funct);

                    String instructionName = correspondingInstruction.getName();

                    if(instructionName.equals("sll") || instructionName.equals("srl"))
                    {
                        String secondSourceRegister = registerNameToBinaryMap.get(line.substring(11, 16));
                        String destinationRegister = registerNameToBinaryMap.get(line.substring(16, 21));     
                        int shamt = Integer.parseInt(line.substring(21, 26), 2);

                        correspondingAssemblyCode = instructionName + " " + destinationRegister + "," + secondSourceRegister + "," + Integer.toString(shamt) + "\n";
                    }
                    else
                    {
                        String secondSourceRegister = registerNameToBinaryMap.get(line.substring(11, 16));
                        String destinationRegister = registerNameToBinaryMap.get(line.substring(16, 21));    
                        String firstSourceRegister = registerNameToBinaryMap.get(line.substring(6, 11));    

                        correspondingAssemblyCode = instructionName + " " + destinationRegister + "," + firstSourceRegister + "," + secondSourceRegister + "\n";
                    }

                }
                else
                {
                    Instruction correspondingInstruction = instructionNameToBinaryMap.get(firstFiveBits);

                    String instructionName = correspondingInstruction.getName();
                    if(instructionName.equals("addi") || instructionName.equals("andi") || instructionName.equals("ori") || instructionName.equals("beq") ||
                        instructionName.equals("bne") || instructionName.equals("slti") || instructionName.equals("sltiu"))
                    {
                        String secondSourceRegister = registerNameToBinaryMap.get(line.substring(11, 16));
                        int immediateValue = Integer.parseInt(line.substring(16, 32), 2);    
                        String firstSourceRegister = registerNameToBinaryMap.get(line.substring(6, 11)); 

                        correspondingAssemblyCode = instructionName + " " + secondSourceRegister + "," + firstSourceRegister + "," + Integer.toString(immediateValue)+ "\n";
                    }
                    else
                    {
                        String secondSourceRegister = registerNameToBinaryMap.get(line.substring(11, 16));
                        int immediateValue = Integer.parseInt(line.substring(16, 32), 2);    
                        String firstSourceRegister = registerNameToBinaryMap.get(line.substring(6, 11));

                        correspondingAssemblyCode = instructionName + " " + secondSourceRegister + "," + Integer.toString(immediateValue) + "(" + firstSourceRegister + ")" + "\n";
                    }
                }

                writeToFile("op_asm.txt", correspondingAssemblyCode);
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
            BufferedWriter out = new BufferedWriter(
                new FileWriter(fileName, true));

            out.write(textToBeAppended + "\n");
            out.close();
        }
        catch (IOException e) 
        {
            System.out.println("exception occured" + e);
        }
    }
    
}
