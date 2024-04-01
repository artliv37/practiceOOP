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