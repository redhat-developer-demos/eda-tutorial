package com.redhat.developers.transaction.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {
    CREDIT("C", BigDecimal::add),
    DEBIT("D", BigDecimal::subtract);

    private static final Map<String, Operation> valueMap = Stream.of(values())
            .collect(Collectors.toMap(Operation::toStringValue, Function.identity()));

    private final String stringValue;

    private final BinaryOperator<BigDecimal> operator;

    Operation(String stringValue, BinaryOperator<BigDecimal> operator) {
        this.stringValue = stringValue;
        this.operator = operator;
    }

    public static Operation fromString(String databaseValue) {
        return valueMap.get(databaseValue);
    }

    public String toStringValue() {
        return stringValue;
    }

    public BigDecimal apply(BigDecimal x, BigDecimal y) {
        return operator.apply(x, y);
    }

}
