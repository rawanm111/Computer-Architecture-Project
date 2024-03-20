package instructions;

public class IInstruction extends instruction{
       private int immediate; 
	public int getImmediate() {
		return immediate;
	}
	public void setImmediate(int immediate) {
		this.immediate = immediate;
	}
	public IInstruction(short binary, int opcode, int FirstRegister,int immediate) {
		super(binary, opcode, FirstRegister);
		this.immediate=immediate;
		super.Type= "I";
	}

}
