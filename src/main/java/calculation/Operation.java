package calculation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
enum Operation implements BiFunction<BigDecimal, BigDecimal, BigDecimal> {
    ADD(List.of("add", "addition", "plus"), BigDecimal::add),
    SUBTRACT(List.of("subtract", "sub", "minus"), BigDecimal::subtract),
    MULTIPLY(List.of("multiply", "mlt", "times"), BigDecimal::multiply),
    DIVIDE(List.of("divide", "division", "div"), (left, right) -> { return left.divide(right, 3, RoundingMode.HALF_UP); }),
    POWER(List.of("power", "pow"), (left, right) -> { return left.pow(right.intValueExact()); });

    @Getter
    private final List<String> types;
    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> function;

    private static final Map<List<String>, Operation> SYMBOLS = Stream.of(values())
            .collect(Collectors.toMap(v -> v.types, v -> v));

    public static Operation of(String operationType) {
        return SYMBOLS.entrySet()
                .stream()
                .filter(v -> v.getKey().contains(operationType))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow(() -> new IllegalArgumentException("Unknown operation symbol"));
    }

    @Override
    public BigDecimal apply(BigDecimal left, BigDecimal right) {
        return function.apply(left, right);
    }
}