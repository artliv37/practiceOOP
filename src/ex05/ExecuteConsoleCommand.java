package src.ex05;

import java.util.concurrent.TimeUnit;
import src.ex02.View;
import src.ex02.ViewResult;
import src.ex04.ConsoleCommand;

/**
 * Консольная команда
 * Execute all threads;
 * шаблон Command
 */

public class ExecuteConsoleCommand implements ConsoleCommand {
    private View view;

    public View getView() {
        return view;
    }

    public View setView(View view) {
        return this.view = view;
    }

    public ExecuteConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'e';
    }

    @Override
    public String toString() {
        return "'e'xecute";
    }

    @Override
    public void execute() {
        CommandQueue queue1 = new CommandQueue();

        AvgCommand avgCommand = new AvgCommand((ViewResult) view);
        System.out.println("Execute all threads...");

        queue1.put(avgCommand);

        try {
            while (avgCommand.running()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            queue1.shutdown();

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println(e);
        }
        System.out.println("All done.");
    }
}