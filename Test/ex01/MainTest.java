package Test.ex01;

import org.junit.Test;
import src.ex01.*;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

/**
 * Выполняет тестирование разработанных классов.
 * 
 */

public class MainTest {

    @Test
    public void testCalc() {
        Calc calc = new Calc();
        assertEquals(0, 5, calc.countHexDigits("12345"));
        assertEquals(1, 4, calc.countHexDigits("1234F"));
        assertEquals(1, 2, calc.countHexDigits("1A2"));
        assertEquals(0, 5, calc.countOctDigits("12345"));
        assertEquals(16, 8, calc.countOctDigits("0123456789ABCDEF"));
        assertEquals(0, 3, calc.countOctDigits("123"));
    }

    @Test
    public void testRestore() {
        Calc calc = new Calc();
        Item2d result1 = new Item2d(2, 3);
        calc.setResult(result1);
        try {
            calc.save();
            calc.restore();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Item2d result2 = calc.getResult();
        assertEquals(result1.getHexCount(), result2.getHexCount());
        assertEquals(result1.getOctCount(), result2.getOctCount());
    }
}