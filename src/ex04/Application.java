package src.ex04;

import src.ex02.View;
import src.ex03.ViewableTable;

/**
 * Формирует и отображает
 * меню; реализует шаблон
 * Singleton
 */

public class Application {
    private static Application instance = new Application();

    private Application() {
    }

    public static Application getInstance() {
        return instance;
    }

    private View view = new ViewableTable().getView();
    private Menu menu = new Menu();

    public void run() {
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.execute();
    }
}