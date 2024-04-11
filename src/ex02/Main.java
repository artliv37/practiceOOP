package src.ex02;

import java.io.*;

import src.ex03.ViewTable;

/**
 * Вычисление и отображение результатов.<br>
 * Содержит реализацию статического метода main()
 * 
 * @see Main#main
 */

public class Main {

    private View view;

    public Main(View view) {
        this.view = view;
    }

    protected void menu() throws IOException {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Enter command...");
            System.out.print("'q'uit, 'c'ount digits, 's'ave, 'r'estore: ");
            try {
                s = in.readLine();
            } catch (IOException e) {
                System.out.println("Error: " + e);
                System.exit(0);
            }
            switch (s.charAt(0)) {
                case 'q':
                    System.out.println("Exit.");
                    break;
                case 'c':
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
                    break;
                case 's':
                    System.out.println("Save current.");
                    try {
                        view.viewSave();
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    break;
                case 'r':
                    System.out.println("Restore last saved.");
                    try {
                        view.viewRestore();
                    } catch (Exception e) {
                        System.out.println("Serialization error: " + e);
                    }
                    view.viewShow();
                    break;
                default:
                    System.out.print("Wrong command. ");
            }
        } while (s.charAt(0) != 'q');
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main(new ViewableResult().getView());
        main.menu();
    }
}