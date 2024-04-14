package test.ex05;

import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import src.ex02.ViewResult;
import src.ex05.AvgCommand;
import src.ex05.CommandQueue;

/**
 * Тестирование
 * разработанных классов
 */

public class MainTest {
    private final static int N = 1000;
    private static ViewResult view = new ViewResult(N);
    private static AvgCommand avg1 = new AvgCommand(view);
    private static AvgCommand avg2 = new AvgCommand(view);
    private CommandQueue queue = new CommandQueue();

    @Test
    public void testAvg() {
        avg1.execute();
    }

    @Test
    public void testAvgQueue() {
        queue.put(avg2);
        try {
            while (avg2.running()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            queue.shutdown();
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            fail(e.toString());
        }
    }
}