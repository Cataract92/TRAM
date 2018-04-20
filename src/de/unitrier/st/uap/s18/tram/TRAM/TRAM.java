package de.unitrier.st.uap.s18.tram.TRAM;

import de.unitrier.st.uap.s18.tram.Instruction;

import java.util.ArrayList;

public class TRAM {

    private int threadCount;

    public TRAM(int threadCount) {
        this.threadCount = threadCount;
    }

    public void runProgramm(Instruction[] programm)
    {
        TRAMThread thread = new TRAMThread(programm);
        thread.start();
    }
}
