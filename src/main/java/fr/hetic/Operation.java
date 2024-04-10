package fr.hetic;

@FunctionalInterface
public interface Operation {
    int execute(int num1, int num2);
}