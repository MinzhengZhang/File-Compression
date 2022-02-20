import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.DataFormatException;

public class CharCounterTest {

    @Test
    public void testGetCountException(){
      CharCounter cc = new CharCounter();
      Exception e = assertThrows(DataFormatException.class,()->{
          cc.getCount(257);
      });
      assertEquals("input is not a valid character",e.getMessage());

  }

    @Test
    public void testGetCount() throws DataFormatException{
        CharCounter cc = new CharCounter();
        int actual1 = cc.getCount(95);
        int expected1 = 0;
        assertEquals(expected1,actual1);
        cc.add(65);
        int actual2 = cc.getCount(65);
        int expected2 = 1;
        assertEquals(expected2,actual2);

    }

    @Test
   public void  testCountAll() throws IOException {
        CharCounter cc = new CharCounter();
        InputStream ins = new ByteArrayInputStream("teststring".getBytes("UTF-8"));
        int actual = cc.countAll(ins);
        int expected = 10;
        assertEquals(expected,actual);

    }

    @Test
    public void testAdd() throws DataFormatException {
        CharCounter cc = new CharCounter();
        cc.add(95);
        int actual1 = cc.getCount(95);
        int expected1 = 1;
        assertEquals(actual1,expected1);
        cc.add(95);
        int actual2 = cc.getCount(95);
        int expected2 = 2;
        assertEquals(actual2,expected2);
    }

    @Test
    public void testSet() throws DataFormatException {
        CharCounter cc = new CharCounter();
        cc.add(95);
        cc.set(95,40);
        int expected = 40;
        int actual = cc.getCount(95);
        assertEquals(actual,expected);
    }

    @Test
    public void testClear() throws DataFormatException {
        CharCounter cc = new CharCounter();
        cc.add(95);
        cc.clear();
        int expected = 0;
        int actual = cc.getCount(95);
        assertEquals(actual,expected);
    }

    @Test
    public void testGetTable(){
        CharCounter cc = new CharCounter();
        cc.add(95);
        Map<Integer, Integer> map= cc.getTable();
       assertEquals(1, map.get(95));
    }
}
