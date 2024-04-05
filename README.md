# Завдання 1

### Код:

```java
class app {

    public static void main(String[] args) {
        String Greeting = "Hello World!";
        System.out.println(Greeting);
    }
}
```

### Результати:

![](/screens/task1.png)

# Завдання 2

### Код

```java
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
```

```java
package src.ex01;

import java.io.Serializable;

/**
 * Хранит исходные данные и результат вычислений.
 */

public class Item2d implements Serializable {
    private int hexCount;
    private int octCount;
    private static final long serialVersionUID = 1L;

    public Item2d() {
        hexCount = 0;
        octCount = 0;
    }

    public Item2d(int hexCount, int octCount) {
        this.hexCount = hexCount;
        this.octCount = octCount;
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
        return "Hex Count: " + hexCount + ", Oct Count: " + octCount;
    }
}
```

```java
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
```

```java
package Test.ex01;

import org.junit.Test;
import src.ex01.*;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

/**
 * Выполняет тестирование разработанных классов.
 */

public class MainTest {

    @Test
    public void testCalc() {
        Calc calc = new Calc();
        assertEquals(0, 5, calc.countHexDigits("12345"));
        assertEquals(1, 4, calc.countHexDigits("1234F"));
        assertEquals(1, 2, calc.countHexDigits("1A2"));
        assertEquals(0, 5, calc.countOctDigits("12345"));
        assertEquals(16, 8, calc.countOctDigits("0123456789ABCDEF"));
        assertEquals(0, 3, calc.countOctDigits("123"));
    }

    @Test
    public void testRestore() {
        Calc calc = new Calc();
        Item2d result1 = new Item2d(2, 3);
        calc.setResult(result1);
        try {
            calc.save();
            calc.restore();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        Item2d result2 = calc.getResult();
        assertEquals(result1.getHexCount(), result2.getHexCount());
        assertEquals(result1.getOctCount(), result2.getOctCount());
    }
}
```

### Результати:

![](/screens/task2.png)

### Результати тестування:

![](/screens/task2Test.png)

# Завдання 3

### Код

```java
package src.ex02;

import java.io.*;

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
                        view.viewCountHexDigits(number);
                        view.viewCountOctDigits(number);
                        view.viewShow();
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
```

```java
package src.ex02;

import java.io.IOException;

/**
 * Product
 * (шаблон проектирования
 * Factory Method)<br>
 * Интерфейс "фабрикуемых"
 * объектов<br>
 * Объявляет методы
 * отображения объектов
 */

public interface View {
    public void viewHeader();

    public void viewBody();

    public void viewFooter();

    public void viewShow();

    public int viewCountHexDigits(String number);

    public int viewCountOctDigits(String number);

    public void viewSave() throws IOException;

    public void viewRestore() throws Exception;
}
```

```java
package src.ex02;

/**
 * Creator
 * (шаблон проектирования
 * Factory Method)<br>
 * Объявляет метод,
 * "фабрикующий" объекты
 * 
 * @see Viewable#getView()
 */

public interface Viewable {
    public View getView();
}
```

```java
package src.ex02;

/**
 * ConcreteCreator
 * (шаблон проектирования
 * Factory Method)<br>
 * Объявляет метод,
 * "фабрикующий" объекты
 * 
 * @see Viewable
 * @see ViewableResult#getView()
 */

public class ViewableResult implements Viewable {
    @Override
    public View getView() {
        return new ViewResult();
    }
}
```

```java
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
        System.out.println("Hex Count: " + hexCount);
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
        System.out.println("Oct Count: " + octCount);
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
```

### Результати:

![](/screens/task3.png)

### Результати тестування:

![](/screens/task3Test.png)

# Завдання 4

### Код

```java
package src.ex03;

import java.io.IOException;

import src.ex02.View;

/**
 * Вычисление и отображение результатов<br>
 * Содержит реализацию статического метода main()
 * 
 * @see Main#main
 */

public class Main extends src.ex02.Main {
    public Main(View view) {
        super(view);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}
```

```java
package src.ex03;

import src.ex02.ViewableResult;
import src.ex02.View;

/**
 * ConcreteCreator
 * (шаблон проектирования
 * Factory Method)<br>
 * Объявляет метод,
 * "фабрикующий" объекты
 * 
 * @see ViewableResult
 * @see ViewableTable#getView()
 */

public class ViewableTable extends ViewableResult {
    @Override
    public View getView() {
        return new ViewTable();
    }
}
```

```java
package src.ex03;

import java.util.Formatter;
import src.ex02.ViewResult;

/**
 * ConcreteProduct (шаблон проектування Factory Method)<br>
 * Вивід у вигляді таблиці
 * 
 */

public class ViewTable extends ViewResult {
    private static final int DEFAULT_WIDTH = 20;

    private int width;

    public ViewTable() {
        width = DEFAULT_WIDTH;
    }

    public ViewTable(int width) {
        super();
        this.width = width;
    }

    public ViewTable(int width, int n) {
        super(n);
        this.width = width;
    }

    public int setWidth(int width) {
        return this.width = width;
    }

    public int getWidth() {
        return width;
    }

    private void outLine() {
        for (int i = width; i > 0; i--) {
            System.out.print('-');
        }
    }

    private void outLineLn() {
        outLine();
        System.out.println();
    }

    private void outHeader() {
        System.out.println();
        Formatter fmt = new Formatter();
        fmt.format("%-" + ((width - 3) / 2) + "s | %-" + ((width - 3) / 2) + "s%n", "hexCount", "octCount");
        System.out.print(fmt);
    }

    private void outBody(int hexCount, int octCount) {
        String format = "%-" + ((width - 3) / 2) + "d | %-" + ((width - 3) / 2) + "d%n";
        System.out.printf(format, hexCount, octCount);
    }

    @Override
    public void viewHeader() {
        outHeader();
        outLineLn();
    }

    private int hexCount;
    private int octCount;

    public void setCounts(int hexCount, int octCount) {
        this.hexCount = hexCount;
        this.octCount = octCount;
    }

    public void viewBody() {
        outBody(hexCount, octCount);
    }

    @Override
    public void viewFooter() {
        outLineLn();
        System.out.println();
    }
}
```

### Результати:

![](/screens/task4.png)

### Результати тестування:

![](/screens/task4Test.png)

# Завдання 5

### Код

```java

```

```java

```

```java

```

### Результати:

![]()

### Результати тестування:

![]()
