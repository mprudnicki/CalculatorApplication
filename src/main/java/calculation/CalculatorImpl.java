package calculation;

import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
@NoArgsConstructor
public class CalculatorImpl implements Calculator {
    @Override
    public BigDecimal performOperation(BigDecimal baseValue, String operation) {
        Objects.requireNonNull(baseValue, "Base value cannot be null.");
        Objects.requireNonNull(operation, "Operation value cannot be null.");
        final String operationType = operation.split(" ")[0];
        if(operationType.equals("apply")) return baseValue;
        if(!isOperationValid(operation)) throw new IllegalArgumentException("Operation not supported or invalid.");
        final BigDecimal operationValue = new BigDecimal(operation.split(" ")[1]);
        return Operation.of(operationType).apply(baseValue, operationValue);
    }

    @Override
    public boolean isOperationValid(String operation) {
        String operationType = operation.split(" ")[0];
        return operation.matches("\\w+ [-+]?\\d*\\.?\\d*")
                && (operationType.equals("apply") || Operation.of(operationType) != null);
    }
}
