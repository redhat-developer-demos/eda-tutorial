package com.redhat.developers.transaction.model;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Operation {
    CREDIT("C"),
    DEBIT("D");

    private static final Map<String, Operation> valueMap = Stream.of(values())
            .collect(Collectors.toMap(Operation::toDatabaseValue, Function.identity()));

    private final String databaseValue;

    Operation(String databaseValue) {
        this.databaseValue = databaseValue;
    }

    public static Operation fromDatabaseValue(String databaseValue) {
        return valueMap.get(databaseValue);
    }

    public String toDatabaseValue() {
        return databaseValue;
    }

}
