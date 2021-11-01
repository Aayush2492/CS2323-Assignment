public class RFormatInstruction extends Instruction {
    int funct;
    RFormatInstruction(String _instructionName, int _opcode, int _funct){
        super(_instructionName, _opcode);
        this.funct = _funct;
    }   
}
