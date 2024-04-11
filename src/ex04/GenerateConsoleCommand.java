package src.ex04;

import src.ex02.View;
import src.ex03.ViewTable;
import java.io.*;

/**
 * Консольная команда
 * Generate;
 * шаблон Command
 */
public class GenerateConsoleCommand implements ConsoleCommand {
    private View view;

    public GenerateConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'c';
    }

    @Override
    public String toString() {
        return "'c'ount digits";
    }

    @Override
    public void execute() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        System.out.println("Enter a decimal number: ");
        try {
            String number = in.readLine();
            view.viewHeader();
            int hexCount = view.viewCountHexDigits(number);
            int octCount = view.viewCountOctDigits(number);
            ViewTable otherObject = new ViewTable();
            otherObject.setCounts(hexCount, octCount);
            otherObject.viewBody();
            view.viewFooter();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}