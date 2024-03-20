package memory;


import instructions.instruction;


public class InstructionMemory {
	private instruction[] instructionMemory;
	private int lastInstruction;

	private static final InstructionMemory instance = new InstructionMemory();

	private InstructionMemory() {
		this.instructionMemory = new instruction[1024];
		this.lastInstruction = -1;
	}



	public instruction[] getInstructionMemory() {
		return instructionMemory;
	}


	public void addInstruction(instruction instruction) {
		instructionMemory[++lastInstruction] = instruction;
		instruction.setAddress(lastInstruction);
	}

	public static InstructionMemory getInstance() {
		return instance;
	}

	public int getLastInstruction() {
		return lastInstruction;
	}

//	public void print() {
//		CPU.getInstance().println("INSTRUCTIONS MEMORY");
//		for (int i = 0; i < instructionMemory.length; i++) {
//			if (instructionMemory[i] == null)
//				continue;
//			CPU.getInstance()
//					.println("Instruction " + i + ": binaryContent = "
//							+ Helper.StringExtend(instructionMemory[i].getBinaryCode(), 16) + " content = "
//							+ instructionMemory[i]);
//		}
//	}

	public void reset() {
		this.instructionMemory = new instruction[1024];
		this.lastInstruction = -1;
	}
}
