package src;

public class HexCalculation implements Calculation, CalculationResult {
    private int result;

    @Override
    public void performCalculation() {
    }

    @Override
    public void displayResult() {
        System.out.println("Hex Calculation Result: " + Integer.toHexString(result));
    }
}