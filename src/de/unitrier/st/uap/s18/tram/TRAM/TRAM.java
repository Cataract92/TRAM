/*
 * Nico Feld - 1169233
 */

package de.unitrier.st.uap.s18.tram.TRAM;

import de.unitrier.st.uap.s18.tram.Program;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TRAM {

    private ExecutorService executorService;

    public TRAM(int threadCount) {
        executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void startProgram(Program program)
    {
        executorService.submit(new TRAMThread(program));
    }


}
