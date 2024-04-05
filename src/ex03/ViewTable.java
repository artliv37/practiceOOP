package src.ex03;

import java.util.Formatter;
import src.ex02.ViewResult;

/**
 * ConcreteProduct (шаблон проектування Factory Method)<br>
 * Вивід у вигляді таблиці
 * 
 */

public class ViewTable extends ViewResult {
    private static final int DEFAULT_WIDTH = 20;

    private int width;

    public ViewTable() {
        width = DEFAULT_WIDTH;
    }

    public ViewTable(int width) {
        super();
        this.width = width;
    }

    public ViewTable(int width, int n) {
        super(n);
        this.width = width;
    }

    public int setWidth(int width) {
        return this.width = width;
    }

    public int getWidth() {
        return width;
    }

    private void outLine() {
        for (int i = width; i > 0; i--) {
            System.out.print('-');
        }
    }

    private void outLineLn() {
        outLine();
        System.out.println();
    }

    private void outHeader() {
        System.out.println();
        Formatter fmt = new Formatter();
        fmt.format("%-" + ((width - 3) / 2) + "s | %-" + ((width - 3) / 2) + "s%n", "hexCount", "octCount");
        System.out.print(fmt);
    }

    private void outBody(int hexCount, int octCount) {
        String format = "%-" + ((width - 3) / 2) + "d | %-" + ((width - 3) / 2) + "d%n";
        System.out.printf(format, hexCount, octCount);
    }

    @Override
    public void viewHeader() {
        outHeader();
        outLineLn();
    }

    private int hexCount;
    private int octCount;

    public void setCounts(int hexCount, int octCount) {
        this.hexCount = hexCount;
        this.octCount = octCount;
    }

    public void viewBody() {
        outBody(hexCount, octCount);
    }

    @Override
    public void viewFooter() {
        outLineLn();
        System.out.println();
    }
}