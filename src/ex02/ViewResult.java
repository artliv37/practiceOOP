package src.ex02;

import java.io.*;
import java.util.ArrayList;
import src.ex01.Item2d;

/**
 * ConcreteProduct
 * (Шаблон проектирования
 * Factory Method)<br>
 * Вычисление функции,
 * сохранение и отображение
 * результатов
 * 
 * @see View
 */

public class ViewResult implements View {

    private static final String FNAME = "items.bin";
    private static final int DEFAULT_NUM = 10;
    private ArrayList<Item2d> items = new ArrayList<Item2d>();

    public ViewResult() {
        this(DEFAULT_NUM);
    }

    public ViewResult(int n) {
        for (int ctr = 0; ctr < n; ctr++) {
            items.add(new Item2d());
        }
    }

    public ArrayList<Item2d> getItems() {
        return items;
    }

    public int viewCountHexDigits(String number) {
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

    public int viewCountOctDigits(String number) {
        String numberString = String.valueOf(number);
        int octCount = 0;
        for (int i = 0; i < numberString.length(); i++) {
            char digit = numberString.charAt(i);
            if (digit >= '0' && digit <= '7')
                octCount++;
        }
        return octCount;
    }

    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Item2d>) is.readObject();
        is.close();
    }

    @Override
    public void viewHeader() {
        System.out.println();
        System.out.println("Results:");
    }

    @Override
    public void viewBody() {
        System.out.println();
    }

    @Override
    public void viewFooter() {
        System.out.println("End.");
        System.out.println();
    }

    @Override
    public void viewShow() {
        viewBody();
        viewFooter();
    }
}