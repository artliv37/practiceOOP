package src;

public class CalculationFactory {
    public Calculation createCalculation(String type) {
        if (type.equalsIgnoreCase("hex")) {
            return new HexCalculation();
        } else if (type.equalsIgnoreCase("oct")) {
            return new OctCalculation();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        CalculationFactory factory = new CalculationFactory();
        Calculation hexCalculation = factory.createCalculation("hex");
        Calculation octCalculation = factory.createCalculation("oct");
        CalculationResult hexCalculationR = (CalculationResult) factory.createCalculation("hex");
        CalculationResult octCalculationR = (CalculationResult) factory.createCalculation("oct");

        hexCalculation.performCalculation();
        hexCalculationR.displayResult();

        octCalculation.performCalculation();
        octCalculationR.displayResult();
    }
}