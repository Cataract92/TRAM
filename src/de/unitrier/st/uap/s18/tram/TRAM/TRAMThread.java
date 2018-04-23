package de.unitrier.st.uap.s18.tram.TRAM;

import de.unitrier.st.uap.s18.tram.Instruction;
import de.unitrier.st.uap.s18.tram.Program;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;

public class TRAMThread implements Callable<Integer>{

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
    }

    public Integer call() throws Exception {
        return executeProgramm();
    }

    private int executeProgramm()
    {
        while (PC >= 0) {
            StringBuilder output = new StringBuilder("After instruction = "+ instructions[PC]);
            executeInst(instructions[PC]);
            output.append("; configuration = PC = ").append(PC).append("; PP = ").append(PP).append("; FP = ").append(FP).append("; TOP = ").append(TOP).append("\n");
            output.append("Stack: \n");

            for (int i = 0; i<= TOP; i++)
            {
                output.append("[").append(i).append("] = ").append(STACK.get(i));
                if (PP == i)
                    output.append(" <-- PP");
                if (FP == i)
                    output.append(" <-- FP");
                if (TOP == i)
                    output.append(" <-- TOP");

                output.append("\n");
            }

            output.append("\n");

            logger.debug(output);
        }
        return STACK.get(TOP);
    }

    private void executeInst(Instruction inst)
    {
        switch (inst.getOpcode()){
            case Instruction.CONST : handler.handleConst(inst.getArg1()); break;
            case Instruction.LOAD : handler.handleLoad(inst.getArg1(),inst.getArg2());break;
            case Instruction.STORE : handler.handleStore(inst.getArg1(),inst.getArg2());break;
            case Instruction.ADD : handler.handleAdd();break;
            case Instruction.SUB : handler.handleSub();break;
            case Instruction.MUL : handler.handleMul();break;
            case Instruction.DIV : handler.handleDiv();break;
            case Instruction.LT : handler.handleLt();break;
            case Instruction.GT : handler.handleGt();break;
            case Instruction.EQ : handler.handleEQ();break;
            case Instruction.NEQ : handler.handleNEQ();break;
            case Instruction.IFZERO : handler.handleIfZero(inst.getArg1());break;
            case Instruction.GOTO : handler.handleGoto(inst.getArg1());break;
            case Instruction.HALT : handler.handleHalt();break;
            case Instruction.NOP : handler.handleNop();break;
            case Instruction.INVOKE : handler.handleInvoke(inst.getArg1(),inst.getArg2(),inst.getArg3());break;
            case Instruction.RETURN : handler.handleReturn();break;
        }
    }

}
