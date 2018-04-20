package de.unitrier.st.uap.s18.tram.TRAM;

import java.util.ArrayList;

public class DynamicStack extends ArrayList<Integer> {

    @Override
    public Integer set(int i, Integer integer) {
        try {return super.set(i, integer);}
        catch (IndexOutOfBoundsException e)
        {
            super.add(i,integer);
            return integer;
        }
    }
}
