package test.ex02;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import src.ex02.*;

/**
 * Выполняет тестирование
 * разработанных классов.
 * 
 */

public class MainTest {
    @Test
    public void testViewCountHexDigits() {
        ViewResult view = new ViewResult();
        int hexCount = view.viewCountHexDigits("ABC123");
        assertEquals(6, hexCount);
    }

    @Test
    public void testViewCountOctDigits() {
        ViewResult view = new ViewResult();
        int octCount = view.viewCountOctDigits("1234567");
        assertEquals(7, octCount);
    }

    @Test
    public void testRestore() throws Exception {
        ViewResult view1 = new ViewResult(5);
        ViewResult view2 = new ViewResult();
        try {
            view1.viewSave();
            view2.viewRestore();
            assertEquals(view1.getItems().size(), view2.getItems().size());
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
    }
}