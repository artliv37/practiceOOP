package src;

public class App {
    /**
     * Клас для підрахунку кількості 16-річних та 8-річних цифр у заданому значенні
     * десяткового числа.
     * 
     * @param args
     */
    public static void main(String[] args) {
        int decimalNumber = 123456789;

        String decimalString = Integer.toString(decimalNumber);

        int hexCount = 0;
        int octCount = 0;

        for (int i = 0; i < decimalString.length(); i++) {
            char c = decimalString.charAt(i);
            if (Character.isDigit(c)) {
                int digit = Character.digit(c, 10);
                if (digit >= 8) {
                    octCount++;
                }
                if (digit >= 10) {
                    hexCount++;
                }
            }
        }

        System.out.println("Кількість 16-річних цифр: " + hexCount);
        System.out.println("Кількість 8-річних цифр: " + octCount);
    }
}