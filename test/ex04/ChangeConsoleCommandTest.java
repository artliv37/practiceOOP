package Test.ex04;

import static org.junit.Assert.*;
import org.junit.Test;
import src.ex02.ViewResult;
import src.ex03.ViewTable;
import src.ex04.GenerateConsoleCommand;

public class ChangeConsoleCommandTest {

    @Test
    public void testExecute() {
        ViewTable tbl = new ViewTable(10, 5);
        assertEquals(10, tbl.getWidth());
        assertEquals(5, tbl.getItems().size());
        tbl.setCounts(16, 8);
        tbl.viewHeader();
        tbl.viewBody();
        tbl.viewFooter();
    }

    @Test
    public void testGetKey() {
        GenerateConsoleCommand cmd = new GenerateConsoleCommand(new ViewResult());
        assertEquals('c', cmd.getKey());
    }
}