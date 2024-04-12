package fr.hetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdditionTest {

    @Test
    void testExecutePositiveNumbers() {
        Addition addition = new Addition();
        int result = addition.execute(10, 5);
        Assertions.assertEquals(15, result, "10 + 5 should equal 15");
    }

    @Test
    void testExecuteNegativeNumbers() {
        Addition addition = new Addition();
        int result = addition.execute(-5, -10);
        Assertions.assertEquals(-15, result, "-5 + (-10) should equal -15");
    }

    @Test
    void testExecuteWithZero() {
        Addition addition = new Addition();
        int result = addition.execute(0, 10);
        Assertions.assertEquals(10, result, "0 + 10 should equal 10");
    }
}
