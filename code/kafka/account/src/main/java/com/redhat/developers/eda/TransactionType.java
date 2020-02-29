package com.redhat.developers.eda;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

public enum TransactionType {
    CREDIT(BigDecimal::add),
    DEBIT(BigDecimal::subtract);

    private final BinaryOperator<BigDecimal> operator;

    TransactionType(BinaryOperator<BigDecimal> operator) {
        this.operator = operator;
    }

    BigDecimal apply(BigDecimal accountAmount, BigDecimal transactionAmount) {
        return operator.apply(accountAmount, transactionAmount);
    }

}
