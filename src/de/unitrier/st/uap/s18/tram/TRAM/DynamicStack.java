/*
 * Nico Feld - 1169233
 */

package de.unitrier.st.uap.s18.tram.TRAM;

import java.util.Arrays;

public class DynamicStack{

    private int[] stack = new int[0];

    public void set(int i, int value) {
        if (i > stack.length - 1) {
            stack = Arrays.copyOf(stack, i+1);
        }
        stack[i] = value;
    }

    public int get(int i) {
        return stack[i];
    }
}
