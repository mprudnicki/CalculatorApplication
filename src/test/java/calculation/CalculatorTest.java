package calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorTest {
    @Test
    public void performOperation_BaseValueNullAddOperation_ThrowsNullPointerException() {
        final Calculator calculator = new CalculatorImpl();
        Assertions.assertThrows(NullPointerException.class, () -> {
            BigDecimal value = calculator.performOperation(null, "add");
        });
    }

    @Test
    public void performOperation_OperationNull_ThrowsNullPointerException() {
        final Calculator calculator = new CalculatorImpl();
        Assertions.assertThrows(NullPointerException.class, () -> {
            BigDecimal value = calculator.performOperation(BigDecimal.TEN, null);
        });
    }

    @Test
    public void performOperation_OperationNotInDictionary_ThrowsNullPointerException() {
        final Calculator calculator = new CalculatorImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BigDecimal value = calculator.performOperation(BigDecimal.TEN, "wrong 10");
        });
    }

    @Test
    public void performOperation_OperationIncorrect_ThrowsNullPointerException() {
        final Calculator calculator = new CalculatorImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BigDecimal value = calculator.performOperation(BigDecimal.TEN, "incorrect");
        });
    }

    @Test
    public void performOperation_DivisionByZero_ThrowsArithmeticException() {
        final Calculator calculator = new CalculatorImpl();
        Assertions.assertThrows(ArithmeticException.class, () -> {
            final BigDecimal outcomeValue = calculator.performOperation(BigDecimal.TEN, String.format("divide %d", 0));
        });
    }

    @Test
    public void performOperation_BigIntegerOverflowOperation_ThrowsArithmeticException() {
        final Calculator calculator = new CalculatorImpl();
        Assertions.assertThrows(ArithmeticException.class, () -> {
            final BigDecimal outcomeValue =
                    calculator.performOperation(BigDecimal.valueOf(Integer.MAX_VALUE), String.format("pow %d", Integer.MAX_VALUE));
        });
    }

    @Test
    public void performOperation_AdditionOperations_Success() {
        final Calculator calculator = new CalculatorImpl();
        final BigDecimal givenValue = BigDecimal.TEN;
        Set.of("add", "addition", "plus").forEach(operationType -> {
            assertEquals(
                    givenValue.add(givenValue),
                    calculator.performOperation(BigDecimal.TEN, String.format("%s %d", operationType, givenValue.intValue()))
            );
        });
    }

    @Test
    public void performOperation_SubtractionOperations_Success() {
        final Calculator calculator = new CalculatorImpl();
        final BigDecimal givenValue = BigDecimal.TEN;
        Set.of("subtract", "sub", "minus").forEach(operationType -> {
            assertEquals(
                    givenValue.subtract(givenValue),
                    calculator.performOperation(BigDecimal.TEN, String.format("%s %d", operationType, givenValue.intValue()))
            );
        });
    }

    @Test
    public void performOperation_MultiplicationOperations_Success() {
        final Calculator calculator = new CalculatorImpl();
        final BigDecimal givenValue = BigDecimal.TEN;
        Set.of("multiply", "mlt", "times").forEach(operationType -> {
            assertEquals(
                    givenValue.multiply(givenValue),
                    calculator.performOperation(BigDecimal.TEN, String.format("%s %d", operationType, givenValue.intValue()))
            );
        });
    }

    @Test
    public void performOperation_DivisionOperations_Success() {
        final Calculator calculator = new CalculatorImpl();
        final BigDecimal givenValue = BigDecimal.TEN;
        Set.of("divide", "division", "div").forEach(operationType -> {
            assertEquals(
                    givenValue.divide(givenValue, 3, RoundingMode.HALF_UP),
                    calculator.performOperation(BigDecimal.TEN, String.format("%s %d", operationType, givenValue.intValue()))
            );
        });
    }

    @Test
    public void performOperation_PowerOperations_Success() {
        final Calculator calculator = new CalculatorImpl();
        final BigDecimal givenValue = BigDecimal.TEN;
        Set.of("power", "pow").forEach(operationType -> {
            assertEquals(
                    givenValue.pow(givenValue.intValueExact(), MathContext.DECIMAL64),
                    calculator.performOperation(BigDecimal.TEN, String.format("%s %d", operationType, givenValue.intValue()))
            );
        });
    }
}
