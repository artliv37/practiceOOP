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