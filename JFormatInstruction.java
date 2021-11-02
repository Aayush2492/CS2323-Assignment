import java.util.HashMap;

public class JFormatInstruction extends Instruction {
    JFormatInstruction(String _instructionName, String _opcode){
        super(_instructionName, _opcode);
    }

    public void getMachineCode(String line, HashMap<String, String> registerNameToBinaryMap)
    {
        
    }
}