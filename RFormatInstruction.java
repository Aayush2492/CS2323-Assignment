import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class RFormatInstruction extends Instruction {
    String funct;
    RFormatInstruction(String _instructionName, String _opcode, String _funct){
        super(_instructionName, _opcode);
        this.funct = _funct;
    }

    public String getFunct()
    {
        return(this.funct);
    }

    void getMachineCode(String[] registersOrConstants, HashMap<String, String> registerNameToBinaryMap)
    {
        //Shift amount will be zero except for sll and srl
        if(this.instructionName.equals("sll") || this.instructionName.equals("srl"))
        {
            //First two words in array will be registers and third a constant
            String destinationRegister = registerNameToBinaryMap.get(registersOrConstants[0]);
            String secondSourceRegister = registerNameToBinaryMap.get(registersOrConstants[1]);

            String shamt = Integer.toBinaryString(Integer.parseInt(registersOrConstants[2]));
            if(shamt.length() < 5)
            {
                shamt = ("00000").substring(shamt.length())+shamt;
            }
            
            String fileName = "op.txt";
            String correspondingMachineCode = this.opcode + "00000" + secondSourceRegister + destinationRegister + shamt + this.funct;
            writeToFile(fileName, correspondingMachineCode);
        }
        else
        {
            String destinationRegister = registerNameToBinaryMap.get(registersOrConstants[0]);
            String firstSourceRegister = registerNameToBinaryMap.get(registersOrConstants[1]);
            String secondSourceRegister = registerNameToBinaryMap.get(registersOrConstants[2]);

            String fileName = "op.txt";
            String correspondingMachineCode = this.opcode + firstSourceRegister + secondSourceRegister + destinationRegister + "00000" + this.funct;
            writeToFile(fileName, correspondingMachineCode);
        }
    }

    private void writeToFile(String fileName, String textToBeAppended)
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
