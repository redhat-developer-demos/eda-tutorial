package com.redhat.developers.transaction.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private AccountNumber accountNumber;

    @NotNull
    private LocalDateTime time = LocalDateTime.now();

    @NotNull
    @Column(columnDefinition = "CHAR(1)")
    private Operation operation;

    @NotNull
    private BigDecimal amount;

    protected Transaction() {
    }

    private Transaction(@NotNull AccountNumber accountNumber, Operation operation, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
    }

    public static Transaction credit(AccountNumber accountNumber, BigDecimal amount) {
        checkNotNull(accountNumber);
        checkNotNull(amount);
        return new Transaction(accountNumber, Operation.CREDIT, amount.abs());
    }

    public static Transaction debit(AccountNumber accountNumber, BigDecimal amount) {
        checkNotNull(accountNumber);
        checkNotNull(amount);
        return new Transaction(accountNumber, Operation.DEBIT, amount.abs());
    }

    public Long getId() {
        return id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
