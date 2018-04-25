/*
 * Nico Feld - 1169233
 */

/**
 *  Instructions for TRAM 2018
 */

package de.unitrier.st.uap.s18.tram;

public class Instruction
{
	private int opcode;
	private Integer arg1;
	private Integer arg2;
	private Integer arg3;

	public final static int CONST = 1;
	public final static int LOAD = 2;
	public final static int STORE = 3;
	public final static int ADD = 4;
	public final static int SUB = 5;
	public final static int MUL = 6;
	public final static int DIV = 7;
	public final static int LT = 8;
	public final static int GT = 9;
	public final static int EQ = 10;
	public final static int NEQ = 11;
	public final static int IFZERO = 12;
	public final static int GOTO = 13;
	public final static int HALT = 14;
	public final static int NOP = 15;
	public final static int INVOKE = 16;
	public final static int RETURN = 17;


	public Instruction(int opcode, Integer arg1, Integer arg2, Integer arg3)
	{
		this(opcode, arg1, arg2);
		this.arg3 = arg3;
	}


	public Instruction(int opcode, Integer arg1, Integer arg2)
	{
		this(opcode, arg1);
		this.arg2 = arg2;
	}


	public Instruction(int opcode, Integer arg1)
	{
		this(opcode);
		this.arg1 = arg1;
	}


	public Instruction(int opcode)
	{
		this.opcode = opcode;
	}


	public int getArg1()
	{
		return arg1;
	}


	public void setArg1(Integer arg1)
	{
		this.arg1 = arg1;
	}


	public int getArg2()
	{
		return arg2;
	}


	public void setArg2(Integer arg2)
	{
		this.arg2 = arg2;
	}


	public int getArg3()
	{
		return arg3;
	}


	public void setArg3(Integer arg3)
	{
		this.arg3 = arg3;
	}


	public int getOpcode()
	{
		return opcode;
	}


	public void setOpcode(int opcode)
	{
		this.opcode = opcode;
	}


	@Override
	public String toString()
	{
		String retStr = "";

		switch (opcode)
		{
			case Instruction.CONST:
				retStr += "CONST";
				break;
			case Instruction.LOAD:
				retStr += "LOAD";
				break;
			case Instruction.STORE:
				retStr += "STORE";
				break;
			case Instruction.ADD:
				retStr += "ADD";
				break;
			case Instruction.SUB:
				retStr += "SUB";
				break;
			case Instruction.MUL:
				retStr += "MUL";
				break;
			case Instruction.DIV:
				retStr += "DIV";
				break;
			case Instruction.LT:
				retStr += "LT";
				break;
			case Instruction.GT:
				retStr += "GT";
				break;
			case Instruction.EQ:
				retStr += "EQ";
				break;
			case Instruction.NEQ:
				retStr += "NEQ";
				break;
			case Instruction.IFZERO:
				retStr += "IFZERO";
				break;
			case Instruction.GOTO:
				retStr += "GOTO";
				break;
			case Instruction.HALT:
				retStr += "HALT";
				break;
			case Instruction.NOP:
				retStr += "NOP";
				break;
			case Instruction.INVOKE:
				retStr += "INVOKE";
				break;
			case Instruction.RETURN:
				retStr += "RETURN";
				break;
			default:
				retStr += "ERROR";
		}

		if (arg1 != null)
		{
			retStr += " " + arg1;

			if (arg2 != null)
			{
				retStr += " " + arg2;

				if (arg3 != null)
				{
					retStr += " " + arg3;
				}
			}
		}

		return retStr;
	}


	/***********************************************
	 * Sample Programs
	 ***********************************************/

