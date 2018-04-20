package de.unitrier.st.uap.s18.tram.TRAM;

public class TramInstructionHandler {

    private TRAMThread thread = null;

    public TramInstructionHandler(TRAMThread thread) {
        this.thread = thread;
    }

    void handleConst(int a1)
    {
        thread.STACK.set(++thread.TOP, a1);
        thread.PC++;
    }

    void handleLoad(int a1)
    {
        thread.STACK.set(++thread.TOP,thread.STACK.get(thread.PP + a1));
        thread.PC++;
    }

    void handleStore(int a1)
    {
        thread.STACK.set(thread.PP + a1, thread.STACK.get(thread.TOP--));
        thread.PC++;
    }

    void handleAdd()
    {
        thread.TOP--;
        thread.STACK.set(thread.TOP, thread.STACK.get(thread.TOP) + thread.STACK.get(thread.TOP+1));
        thread.PC++;
    }

    void handleSub()
    {
        thread.TOP--;
        thread.STACK.set(thread.TOP, thread.STACK.get(thread.TOP) - thread.STACK.get(thread.TOP+1));
        thread.PC++;
    }

    void handleMul()
    {
        thread.TOP--;
        thread.STACK.set(thread.TOP, thread.STACK.get(thread.TOP) * thread.STACK.get(thread.TOP+1));
        thread.PC++;
    }

    void handleDiv()
    {
        thread.TOP--;
        thread.STACK.set(thread.TOP, thread.STACK.get(thread.TOP) / thread.STACK.get(thread.TOP+1));
        thread.PC++;
    }

    void handleLt()
    {
        thread.TOP--;
        if (thread.STACK.get(thread.TOP) < thread.STACK.get(thread.TOP + 1))
            thread.STACK.set(thread.TOP,1);
        else
            thread.STACK.set(thread.TOP,0);

        thread.PC++;
    }

    void handleGt()
    {
        thread.TOP--;
        if (thread.STACK.get(thread.TOP) > thread.STACK.get(thread.TOP + 1))
            thread.STACK.set(thread.TOP,1);
        else
            thread.STACK.set(thread.TOP,0);

        thread.PC++;
    }

    void handleEQ()
    {
        thread.TOP--;
        if (thread.STACK.get(thread.TOP) == thread.STACK.get(thread.TOP + 1))
            thread.STACK.set(thread.TOP,1);
        else
            thread.STACK.set(thread.TOP,0);

        thread.PC++;
    }

    void handleNEQ()
    {
        thread.TOP--;
        if (thread.STACK.get(thread.TOP) != thread.STACK.get(thread.TOP + 1))
            thread.STACK.set(thread.TOP,1);
        else
            thread.STACK.set(thread.TOP,0);

        thread.PC++;
    }

    void handleIfZero(int a1)
    {
        if (thread.STACK.get(thread.TOP) == 0)
            thread.PC = a1;
        else
            thread.PC++;

        thread.TOP--;
    }

    void handleGoto(int a1)
    {
        thread.PC = a1;
    }

    void handleHalt()
    {
        thread.PC = -1;
    }

    void handleNop()
    {
        thread.PC++;
    }

    void handleInvoke()
    {

    }

    void handleReturn()
    {

    }
}
