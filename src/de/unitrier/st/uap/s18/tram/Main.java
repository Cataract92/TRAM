/*
 * Nico Feld - 1169233
 */

package de.unitrier.st.uap.s18.tram;

import de.unitrier.st.uap.s18.tram.TRAM.TRAM;

final class Main
{
	private Main(){}
	
	public static void main(String[] argv)
	{
	    TRAM t = new TRAM(3);

		//t.startProgram(new Program(Instruction.program1,1,0,0));
        //t.startProgram(new Program(Instruction.program2,0,0,0));
        //t.startProgram(new Program(Instruction.program3,-1,0,0));
        //t.startProgram(new Program(Instruction.program4,-1,0,0));
		t.startProgram(new Program(Instruction.programEuclid,-1,0,0));
	}
}