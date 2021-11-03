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

    String getMachineCode(String line, HashMap<String, String> registerNameToBinaryMap)
    {
        String[] words = line.split(" ");
        String[] registersOrConstants = words[1].split(",");
        for (int i = 0; i < registersOrConstants.length; i++)
            registersOrConstants[i] = registersOrConstants[i].trim();

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
            
            String correspondingMachineCode = this.opcode + "00000" + secondSourceRegister + destinationRegister + shamt + this.funct;
            return(correspondingMachineCode);
        }
        else
        {
            String destinationRegister = registerNameToBinaryMap.get(registersOrConstants[0]);
            String firstSourceRegister = registerNameToBinaryMap.get(registersOrConstants[1]);
            String secondSourceRegister = registerNameToBinaryMap.get(registersOrConstants[2]);

            String correspondingMachineCode = this.opcode + firstSourceRegister + secondSourceRegister + destinationRegister + "00000" + this.funct;
            return(correspondingMachineCode);
        }
    }

}
