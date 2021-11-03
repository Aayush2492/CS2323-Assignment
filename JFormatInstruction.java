import java.util.HashMap;

public class JFormatInstruction extends Instruction {
    JFormatInstruction(String _instructionName, String _opcode){
        super(_instructionName, _opcode);
    }

    public String getMachineCode(String line, HashMap<String, String> registerNameToBinaryMap)
    {
        String target = line.substring(6, 32);
        target = Integer.toBinaryString(Integer.parseInt(target));
        if(target.length() < 26)
        {
            target = ("00000000000000000000000000").substring(target.length())+target;
        }

        String corresspondingMachineCode = this.opcode + target;
        return(corresspondingMachineCode);
    }
}