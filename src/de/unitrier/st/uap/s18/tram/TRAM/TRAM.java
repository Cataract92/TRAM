package de.unitrier.st.uap.s18.tram.TRAM;

import de.unitrier.st.uap.s18.tram.Instruction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class TRAM {

    private Instruction[] programm;
    private TramInstructionHandler handler;

    private ExecutorService executorService;

    private List<Future<Integer>> allFutures = Collections.synchronizedList(new ArrayList<Future<Integer>>());

    public TRAM(int threadCount) {
        executorService = Executors.newFixedThreadPool(threadCount);
        new Thread(() -> {
            while (true) {
                for (int i = 0; i < allFutures.size(); i++)
                {
                    Future<Integer> ft = allFutures.get(i);
                    if (ft.isDone()) {
                        try {
                            System.out.println(ft.get());
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        allFutures.remove(ft);
                        break;
                    }
                }
            }
        }).start();
    }

    public void startProgramm(Instruction[] programm)
    {
        Future<Integer> futureTask = executorService.submit(new TRAMThread(programm));
        allFutures.add(futureTask);
    }


}
