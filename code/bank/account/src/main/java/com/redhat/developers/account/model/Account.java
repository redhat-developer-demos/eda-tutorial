package com.redhat.developers.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private AccountNumber accountNumber;

    @NotNull
    @Column(columnDefinition = "INT")
    private Boolean active = Boolean.TRUE;

    protected Account() {
    }

    private Account(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static Account of(AccountNumber accountNumber) {
        checkNotNull(accountNumber);
        return new Account(accountNumber);
    }

    public Account activate() {
        this.active = Boolean.TRUE;
        return this;
    }

    public Account inactivate() {
        this.active = Boolean.FALSE;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public Boolean isActive() {
        return active;
    }

}