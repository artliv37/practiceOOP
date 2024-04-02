package src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solver implements Serializable {
    /**
     * Клас для агрегування результатів підрахунку кількості 16-річних та 8-річних
     * цифр у заданому значенні десяткового числа.
     */
    private static final long serialVersionUID = 1L;

    private List<Result> results;

    public Solver() {
        this.results = new ArrayList<>();
    }

    public void addResult(Result result) {
        this.results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }

    public void solveProblem(Result calculationData) {
        int decimalNumber = calculationData.getDecimalNumber();
        int hexCount = calculationData.getHexCount();
        int octCount = calculationData.getOctCount();

        System.out.println("Десяткове число: " + decimalNumber);
        System.out.println("Кількість 16-річних цифр: " + hexCount);
        System.out.println("Кількість 8-річних цифр: " + octCount);

        addResult(calculationData); // Додаємо результат до колекції
    }
}
