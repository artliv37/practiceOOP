package src.ex05;

import java.util.concurrent.TimeUnit;
import src.ex02.ViewResult;
import src.ex03.ViewTable;
import src.ex04.Command;

/**
 * Задача, используемая
 * обработчиком потока;
 * шаблон Worker Thread
 */

public class AvgCommand implements Command {
    private double result = 0.0;
    private int progress = 0;
    private ViewResult viewResult;

    ViewTable view = new ViewTable();

    public ViewResult getViewResult() {
        return viewResult;
    }

    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    public AvgCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    public double getResult() {
        return result;
    }

    public boolean running() {
        return progress < 100;
    }

    @Override
    public void execute() {
        progress = 0;
        System.out.println("Average executed...");
        int idx = 1, size = viewResult.getItems().size();
        progress = idx * 100 / size;
        if (idx++ % (size / 2) == 0) {
            System.out.println("Average " + progress + "%");
        }
        try {
            TimeUnit.MILLISECONDS.sleep(2000 / size);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        view.vres();
        progress = 100;
    }
}