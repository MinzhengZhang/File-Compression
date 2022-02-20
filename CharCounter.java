import java.io.IOException;
import java.io.InputStream;
import java.rmi.NoSuchObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;

public class CharCounter  implements ICharCounter, IHuffConstants{
    private HashMap<Integer,Integer> counter;
    public CharCounter(){
        counter = new HashMap<>();
    }
    /**
     * Returns the count associated with specified character.
     * @param ch is the chunk/character for which count is requested
     * @return count of specified chunk
     * @throws DataFormatException the appropriate exception if ch isn't a valid chunk/character
     */
    @Override
    public int getCount(int ch) throws DataFormatException  {
        if (ch > ALPH_SIZE ){
            throw new DataFormatException("input is not a valid character");
        }
        return counter.getOrDefault(ch,0);
    }
    /**
     * Initialize state by counting bits/chunks in a stream
     * @param stream is source of data
     * @return count of all chunks/read
     * @throws IOException if reading fails
     */
    @Override
    public int countAll(InputStream stream) throws IOException {
        int inbits;
        BitInputStream bits = new BitInputStream(stream);
        int count = 0;
        while((inbits = bits.read(BITS_PER_WORD) )!=-1){
            count +=1;
        }

        return count;
    }

    /**
     * Update state to record one occurrence of specified chunk/character.
     * @param i is the chunk being recorded
     */
    @Override
    public void add(int i) {
        if(counter.containsKey(i)){
            counter.replace(i,counter.get(i)+1);
        } else{
          counter.put(i,1);
        }
    }

    /**
     * Set the value/count associated with a specific character/chunk.
     * @param i is the chunk/character whose count is specified
     * @param value is # occurrences of specified chunk
     */
    @Override
    public void set(int i, int value) {
        counter.put(i,value);
    }
    /**
     * All counts cleared to zero.
     */
    @Override
    public void clear() {
        for (Integer key:counter.keySet()){
            counter.replace(key,0);
        }
    }


    /**
     * @return a map of all characters and their frequency
     */
    @Override
    public Map<Integer, Integer> getTable() {
        return counter;
    }
}
