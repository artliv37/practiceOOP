package src.ex05;

import src.ex02.View;
import src.ex02.ViewableResult;
import src.ex04.GenerateConsoleCommand;
import src.ex04.Menu;
import src.ex04.ViewConsoleCommand;

/**
 * Вычисление и отображение
 * результатов; содержит реализацию
 * статического метода main()
 */

public class Main {
    private View view = new ViewableResult().getView();
    private Menu menu = new Menu();

    public void run() {
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new ExecuteConsoleCommand(view));
        menu.execute();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}