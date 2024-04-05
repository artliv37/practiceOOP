package test.ex03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import src.ex03.*;

/**
 * Выполняет тестирование
 * разработанных классов.
 */

public class MainTest {

    @Test
    public void testCalc() {
        ViewTable tbl = new ViewTable(10, 5);
        assertEquals(10, tbl.getWidth());
        assertEquals(5, tbl.getItems().size());
        tbl.setCounts(16, 8);
        tbl.viewHeader();
        tbl.viewBody();
        tbl.viewFooter();
    }

    @Test
    public void testRestore() {
        ViewTable tbl1 = new ViewTable(10, 1000);
        ViewTable tbl2 = new ViewTable();

        tbl1.setCounts(16, 8);

        try {
            tbl1.viewSave();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tbl2.viewRestore();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(tbl1.getItems().size(), tbl2.getItems().size());
    }
}