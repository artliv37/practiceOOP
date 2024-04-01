package src;

import java.io.*;

public class Demo {
    /**
     * Клас для демонстрації обчислення серіалізації/десеріалізації результатів
     * підрахунку кількості 16-річних та 8-річних цифр у заданому значенні
     * десяткового числа.
     */
    public static void main(String[] args) {
        Calculation data = new Calculation(123456789, 5, 3);

        saveObjectState(data, "data.ser");

        Calculation restoredData = restoreObjectState("data.ser");

        if (restoredData != null) {
            System.out.println("Відновлені дані:");
            System.out.println(restoredData);
        }
    }

    public static void saveObjectState(Object obj, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(obj);
            System.out.println("Стан об'єкта збережено у файл " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Calculation restoreObjectState(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = inputStream.readObject();
            if (obj instanceof Calculation) {
                return (Calculation) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}