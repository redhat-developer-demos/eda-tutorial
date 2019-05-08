package com.redhat.developers.eda;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.math.BigDecimal;
import java.util.Objects;

@RegisterForReflection
public class Account {

    private int id;

    private BigDecimal amount = new BigDecimal("0.00");

    private Account(int id) {
        this.id = id;
    }

    public static Account of(int id) {
        return new Account(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account other = (Account) obj;
            return Objects.equals(this.id, other.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public void add(Transaction transaction) {
        this.amount = transaction.apply(this.amount);
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
