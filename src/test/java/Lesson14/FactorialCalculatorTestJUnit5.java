package Lesson14;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FactorialCalculatorTestJUnit5 {

    @Test
    public void testFactorialOfZero() {
        assertEquals(1, FactorialCalculator.factorial(0));
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        assertEquals(120, FactorialCalculator.factorial(5)); // 5! = 120
        assertEquals(720, FactorialCalculator.factorial(6)); // 6! = 720
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.factorial(-1);
        });
        assertEquals("Число должно быть неотрицательным.", exception.getMessage());
    }
}
