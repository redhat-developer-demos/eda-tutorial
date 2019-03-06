package com.redhat.developers.account.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private AccountNumber accountNumber;

    @NotNull
    private BigDecimal amount = new BigDecimal("0.00");

    protected Balance() {
    }

    private Balance(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static Balance of(AccountNumber accountNumber) {
        checkNotNull(accountNumber);
        return new Balance(accountNumber);
    }

    public void accept(Transaction transaction) {
        this.amount = transaction.getOperation().apply(amount, transaction.getAmount());
    }

    public Long getId() {
        return id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
