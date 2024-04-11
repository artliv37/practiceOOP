package src.ex04;

import src.ex02.View;

/**
 * Консольная команда
 * View;
 * шаблон Command
 */

public class ViewConsoleCommand implements ConsoleCommand {
    private View view;

    public ViewConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'v';
    }

    @Override
    public String toString() {
        return "'v'iew";
    }

    @Override
    public void execute() {
        System.out.println("View current.");
        view.viewHeader();
        view.viewBody();
        view.viewFooter();
    }
}