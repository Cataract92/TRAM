package de.unitrier.st.uap.s18.tram;

import de.unitrier.st.uap.s18.tram.TRAM.TRAM;

import java.util.logging.LogManager;

final class Main
{
	private Main(){}
	
	public static void main(String[] argv)
	{
	    TRAM t = new TRAM(4);
	    t.startProgramm(Instruction.program1);
        t.startProgramm(Instruction.program2);
        t.startProgramm(Instruction.program3);
        t.startProgramm(Instruction.program4);
	}
}