	/**
	 * Quellkode: y = x*3+5*2 
	 * Annahmen: Variable x durch Kellerzelle 0 und Variable y durch Kellerzelle 1 implementiert, 
	 *           sowie PP=0, FP=0 und TOP=1.
	 */
	static Instruction[] program1 = new Instruction[] {
			new Instruction(Instruction.CONST, 6), // value for x
			new Instruction(Instruction.STORE, 0, 0), // store x
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.CONST, 5),
			new Instruction(Instruction.CONST, 2), 
			new Instruction(Instruction.MUL),
			new Instruction(Instruction.ADD), 
			new Instruction(Instruction.STORE, 1, 0),
			new Instruction(Instruction.HALT) };

	/**
	 * Quellkode: x=10; if(x == 0) 100 else 200; 3 
	 * Annahmen: Variable x durch Kellerzelle 0 implementiert, sowie PP=0, FP=0 und TOP=0.
	 */
	static Instruction[] program2 = new Instruction[] { 
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.STORE, 0, 0),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.IFZERO, 6), // --> iftrue
			new Instruction(Instruction.CONST, 200),
			new Instruction(Instruction.GOTO, 7), // --> goto
			// iftrue
			new Instruction(Instruction.CONST, 100),
			// goto
			new Instruction(Instruction.NOP), 
			new Instruction(Instruction.CONST, 3),
			new Instruction(Instruction.HALT) };

	/**
	 * Quellkode: let square(x) { x*x } 
	 *            in square(10) 
	 * Annahmen: Das Argument von square wird durch Kellerzelle 0 repraesentiert, sowie PP=0, FP=0 
	 *           und TOP=-1
	 */
	static Instruction[] program3 = new Instruction[] {
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.INVOKE, 1, 3, 0), // --> square
			// return
			new Instruction(Instruction.HALT),
			// square
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.RETURN) // --> return
	};

	/**
	 * Quellkode: let wrapper(number, threshold) { 
	 *                  let square(x) { 
	 *                        if (x*x > threshold) x 
	 *                        else x*x 
	 *                      }
	 *                  in square(number) 
	 *                } 
	 *            in wrapper(4, 10) 
	 * Annahmen: Die Argumente von wrapper werden durch die Kellerzellen 0 und 1 repraesentiert, 
	 *           sowie PP=0, FP=0 und TOP=-1
	 */
	static Instruction[] program4 = new Instruction[] {
			new Instruction(Instruction.CONST, 4),
			new Instruction(Instruction.CONST, 10),
			new Instruction(Instruction.INVOKE, 2, 4, 0), // --> wrapper
			// return wrapper
			new Instruction(Instruction.HALT),
			// wrapper
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.INVOKE, 1, 7, 0), // --> square
			// return square
			new Instruction(Instruction.RETURN),
			// square
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.LOAD, 1, 1),
			new Instruction(Instruction.GT), 
			new Instruction(Instruction.IFZERO, 15),
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.RETURN), // --> return square
			new Instruction(Instruction.LOAD, 0, 0), 
			new Instruction(Instruction.LOAD, 0, 0),
			new Instruction(Instruction.MUL), 
			new Instruction(Instruction.RETURN) // --> return square
	};


    /**
     * Quellcode: let euclid(a, b) {
     *                if ( b != 0) then
     *                    euclid(b, let mod(x,y,i) {
     *                            do {
     *                                i += b
     *                            } while ( i < a || i == a)
     *                            i = i - b
     *                            a = a - i
     *                        } in mod (a,b,0)
     *            } in euclid(3528,3780)
     * Annahmen: Die Argumente von euclid werden durch die Kellerzellen 0 und 1 repraesentiert,
     *           sowie PP=0, FP=0 und TOP=-1
     */
	
	static Instruction[] programEuclid = new Instruction[] {
			// Init
	/* 00 */    new Instruction(Instruction.CONST, 3528),
    /* 01 */    new Instruction(Instruction.CONST, 3780),
    /* 02 */    new Instruction(Instruction.INVOKE,2,4,0),
    /* 03 */    new Instruction(Instruction.HALT),

            // Euclid
    /* 04 */    new Instruction(Instruction.LOAD, 1, 0),
    /* 05 */    new Instruction(Instruction.IFZERO, 13),
    /* 06 */    new Instruction(Instruction.LOAD, 1, 0),
    /* 07 */    new Instruction(Instruction.LOAD, 0, 0),
    /* 08 */    new Instruction(Instruction.LOAD, 1, 0),
    /* 09 */    new Instruction(Instruction.CONST, 0),
    /* 10 */    new Instruction(Instruction.INVOKE,3,15,0),
    /* 11 */    new Instruction(Instruction.INVOKE, 2,4,0),
    /* 12 */    new Instruction(Instruction.RETURN),
    /* 13 */    new Instruction(Instruction.LOAD, 0, 0),
    /* 14 */    new Instruction(Instruction.RETURN),

			// Mod
    /* 15 */    new Instruction(Instruction.LOAD, 2, 0),
    /* 16 */    new Instruction(Instruction.LOAD, 0, 0),
    /* 17 */    new Instruction(Instruction.LT),
    /* 18 */    new Instruction(Instruction.IFZERO, 20),
    /* 19 */    new Instruction(Instruction.GOTO,24),

    /* 20 */    new Instruction(Instruction.LOAD, 2, 0),
    /* 21 */    new Instruction(Instruction.LOAD, 0, 0),
    /* 22 */    new Instruction(Instruction.EQ),
    /* 23 */    new Instruction(Instruction.IFZERO, 29),
    /* 24 */    new Instruction(Instruction.LOAD, 2, 0),
    /* 25 */    new Instruction(Instruction.LOAD, 1, 0),
    /* 26 */    new Instruction(Instruction.ADD),
    /* 27 */    new Instruction(Instruction.STORE,2,0),
    /* 28 */    new Instruction(Instruction.GOTO,15),

    /* 29 */    new Instruction(Instruction.LOAD, 2, 0),
    /* 30 */    new Instruction(Instruction.LOAD, 1, 0),
    /* 31 */    new Instruction(Instruction.SUB),
    /* 32 */    new Instruction(Instruction.STORE,2,0),
    /* 33 */    new Instruction(Instruction.LOAD, 0, 0),
    /* 34 */    new Instruction(Instruction.LOAD, 2, 0),
    /* 35 */    new Instruction(Instruction.SUB),
    /* 36 */    new Instruction(Instruction.RETURN),
	};
}