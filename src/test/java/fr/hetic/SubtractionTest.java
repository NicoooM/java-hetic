package fr.hetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SubtractionTest {

    @Test
    void testExecutePositiveNumbers() {
        Subtraction subtraction = new Subtraction();
        int result = subtraction.execute(10, 5);
        Assertions.assertEquals(5, result, "10 - 5 should equal 5");
    }

    @Test
    void testExecuteNegativeNumbers() {
        Subtraction subtraction = new Subtraction();
        int result = subtraction.execute(-5, -10);
        Assertions.assertEquals(5, result, "-5 - (-10) should equal 5");
    }

    @Test
    void testExecuteWithZero() {
        Subtraction subtraction = new Subtraction();
        int result = subtraction.execute(0, 10);
        Assertions.assertEquals(-10, result, "0 - 10 should equal -10");
    }
}
