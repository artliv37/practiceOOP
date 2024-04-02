package src;

public class OctCalculation implements Calculation, CalculationResult {
    private int result;

    @Override
    public void performCalculation() {
    }

    @Override
    public void displayResult() {
        System.out.println("Oct Calculation Result: " + Integer.toOctalString(result));
    }
}