# Завдання 1

### Написати просту консольну програму
![](/screens/1.png)

# Завдання 2

### Підрахувати кількість 16-річних та 8-річних цифр у заданому значенні десяткового числа.

```java
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
```

### Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень.

```java
package src;

import java.io.Serializable;

public class Calculation implements Serializable {
    /**
     * Клас для результутату підрахунку кількості 16-річних та 8-річних цифр у
     * заданому значенні десяткового числа.
     */
    private static final long serialVersionUID = 1L;

    private int decimalNumber;
    private int hexCount;
    private int octCount;

    public Calculation(int decimalNumber, int hexCount, int octCount) {
        this.decimalNumber = decimalNumber;
        this.hexCount = hexCount;
        this.octCount = octCount;
    }

    public int getDecimalNumber() {
        return decimalNumber;
    }

    public void setDecimalNumber(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public int getHexCount() {
        return hexCount;
    }

    public void setHexCount(int hexCount) {
        this.hexCount = hexCount;
    }

    public int getOctCount() {
        return octCount;
    }

    public void setOctCount(int octCount) {
        this.octCount = octCount;
    }

    @Override
    public String toString() {
        return "CalculationData{" +
                "decimalNumber=" + decimalNumber +
                ", hexCount=" + hexCount +
                ", octCount=" + octCount +
                '}';
    }
}
```

### Використовуючи агрегування, розробити клас для знаходження рішення задачі.

```java
package src;

import java.io.Serializable;

public class Solver implements Serializable {
    /**
     * Клас для агрегування результатів підрахунку кількості 16-річних та 8-річних
     * цифр у заданому значенні десяткового числа.
     */
    private static final long serialVersionUID = 1L;

    private Calculation calculationData;

    public Solver(Calculation calculationData) {
        this.calculationData = calculationData;
    }

    public void solveProblem() {
        int decimalNumber = calculationData.getDecimalNumber();
        int hexCount = calculationData.getHexCount();
        int octCount = calculationData.getOctCount();

        System.out.println("Десяткове число: " + decimalNumber);
        System.out.println("Кількість 16-річних цифр: " + hexCount);
        System.out.println("Кількість 8-річних цифр: " + octCount);
    }

    public Calculation getCalculationData() {
        return calculationData;
    }

    public void setCalculationData(Calculation calculationData) {
        this.calculationData = calculationData;
    }
}
```

### Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів. 

```java
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
```

### Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації.

```java
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
```

### Результати

![](/screens/App.png)

![](/screens/Demo.png)

![](/screens/Test.png)
