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
package test.ex01;

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
        viewHeader();
        viewBody();
        viewFooter();
    }
}
```

```java
package test.ex02;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import src.ex02.*;

/**
 * Выполняет тестирование
 * разработанных классов.
 */

public class MainTest {
    @Test
    public void testViewCountHexDigits() {
        ViewResult view = new ViewResult();
        int hexCount = view.viewCountHexDigits("ABC123");
        assertEquals(6, hexCount);
    }

    @Test
    public void testViewCountOctDigits() {
        ViewResult view = new ViewResult();
        int octCount = view.viewCountOctDigits("1234567");
        assertEquals(7, octCount);
    }

    @Test
    public void testRestore() throws Exception {
        ViewResult view1 = new ViewResult(5);
        ViewResult view2 = new ViewResult();
        try {
            view1.viewSave();
            view2.viewRestore();
            assertEquals(view1.getItems().size(), view2.getItems().size());
        } catch (IOException | ClassNotFoundException e) {
            e.getMessage();
        }
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

```java
package test.ex03;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import src.ex03.*;

/**
 * Выполняет тестирование
 * разработанных классов.
 */

public class MainTest {

    @Test
    public void testCalc() {
        ViewTable tbl = new ViewTable(10, 5);
        assertEquals(10, tbl.getWidth());
        assertEquals(5, tbl.getItems().size());
        tbl.setCounts(16, 8);
        tbl.viewHeader();
        tbl.viewBody();
        tbl.viewFooter();
    }

    @Test
    public void testRestore() {
        ViewTable tbl1 = new ViewTable(10, 1000);
        ViewTable tbl2 = new ViewTable();

        tbl1.setCounts(16, 8);

        try {
            tbl1.viewSave();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            tbl2.viewRestore();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(tbl1.getItems().size(), tbl2.getItems().size());
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
package src.ex04;

import src.ex02.View;
import src.ex03.ViewableTable;

/**
 * Формирует и отображает
 * меню; реализует шаблон
 * Singleton
 */

public class Application {
    private static Application instance = new Application();

    private Application() {
    }

    public static Application getInstance() {
        return instance;
    }

    private View view = new ViewableTable().getView();
    private Menu menu = new Menu();

    public void run() {
        menu.add(new ViewConsoleCommand(view));
        menu.add(new GenerateConsoleCommand(view));
        menu.add(new SaveConsoleCommand(view));
        menu.add(new RestoreConsoleCommand(view));
        menu.execute();
    }
}
```

```java
package src.ex04;

/**
 * Интерфейс команды
 * или задачи;
 * шаблоны: Command,
 * Worker Thread
 */

public interface Command {
    public void execute();
}
```

```java
package src.ex04;

/**
 * Интерфейс
 * консольной команды;
 * шаблон Command
 */

public interface ConsoleCommand extends Command {
    public char getKey();
}
```

```java
package src.ex04;

import src.ex02.View;
import src.ex03.ViewTable;
import java.io.*;

/**
 * Консольная команда
 * Generate;
 * шаблон Command
 */
public class GenerateConsoleCommand implements ConsoleCommand {
    private View view;

    public GenerateConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'c';
    }

    @Override
    public String toString() {
        return "'c'ount digits";
    }

    @Override
    public void execute() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
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
    }
}
```

```java
package src.ex04;

/**
 * Вычисление и отображение
 * результатов; cодержит реализацию
 * статического метода main()
 * 
 * @see Main#main
 */

public class Main {
    public static void main(String[] args) {
        Application app = Application.getInstance();
        app.run();
    }
}
```

```java
package src.ex04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Макрокоманда
 * (шаблон Command);
 * Коллекция объектов
 * класса ConsoleCommand
 */

public class Menu implements Command {
    private List<ConsoleCommand> menu = new ArrayList<ConsoleCommand>();

    public ConsoleCommand add(ConsoleCommand command) {
        menu.add(command);
        return command;
    }

    @Override
    public String toString() {
        String s = "Enter command...\n";
        for (ConsoleCommand c : menu) {
            s += c + ", ";
        }
        s += "'q'uit: ";
        return s;
    }

    @Override
    public void execute() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        menu: while (true) {
            do {
                System.out.print(this);
                try {
                    s = in.readLine();
                } catch (IOException e) {
                    System.err.println("Error: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            char key = s.charAt(0);
            if (key == 'q') {
                System.out.println("Exit.");
                break menu;
            }
            for (ConsoleCommand c : menu) {
                if (s.charAt(0) == c.getKey()) {
                    c.execute();
                    continue menu;
                }
            }
            System.out.println("Wrong command.");
            continue menu;
        }
    }
}
```

```java
package src.ex04;

import src.ex02.View;

/**
 * Консольная команда
 * Restore;
 * шаблон Command
 */

public class RestoreConsoleCommand implements ConsoleCommand {
    private View view;

    public RestoreConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'r';
    }

    @Override
    public String toString() {
        return "'r'estore";
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Restore last saved.");
        try {
            view.viewRestore();
        } catch (Exception e) {
            System.err.println("Serialization error: " + e);
        }
        System.out.println();
    }
}
```

```java
package src.ex04;

import java.io.IOException;
import src.ex02.View;

/**
 * Консольная команда
 * Save;
 * шаблон Command
 */

public class SaveConsoleCommand implements ConsoleCommand {
    private View view;

    public SaveConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 's';
    }

    @Override
    public String toString() {
        return "'s'ave";
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Save current.");
        try {
            view.viewSave();
        } catch (IOException e) {
            System.err.println("Serialization error: " + e);
        }
        System.out.println();
    }
}
```

```java
package src.ex04;

import src.ex02.View;

/**
 * Консольная команда
 * View;
 * шаблон Command
 */

public class ViewConsoleCommand implements ConsoleCommand {
    private View view;

    public ViewConsoleCommand(View view) {
        this.view = view;
    }

    @Override
    public char getKey() {
        return 'v';
    }

    @Override
    public String toString() {
        return "'v'iew";
    }

    @Override
    public void execute() {
        System.out.println("View current.");
        view.viewHeader();
        view.viewBody();
        view.viewFooter();
    }
}
```

```java
package test.ex04;

import static org.junit.Assert.*;
import org.junit.Test;
import src.ex02.ViewResult;
import src.ex03.ViewTable;
import src.ex04.GenerateConsoleCommand;

public class ChangeConsoleCommandTest {

    @Test
    public void testExecute() {
        ViewTable tbl = new ViewTable(10, 5);
        assertEquals(10, tbl.getWidth());
        assertEquals(5, tbl.getItems().size());
        tbl.setCounts(16, 8);
        tbl.viewHeader();
        tbl.viewBody();
        tbl.viewFooter();
    }

    @Test
    public void testGetKey() {
        GenerateConsoleCommand cmd = new GenerateConsoleCommand(new ViewResult());
        assertEquals('c', cmd.getKey());
    }
}
```

### Результати:

![](/screens/task5.png)

### Результати тестування:

![](/screens/task5Test.png)
