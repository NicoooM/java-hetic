package fr.hetic;


public class OperationFactory {
    public static Operation getOperation(String op) {
        if (op == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        switch (op) {
            case "+":
                return (a, b) -> a + b;
            case "-":
                return (a, b) -> a - b;
            case "*":
                return (a, b) -> a * b;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }
}
