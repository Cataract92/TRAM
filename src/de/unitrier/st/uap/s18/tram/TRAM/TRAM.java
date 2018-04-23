package de.unitrier.st.uap.s18.tram.TRAM;

import de.unitrier.st.uap.s18.tram.Program;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TRAM {

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

    public void startProgramm(Program program)
    {
        Future<Integer> futureTask = executorService.submit(new TRAMThread(program));
        allFutures.add(futureTask);
    }


}
