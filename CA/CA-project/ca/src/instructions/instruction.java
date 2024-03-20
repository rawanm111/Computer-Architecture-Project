package instructions;

public class instruction {
    private short binary ;
    private String instruction;
	private int opCode;
	private int FirstRegister;
//	private int FirstRegisterContent;
	private int address; 
	protected String Type;
	
	public instruction(short binary,int opcode ,int FirstRegister) {
		this.binary=binary;
		this.opCode=opcode;
		this.FirstRegister=FirstRegister;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public int getAddress() {
		return address;
	}
	public short getBinary() {
		return binary;
	}
	public void setBinary(short binary) {
		this.binary = binary;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public int getOpCode() {
		return opCode;
	}
	public void setOpCode(int opCode) {
		this.opCode = opCode;
	}
	public int getFirstRegister() {
		return FirstRegister;
	}
	public void setFirstRegister(int firstRegister) {
		FirstRegister = firstRegister;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

}
