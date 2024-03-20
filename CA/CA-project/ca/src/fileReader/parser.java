package fileReader;

import java.io.BufferedReader;


import java.io.FileReader;
import instructions.*;
import memory.InstructionMemory;

public class parser {
	public static  void parseProgram(String fileName) throws Exception {
		BufferedReader bufferedreader = new BufferedReader(new FileReader(fileName));
		while (bufferedreader.ready()) {
			String inputLine = bufferedreader.readLine();
			String[] line = inputLine.split(" ");
			short binary ;
			switch (line[0]) {
			case "ADD":
				binary =0;
				break;
			case "SUB":
				binary =1;
				break;
			case "MUL":
				binary =2;
				break;
			case "LDI":
				binary =3;
				break;
			case "BEQZ":
				binary =4;
				break;
			case "AND":
				binary =5;
				break;
			case "OR":
				binary =6;
				break;
			case "JR":
				binary =7;
				break;
			case "SLC":
				binary =8;
				break;
			case "SRC":
				binary =9;
				break;
			case "LB":
				binary =10;
				break;
			default:
				binary =11;
				break;
			}
			
			binary=(short) (binary&0b1111);
			
			int x = binary;
			binary = (short) (binary<<12);
			int r1 = Integer.parseInt(line[1].substring(1));
			int firstregister=r1;
			r1 <<= 6;			
			binary = (short) (binary|r1);
			instruction instruction;
			if (line[2].charAt(0) == 'R') {
				int r2 = Integer.parseInt(line[2].substring(1));
				r2 &= (1 << 6) - 1;
				binary = (short) (binary|r2);
				 instruction = new RInstruction(binary,x ,firstregister,r2);
			} else {
				
				int immediate = Integer.parseInt(line[2]);
				
				immediate=immediate&0b111111;
				binary = (short) (binary|immediate);
//				System.out.println(printBinary(binary));
				 instruction = new IInstruction((short)binary,x ,firstregister,immediate);			
			
			}       
			instruction.setInstruction(inputLine);
			InstructionMemory.getInstance().addInstruction(instruction);
		}
		
	}
	public static int getNumBits(byte num) {
	    // Convert the number to an unsigned value and count the number of leading zeros
	    int unsignedNum = num & 0xFF;
	    return Integer.SIZE - Integer.numberOfLeadingZeros(unsignedNum);
	}
	public static byte extendNegativeTo8Bit(byte num, int numBits) {
	    // Check if the number is negative (MSB is 1)
	    if ((num & (1 << (numBits - 1))) != 0) {
	        // Perform sign extension by replicating the sign bit to fill the remaining bits
	        int extensionBits = Byte.SIZE - numBits;
	        int signBit = 1 << (Byte.SIZE - 1);
	        int extensionMask = (1 << extensionBits) - 1;
	        int extendedValue = (num | (signBit >> (numBits - 1))) & (extensionMask | signBit);
	        return (byte) extendedValue;
	    } else {
	        // The number is positive, return the original value
	        return num;
	    }
	}

}
