package instructions;

public class RInstruction extends instruction {
      private int secondRegister ;
	public RInstruction(short binary, int opcode, int FirstRegister,int secondRegister) {
		super(binary, opcode, FirstRegister);
		this.secondRegister=secondRegister;
		super.Type="R";
	}

	public int getSecondRegister() {
		return secondRegister;
	}
	public void setSecondRegister(int secondRegister) {
		this.secondRegister = secondRegister;
	}
}
