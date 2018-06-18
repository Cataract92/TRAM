/*
 * Nico Feld - 1169233
 */

package de.unitrier.st.uap.s18.tram.TRAM;

import de.unitrier.st.uap.s18.tram.Instruction;
import de.unitrier.st.uap.s18.tram.Program;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class TRAMThread implements Runnable{

    private Instruction[] instructions;

    DynamicStack STACK = new DynamicStack();
    int PP, TOP, FP, PC;

    private TramInstructionHandler handler;
    private Logger logger = LogManager.getRootLogger();

    public TRAMThread(Program program) {
        this.instructions = program.getInstructions();
        this.handler = new TramInstructionHandler(this);
        this.PP = program.getPP();
        this.TOP = program.getTOP();
        this.PC = program.getPC();
        this.logger.addAppender(new ConsoleAppender(new PatternLayout("%d{dd MMM yyyy HH:mm:ss} [%t] - %m%n")));
    }

    @Override
    public void run() {
        System.out.println("Ergebniss: "+executeProgramm());
    }

    private int executeProgramm()
    {
        while (PC >= 0) {
            String output = "After instruction = "+ instructions[PC];
            executeInst(instructions[PC]);
            output += "; configuration = PC = "+PC+"; PP = "+PP+"; FP = "+FP+"; TOP = "+TOP+"\nStack: \n";

            for (int i = 0; i<= TOP; i++)
            {
                output += "["+i+"] = " +STACK.get(i);
                if (PP == i)
                    output += " <-- PP";
                if (FP == i)
                    output += " <-- FP";
                if (TOP == i)
                    output += " <-- TOP";

                output += "\n";
            }

            output += "\n";

            logger.debug(output);
        }
        return STACK.get(TOP);
    }

    private void executeInst(Instruction inst)
    {
        switch (inst.getOpcode()){
            case Instruction.CONST : handler.handleCONST(inst.getArg1()); break;
            case Instruction.LOAD : handler.handleLOAD(inst.getArg1(),inst.getArg2());break;
            case Instruction.STORE : handler.handleSTORE(inst.getArg1(),inst.getArg2());break;
            case Instruction.ADD : handler.handleADD();break;
            case Instruction.SUB : handler.handleSUB();break;
            case Instruction.MULT : handler.handleMULT();break;
            case Instruction.DIV : handler.handleDIV();break;
            case Instruction.LT : handler.handleLT();break;
            case Instruction.GT : handler.handleGT();break;
            case Instruction.EQ : handler.handleEQ();break;
            case Instruction.NEQ : handler.handleNEQ();break;
            case Instruction.IFZERO : handler.handleIFZERO(inst.getArg1());break;
            case Instruction.GOTO : handler.handleGOTO(inst.getArg1());break;
            case Instruction.HALT : handler.handleHALT();break;
            case Instruction.NOP : handler.handleNOP();break;
            case Instruction.INVOKE : handler.handleINVOKE(inst.getArg1(),inst.getArg2(),inst.getArg3());break;
            case Instruction.RETURN : handler.handleRETURN();break;
            case Instruction.POP : handler.handlePOP();break;
            case Instruction.AND : handler.handleAND();
            case Instruction.OR : handler.handleOR();break;
            case Instruction.GTE : handler.handleGTE();break;
            case Instruction.LTE : handler.handleLTE();break;
        }
    }

}
