package de.unitrier.st.uap.s18.tram;

import de.unitrier.st.uap.s18.tram.TRAM.TRAM;

final class Main
{
	private Main(){}
	
	public static void main(String[] argv)
	{
		new TRAM(3).runProgramm(Instruction.program4);
	}
}