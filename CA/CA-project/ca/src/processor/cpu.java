package processor;

import java.awt.TextArea;
import java.nio.file.Paths;

import fileReader.parser;
import instructions.*;
import memory.DataMemory;
import memory.InstructionMemory;




public class cpu {
	private int clock;
	private Byte[] registers = new Byte[64];;
	private short PC;
	private byte SREG=0;
	private instruction fetching=null;
	private instruction decoding=null;
	private instruction executing=null;
	private static final cpu instance = new cpu();
	private  int  numboerOfClock ;
	private int c; 
	private int v=0;
	private int n;
	private int s;
	private int z;
	private boolean jump=false;

	
//public  void cpu2(String file) throws Exception {
//	parser.parseProgram(file);
//	numboerOfClock= InstructionMemory.getInstance().getLastInstruction()+3;
//	clock=1;
//    for(int i=0;i<64;i++) {
//    	registers[i]=0;
//    }
//	int[] res=null;
//	while(clock<=numboerOfClock) {
//	if(clock==1) {
//		fetching = fetch();
//	}
//	else if (clock ==2) {
//		decoding=fetching;
//		res=decode(fetching);
//		fetching = fetch();	
//	}
//	else if (clock==numboerOfClock-1) {
//		executing=decoding;
//		decoding=fetching;
//		execute(res);
//		res=decode(fetching);
//		fetching=null;
//	}
//	else if (clock ==numboerOfClock) {
//		executing=decoding;
//		execute(res);
//		fetching=null;
//		decoding=null;
//	}
//	else {
//		executing=decoding;
//		decoding=fetching;
//		execute(res);
//		res=decode(fetching);
//		fetching = fetch();
//	}
//	printing(clock,fetching,decoding,executing);
//	System.out.println(" SREG content is :   "+printBinary(SREG));
//
//	clock++;
//
//	}
//	printAllRegister();
//	printAllMeomry();
//}
//public  void cpu1(String file) throws Exception {
//	parser.parseProgram(file);
//	clock=1;
//    for(int i=0;i<64;i++) {
//    	registers[i]=0;
//    }
//	int[] res=null;
//	int  numberofinstruction=InstructionMemory.getInstance().getLastInstruction();
//	while(true) {
//		if(fetching==null&&executing==null&&decoding==null) {
//			fetching=fetch();
//			if((fetching!=null)&&((fetching.getBinary()==4 && fetching.getFirstRegister()==0)||fetching.getBinary()==7)){
//				res=decode(fetching);
//				execute(res);
//				clock++;
//				fetching=null;
//				decoding=null;
//				executing=null;
//			}
//		}
//		else if(fetching!=null&&decoding==null&&executing==null) {
//			decoding=fetching;
//			res=decode(decoding);
//			fetching=fetch();
//			if((fetching!=null)&&((fetching.getBinary()==4 && fetching.getFirstRegister()==0)||fetching.getBinary()==7)){
//			execute(res);
//			clock++;
//			res=decode(fetching);
//			execute(res);
//			clock++;
//			fetching=null;
//			decoding=null;
//			executing=null;
//			}
//		}
//		else if(fetching!=null&&decoding!=null&&(executing==null||executing!=null)){
//			
//			executing=decoding;
//			execute(res);
//			clock++;
//			decoding=fetching;
//			res =decode(decoding);
//			fetching=fetch();
//		//	System.out.println(fetching!=null);
//			if((fetching!=null)&&((fetching.getBinary()==4 && fetching.getFirstRegister()==0)||fetching.getBinary()==7)){
//				execute(res);
//				clock++;
//				res=decode(fetching);
//				execute(res);
//				clock++;
//				fetching=null;
//				decoding=null;
//				executing=null;
//				}
//		}
//		else if(fetching==null&&decoding!=null&&executing!=null) {
//			executing=decoding;
//			execute(res);
//			clock++;
//			decoding=fetching;
//
//		}
//		else if(fetching==null&&decoding==null&&executing!=null) {
//			executing=decoding;
//			break;
//		}
////System.out.println(PC);
//	}
//}
public  void cpu(String file) throws Exception {
parser.parseProgram(file);
clock=1;
for(int i=0;i<64;i++) {
	registers[i]=0;
}
int[] res=null;
while(true) {
	if(fetching==null&&decoding==null&&executing==null) {
		fetching=fetch();

	}
	else if(fetching!=null&&decoding==null&&executing==null) {
		decoding=fetching;
		res=decode(decoding);
		fetching=fetch();
		
	}
	else if(fetching!=null&&decoding!=null&&(executing==null||executing!=null)){
		executing=decoding;
		if((executing!=null)&&((executing.getOpCode()==4 && registers[executing.getFirstRegister()]==0)||executing.getOpCode()==7)){
		//	System.out.println(executing.getInstruction());
			execute(res);
			decoding=fetching;
			fetching=fetch();
			printing(clock,fetching,decoding,executing);
			fetching=null;
			decoding=null;
			executing=null;
			res=null;
		
			}
		else {
		execute(res);
		decoding=fetching;
		res =decode(decoding);	

		fetching=fetch();
		
		
		}
	}
	else if(fetching==null&&decoding!=null&&executing!=null) {
		executing=decoding;
		if((executing!=null)&&((executing.getOpCode()==4 && registers[executing.getFirstRegister()]==0)||executing.getOpCode()==7)){
			execute(res);
			decoding=fetching;
			fetching=fetch();
			printing(clock,fetching,decoding,executing);
			fetching=null;
			decoding=null;
			executing=null;
			
			}
		else {
		execute(res);
		decoding=fetching;
	
		
		}
	}
		else if(fetching==null&&decoding!=null&&executing==null) {
			executing=decoding;
			decoding=fetching;
			clock=clock-1;
		}
	else if(fetching==null&&decoding==null&&executing!=null) {
		executing=decoding;
		
		break;
	}
	printing(clock,fetching,decoding,executing);	
//	System.out.println(" SREG content is :   "+printBinary(SREG));
	clock++;
//System.out.println(PC);
  }
printAllRegister();
printAllMeomry();
}
private void printAllMeomry() {
	DataMemory memory =DataMemory.getInstance();
	System.out.println("___________________DataMemory______________________");
	for(int i =0 ;i<memory.getDataMemory().length;i++) {
		System.out.println("row" + i +":        "+ printBinary(memory.getDataMemory()[i]));
	}
	InstructionMemory instructionMemory =InstructionMemory.getInstance();
	System.out.println("___________________instructionMemory______________________");
	for(int i =0 ;i<instructionMemory.getInstructionMemory().length;i++) {
		if(instructionMemory.getInstructionMemory()[i]!=null)
		   System.out.println("instruction" + i +":         "+ printBinary(instructionMemory.getInstructionMemory()[i].getBinary()));
		else
			System.out.println("instruction" + i +":         "+"there is no instruction");
	}
}
private void printAllRegister() {
	System.out.println("___________________registers______________________");
	for(int i=0;i<64;i++) {
		System.out.println("R"+i+":      "+printBinary(registers[i]));
	
	}
	//System.out.println("_______________________________________________");
}
public String printBinary(Object obj) {
    if (obj instanceof Short) {
        short value = (Short) obj;
        StringBuilder binaryString = new StringBuilder(Short.SIZE);
        for (int i = Short.SIZE - 1; i >= 0; i--) {
            binaryString.append((value & (1 << i)) != 0 ? '1' : '0');
        }
        return binaryString.toString();
    } else if (obj instanceof Byte) {
    	 byte value = (Byte) obj;
    	    StringBuilder binaryString = new StringBuilder(Byte.SIZE);
    	    for (int i = Byte.SIZE - 1; i >= 0; i--) {
    	        binaryString.append((value & (1 << i)) != 0 ? '1' : '0');
    	    }
    	    return binaryString.toString();
    } else {
        return "Unsupported type";
    }
}

public void printing(int clock,instruction fetching,instruction decoding, instruction executing) {
	System.out.println("______________________clock" +clock+"______________________");
	if(fetching!=null)
	 System.out.println("instruction will be execute in fetching stage is :"+fetching.getAddress()+"name "+fetching.getInstruction());
	else 
	 System.out.println("no fetching in this cycle");
	if(decoding!=null)
	System.out.println("instruction will be execute in decoding stage is :"+decoding.getAddress()+"name "+decoding.getInstruction());
	else 
		 System.out.println("no decoding in this cycle");
	if(executing!=null)
	System.out.println("instruction will be execute in executing stage is :"+executing.getAddress()+"name "+executing.getInstruction());
	else 
		 System.out.println("no executing in this cycle");
	
	System.out.println("________________endcycle_________________");
}
public instruction fetch() {
	return InstructionMemory.getInstance().getInstructionMemory()[PC++];
}
public int[] decode(instruction instruction) {
	int opcode =instruction.getOpCode();
	int r1=instruction.getFirstRegister();
	int r2;
	int r2content;
	if(instruction.getType()=="R") {
		r2=((RInstruction)instruction).getSecondRegister();
		r2content=registers[r2];
	}
	else {
		r2content=((IInstruction)instruction).getImmediate();
	}
	int r1content=registers[r1];
	int[] res = {opcode,r1,r1content,r2content}; 
	return res;
	
}
public void execute(int[] res) {
	switch (res[0]) {
	case 0:
		 Add(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 1:
		Sub(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 2:
		 Mul(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 3:
		Ldi(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 4:
		Beqz(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 5:
		And(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 6:
		Or(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 7:
		Jr(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 8:
		Slc(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 9:
		Src(res[1],(byte)res[2],(byte)res[3]);
		break;
	case 10:
		Lb(res[1],(byte)res[2],(byte)res[3]);
		break;
	default:
		Sb(res[1],(byte)res[2],(byte)res[3]);
		break;
	}
	SREG();
}

public void Add(int r1,byte r1content,byte r2content) {
	 int tmp =r1content+r2content;
     cFlag(tmp);
	 vFlag(r1content,r2content,(byte)tmp,"Add");
	 nFlag((byte)tmp);
	 sFlag();
	 zFlag((byte)tmp);
	registers[r1]=(byte)tmp;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)tmp) +"  "+ (byte)tmp);
}
public void Sub(int r1,byte r1content,byte r2content) {
	 int tmp =r1content-r2content;
	 vFlag(r1content,r2content,(byte)tmp,"Sub");
	 nFlag((byte)tmp);
	 sFlag();
	 zFlag((byte)tmp); 
	registers[r1]=(byte)tmp;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)tmp)+"  "+(byte)tmp);
	//overflow  v ,nflag,sflag,z
}
public void Mul(int r1,byte r1content,byte r2content) {
	 int tmp =r1content*r2content;
	 nFlag((byte)tmp);
	 zFlag((byte)tmp); 
	registers[r1]=(byte)tmp;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)tmp)+"  "+(byte)tmp);
	//nfalg,z
}
public void Ldi(int r1,byte r1content,byte r2content) {
	int shiftedNumber = r2content >> 5;
	int sixthBit = shiftedNumber & 1;
	if(sixthBit==1) {		
		r2content	=(byte) (r2content|0b11000000);
		}
//	System.out.println(printBinary(r2content));
	registers[r1]=r2content;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)r2content)+"  "+(byte)r2content);
}
public void Beqz(int r1,byte r1content,byte r2content) {
//	System.out.println(PC);
	if(r1content == 0) {
		PC=(short) (PC-3);
		PC = (short) (PC+1+r2content); }
//	System.out.println(PC);
}
public void And(int r1,byte r1content,byte r2content) {
	 int tmp =r1content&r2content;
	 nFlag((byte)tmp);
	 zFlag((byte)tmp); 
	registers[r1]=(byte)tmp;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)tmp)+"  "+ (byte)tmp);
	//nfalg,z
}
public void Or(int r1,byte r1content,byte r2content) {
	 int tmp =r1content|r2content;
	 nFlag((byte)tmp);
	 zFlag((byte)tmp); 
	registers[r1]=(byte)tmp;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)tmp)+"  "+ (byte)tmp);
	//nfalg,z
}
public void Jr(int r1,byte r1content,byte r2content) {
//	System.out.println(PC);
//	System.out.println(r2content);
	
	PC = (short) ((r1content<<8) | r2content);
	PC=(short) (PC-2);
//	System.out.println(PC);
}
public void Slc(int r1,byte r1content,byte r2content) {
	 int tmp =r1content << r2content | r1content >>> 8 - r2content;
	 nFlag((byte)tmp);
	 zFlag((byte)tmp); 
	registers[r1]=(byte)tmp;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)tmp)+"  "+ (byte)tmp);
	//nfalg,z
}
public void Src(int r1,byte r1content,byte r2content) {
	 int tmp =r1content >>> r2content | r1content << 8 - r2content;
	 nFlag((byte)tmp);
	 zFlag((byte)tmp); 
	registers[r1]=(byte)tmp;
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)tmp)+"  "+ (byte)tmp);
	//nfalg,z
}
public void Lb(int r1,byte r1content,byte r2content) {
	registers[r1] =(byte)DataMemory.getInstance().readAddress(r2content);
	System.out.println("update register for next cycle"+r1+ ":"+printBinary((byte)DataMemory.getInstance().readAddress(r2content))+" "+(byte)DataMemory.getInstance().readAddress(r2content));
}
public void Sb(int r1,byte r1content,byte r2content) {
	DataMemory.getInstance().writeAddress(r2content,r1content);
	System.out.println("update data memory at the address for next cycle"+r2content+ ":"+printBinary((byte)r1content)+" "+ (byte)r1content);
}
public void SREG() {
//	byte OLDSREG = SREG;
	String binaryValue ="000" + c + v + n + s + z;
	byte SREG = (byte) Integer.parseInt(binaryValue, 2);
	if(SREG!=0)
	 System.out.println("SREG content is changed to: "+printBinary(SREG));
}
public void zFlag(byte r1) {
if (r1==0) {
	z=1;
}
else {
	z=0;
}
}

public void sFlag() {
	s = n ^ v;
}

public void nFlag(byte value) {
if(value<0)
	n=1;
else
	n=0;
}

public void vFlag(byte r1, byte r2, byte res,String operation) {
if(operation.equals("Add")) {
	if(r1>0&&r2>0&&res<0)
		v=1;
	if(r1<0&&r2<0&&res>0)
		v=1;
}
else {
	if(r1>0&&r2<0&&res<0)
		v=1;
	if(r1<0&&r2>0&&res>0)
		v=1;
}
}

public void cFlag(int res) {
 if(8>>res==1)
	 c=1;
 else
	 c=0;
}

public static void main(String[] args) throws Exception {
	cpu c=new cpu();
	c.cpu("src/filetest/testing.txt");
}
}
