package src;

import java.io.*;
import java.util.List;

public class Demo {
    /**
     * Клас для демонстрації обчислення серіалізації/десеріалізації результатів
     * підрахунку кількості 16-річних та 8-річних цифр у заданому значенні
     * десяткового числа.
     */
    public static void main(String[] args) {
        Solver solver = new Solver();

        Result data1 = new Result(123456789, 5, 3);
        Result data2 = new Result(987654321, 3, 7);

        solver.solveProblem(data1);
        solver.solveProblem(data2);

        List<Result> results = solver.getResults();
        for (Result result : results) {
            System.out.println("Результат: " + result);
        }

        saveObjectState(solver, "solver.ser");

        Solver restoredSolver = restoreObjectState("solver.ser");

        if (restoredSolver != null) {
            System.out.println("Відновлені дані:");
            List<Result> restoredResults = restoredSolver.getResults();
            for (Result result : restoredResults) {
                System.out.println("Результат: " + result);
            }
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

    public static Solver restoreObjectState(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = inputStream.readObject();
            if (obj instanceof Result) {
                return (Solver) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}