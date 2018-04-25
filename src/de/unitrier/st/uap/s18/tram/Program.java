/*
 * Nico Feld - 1169233
 */

package de.unitrier.st.uap.s18.tram;

public class Program
{
	private Instruction[] instructions;
	
	private int TOP, PC, PP;
	
	public Program(Instruction[] instructions, int TOP, int PC, int PP)
	{
		this.instructions = instructions;
		this.TOP = TOP;
		this.PC = PC;
		this.PP = PP;
	}
	
	public Instruction[] getInstructions()
	{
		return instructions;
	}
	
	public int getTOP()
	{
		return TOP;
	}
	
	public int getPC()
	{
		return PC;
	}
	
	public int getPP()
	{
		return PP;
	}

}
