package src.ex01;

import java.io.*;

/**
 * Содержит реализацию методов для вычисления и отображения результатов.
 */

public class Calc {
    private static final String FNAME = "Item2d.bin";
    private Item2d result;

    public Calc() {
        result = new Item2d();
    }

    public void setResult(Item2d result) {
        this.result = result;
    }

    public Item2d getResult() {
        return result;
    }

    public int countHexDigits(String number) {
        String numberString = String.valueOf(number);
        int hexCount = 0;
        for (int i = 0; i < numberString.length(); i++) {
            char digit = numberString.charAt(i);
            if (digit >= 'A' && digit <= 'F')
                hexCount++;
            if (digit >= '0' && digit <= '9')
                hexCount++;
        }
        return hexCount;
    }

    public int countOctDigits(String number) {
        String numberString = String.valueOf(number);
        int octCount = 0;
        for (int i = 0; i < numberString.length(); i++) {
            char digit = numberString.charAt(i);
            if (digit >= '0' && digit <= '7')
                octCount++;
        }
        return octCount;
    }

    public void save() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(result);
        os.flush();
        os.close();
    }

    public void restore() throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        result = (Item2d) is.readObject();
        is.close();
    }
}