package com.redhat.developers.transaction.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.google.common.base.Preconditions.checkNotNull;

public class Transaction implements Serializable {

    private final long id;

    private final AccountNumber accountNumber;

    private final LocalDateTime time;

    private final Operation operation;

    private final BigDecimal amount;

    public Transaction(long id, AccountNumber accountNumber, LocalDateTime time, Operation operation, BigDecimal amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.time = time;
        this.operation = operation;
        this.amount = amount;
    }

    public static Transaction of(JSONObject jsonObject) {
        checkNotNull(jsonObject);
        return new Transaction(
                jsonObject.getLong("id"),
                AccountNumber.of(jsonObject.getInt("account_number")),
                LocalDateTime.ofInstant(Instant.ofEpochMilli(jsonObject.getLong("time")), ZoneId.systemDefault()),
                Operation.fromString(jsonObject.getString("operation")),
                new BigDecimal(jsonObject.getString("amount"))
        );
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
