package Lesson_7_junit_5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {

    @Test
    public void testFactorialOfZero() {
        assertEquals(1, Factorial.calculateFactorial(0));
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        assertEquals(120, Factorial.calculateFactorial(5)); // 5! = 120
        assertEquals(720, Factorial.calculateFactorial(6)); // 6! = 720
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Factorial.calculateFactorial(-1);
        });
        assertEquals("Число не должно быть отрицательным", exception.getMessage());
    }
}