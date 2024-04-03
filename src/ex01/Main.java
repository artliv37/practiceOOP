package src.ex01;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Вычисление и отображение результатов.<br>
 * Содержит реализацию статического метода main().
 * 
 * @see Main#main
 */

public class Main {
    private Calc calc = new Calc();

    private void menu() {
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
                    System.out.println("Enter a decimal number: ");
                    try {
                        String number = in.readLine();
                        int hexCount = calc.countHexDigits(number);
                        int octCount = calc.countOctDigits(number);
                        calc.getResult().setHexCount(hexCount);
                        calc.getResult().setOctCount(octCount);
                        System.out.println(calc.getResult());
                    } catch (IOException | NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 's':
                    System.out.println("Save current.");
                    try {
                        calc.save();
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    break;
                case 'r':
                    System.out.println("Restore last saved.");
                    try {
                        calc.restore();
                    } catch (Exception e) {
                        System.out.println("Serialization error: " + e);
                    }
                    System.out.println(calc.getResult());
                    break;
                default:
                    System.out.print("Wrong command. ");
            }
        } while (s.charAt(0) != 'q');
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }
}