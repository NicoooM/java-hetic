package fr.hetic;


public class OperationFactory {
    public static Operation getOperation(String op) throws Exception {
        switch (op) {
            case "+":
                return new Addition();
            case "-":
                return new Subtraction();
            case "*":
                return new Multiplication();
            default:
                throw new Exception("Unknown operation");
        }
    }
}
