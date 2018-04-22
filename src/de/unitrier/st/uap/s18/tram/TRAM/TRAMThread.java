package de.unitrier.st.uap.s18.tram.TRAM;

import de.unitrier.st.uap.s18.tram.Instruction;

import java.util.ArrayList;

public class TRAMThread extends Thread{

    private Instruction[] programm;
    private TramInstructionHandler handler;
    private boolean isRunning = false;

    DynamicStack STACK = new DynamicStack();
    int PP, TOP = -1, FP, PC;




    public TRAMThread(Instruction[] programm) {
        this.programm = programm;
        handler = new TramInstructionHandler(this);
    }

    @Override
    public void run() {
        super.run();
        this.isRunning = true;
        System.out.println(executeProgramm());
        this.isRunning = false;

    }

    public boolean isRunning() {
        return isRunning;
    }

    public int executeProgramm()
    {
        while (PC >= 0)
            executeInst(programm[PC]);
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
