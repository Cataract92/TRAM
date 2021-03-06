/*
 * Nico Feld - 1169233
 */

package de.unitrier.st.uap.s18.tram.TRAM;

public class TramInstructionHandler {

    private TRAMThread thread;

    TramInstructionHandler(TRAMThread thread) {
        this.thread = thread;
    }

    private int spp(int d, int pp, int fp)
    {
        if (d == 0)
            return pp;
        else
            return spp(d-1,thread.STACK.get(fp+3),thread.STACK.get(fp+4));
    }

    private int sfp(int d, int pp, int fp)
    {
        if (d == 0)
            return fp;
        else
            return sfp(d-1,thread.STACK.get(fp+3),thread.STACK.get(fp+4));
    }

    void handleCONST(int a1)
    {
        thread.STACK.set(thread.TOP + 1, a1);
        thread.TOP++;
        thread.PC++;
    }

    void handleLOAD(int k, int d)
    {
        thread.STACK.set(thread.TOP+1,thread.STACK.get(spp(d,thread.PP,thread.FP)+k));
        thread.TOP++;
        thread.PC++;
    }

    void handleSTORE(int k, int d)
    {
        thread.STACK.set(spp(d,thread.PP,thread.FP)+k, thread.STACK.get(thread.TOP));
        thread.TOP--;
        thread.PC++;
    }

    void handleADD()
    {
        thread.STACK.set(thread.TOP -1 , thread.STACK.get(thread.TOP-1) + thread.STACK.get(thread.TOP));
        thread.TOP--;
        thread.PC++;
    }

    void handleSUB()
    {
        thread.STACK.set(thread.TOP -1 , thread.STACK.get(thread.TOP-1) - thread.STACK.get(thread.TOP));
        thread.TOP--;
        thread.PC++;
    }

    void handleMULT()
    {
        thread.STACK.set(thread.TOP -1 , thread.STACK.get(thread.TOP-1) * thread.STACK.get(thread.TOP));
        thread.TOP--;
        thread.PC++;
    }

    void handleDIV()
    {
        thread.STACK.set(thread.TOP -1 , thread.STACK.get(thread.TOP-1) / thread.STACK.get(thread.TOP));
        thread.TOP--;
        thread.PC++;
    }

    void handleLT()
    {
        if (thread.STACK.get(thread.TOP - 1) < thread.STACK.get(thread.TOP))
            thread.STACK.set(thread.TOP - 1,1);
        else
            thread.STACK.set(thread.TOP - 1,0);

        thread.TOP--;
        thread.PC++;
    }

    void handleLTE()
    {
        if (thread.STACK.get(thread.TOP - 1) <= thread.STACK.get(thread.TOP))
            thread.STACK.set(thread.TOP - 1,1);
        else
            thread.STACK.set(thread.TOP - 1,0);

        thread.TOP--;
        thread.PC++;
    }

    void handleGT()
    {
        if (thread.STACK.get(thread.TOP - 1) > thread.STACK.get(thread.TOP))
            thread.STACK.set(thread.TOP - 1,1);
        else
            thread.STACK.set(thread.TOP - 1,0);

        thread.TOP--;
        thread.PC++;
    }

    void handleGTE()
    {
        if (thread.STACK.get(thread.TOP - 1) >= thread.STACK.get(thread.TOP))
            thread.STACK.set(thread.TOP - 1,1);
        else
            thread.STACK.set(thread.TOP - 1,0);

        thread.TOP--;
        thread.PC++;
    }

    void handleEQ()
    {
        if (thread.STACK.get(thread.TOP - 1) == thread.STACK.get(thread.TOP))
            thread.STACK.set(thread.TOP - 1,1);
        else
            thread.STACK.set(thread.TOP - 1,0);

        thread.TOP--;
        thread.PC++;
    }

    void handleNEQ()
    {
        if (thread.STACK.get(thread.TOP - 1) != thread.STACK.get(thread.TOP))
            thread.STACK.set(thread.TOP - 1,1);
        else
            thread.STACK.set(thread.TOP - 1,0);

        thread.TOP--;
        thread.PC++;
    }

    void handleIFZERO(int a1)
    {
        if (thread.STACK.get(thread.TOP) == 0)
            thread.PC = a1;
        else
            thread.PC++;

        thread.TOP--;
    }

    void handleGOTO(int a1)
    {
        thread.PC = a1;
    }

    void handleHALT()
    {
        thread.PC = -1;
    }

    void handleNOP()
    {
        thread.PC++;
    }

    void handlePOP()
    {
        thread.TOP--;
        thread.PC++;
    }

    void handleINVOKE(int n, int p, int d)
    {
        thread.STACK.set(thread.TOP +1,thread.PC+1);
        thread.STACK.set(thread.TOP +2,thread.PP);
        thread.STACK.set(thread.TOP +3,thread.FP);
        thread.STACK.set(thread.TOP +4,spp(d,thread.PP,thread.FP));
        thread.STACK.set(thread.TOP +5,sfp(d,thread.PP,thread.FP));
        thread.PP = thread.TOP - n + 1;
        thread.FP = thread.TOP +1;
        thread.TOP += 5;
        thread.PC = p;
    }

    void handleRETURN()
    {
        int res = thread.STACK.get(thread.TOP);
        thread.TOP = thread.PP;
        thread.PC = thread.STACK.get(thread.FP);
        thread.PP = thread.STACK.get(thread.FP +1);
        thread.FP = thread.STACK.get(thread.FP +2);
        thread.STACK.set(thread.TOP,res);
    }
}
