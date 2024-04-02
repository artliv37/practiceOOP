package src;

import java.io.Serializable;

public class Result implements Serializable {
    /**
     * Клас для результутату підрахунку кількості 16-річних та 8-річних цифр у
     * заданому значенні десяткового числа.
     */
    private static final long serialVersionUID = 1L;

    private int decimalNumber;
    private int hexCount;
    private int octCount;

    public Result(int decimalNumber, int hexCount, int octCount) {
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