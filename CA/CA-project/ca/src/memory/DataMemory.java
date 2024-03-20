package memory;

import instructions.instruction;

public class DataMemory {
	private Byte[] dataMemory;

	private static final DataMemory instance = new DataMemory();

	private DataMemory() {
		dataMemory = new Byte[2048];
		for(int i=0 ;i<2048;i++) {
			dataMemory[i]=0;
		}
	}

	public int readAddress(int address) {
		if(dataMemory[address] == null)
			return 0;
		else
			return dataMemory[address];
		
	}

	public void reset() {
		dataMemory = new Byte[2048];
	}

	public void writeAddress(int address, byte data) {
		dataMemory[address]=data;

	}

	public Byte[] getDataMemory() {
		return dataMemory;
	}


	public static DataMemory getInstance() {
		return instance;
	}


}
