package src;

import java.io.*;

public class Test {
    /**
     * Клас для тестування правильності обчислення та серіалізації/десеріалізації
     * результатів підрахунку кількості 16-річних та 8-річних цифр у заданому
     * значенні десяткового числа.
     */
    public static void main(String[] args) {
        Result data = new Result(987654321, 3, 7);

        System.out.println("Початкові дані:");
        System.out.println(data);

        testCalculations(data);

        testSerialization(data);
    }

    public static void testCalculations(Result data) {
        int decimalNumber = data.getDecimalNumber();
        int hexCount = data.getHexCount();
        int octCount = data.getOctCount();

        assert decimalNumber == 987654321;
        assert hexCount == 3;
        assert octCount == 7;

        System.out.println("Тест коректності обчислень пройдено успішно.");
    }

    public static void testSerialization(Result data) {
        Demo.saveObjectState(data, "test.ser");

        Result restoredData = Test.restoreObjectState("test.ser");

        assert restoredData != null;
        assert restoredData.getDecimalNumber() == data.getDecimalNumber();
        assert restoredData.getHexCount() == data.getHexCount();
        assert restoredData.getOctCount() == data.getOctCount();

        System.out.println("Тест серіалізації та десеріалізації пройдено успішно.");
    }

    public static Result restoreObjectState(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = inputStream.readObject();
            if (obj instanceof Result) {
                return (Result) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}