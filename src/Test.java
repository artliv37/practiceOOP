package src;

public class Test {
    /**
     * Клас для тестування правильності обчислення та серіалізації/десеріалізації
     * результатів підрахунку кількості 16-річних та 8-річних цифр у заданому
     * значенні десяткового числа.
     */
    public static void main(String[] args) {
        Calculation data = new Calculation(987654321, 3, 7);

        System.out.println("Початкові дані:");
        System.out.println(data);

        testCalculations(data);

        testSerialization(data);
    }

    public static void testCalculations(Calculation data) {
        int decimalNumber = data.getDecimalNumber();
        int hexCount = data.getHexCount();
        int octCount = data.getOctCount();

        assert decimalNumber == 987654321;
        assert hexCount == 3;
        assert octCount == 7;

        System.out.println("Тест коректності обчислень пройдено успішно.");
    }

    public static void testSerialization(Calculation data) {
        Demo.saveObjectState(data, "test.ser");

        Calculation restoredData = Demo.restoreObjectState("test.ser");

        assert restoredData != null;
        assert restoredData.getDecimalNumber() == data.getDecimalNumber();
        assert restoredData.getHexCount() == data.getHexCount();
        assert restoredData.getOctCount() == data.getOctCount();

        System.out.println("Тест серіалізації та десеріалізації пройдено успішно.");
    }
}