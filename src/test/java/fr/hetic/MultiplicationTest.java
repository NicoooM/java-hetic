package fr.hetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MultiplicationTest {

    @Test
    void testExecutePositiveNumbers() {
        Multiplication multiplication = new Multiplication();
        int result = multiplication.execute(10, 5);
        Assertions.assertEquals(50, result, "10 * 5 should equal 50");
    }

    @Test
    void testExecuteNegativeNumbers() {
        Multiplication multiplication = new Multiplication();
        int result = multiplication.execute(-5, -10);
        Assertions.assertEquals(50, result, "-5 * (-10) should equal 50");
    }

    @Test
    void testExecuteWithZero() {
        Multiplication multiplication = new Multiplication();
        int result = multiplication.execute(0, 10);
        Assertions.assertEquals(0, result, "0 * 10 should equal 0");
    }
}