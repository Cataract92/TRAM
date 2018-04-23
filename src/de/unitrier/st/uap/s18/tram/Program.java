package de.unitrier.st.uap.s18.tram;

/**
 * Created by nico on 23.04.18.
 */
public class Program
{
	private Instruction[] instructions = null;
	
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
