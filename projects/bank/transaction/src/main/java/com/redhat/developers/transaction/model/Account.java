package com.redhat.developers.transaction.model;

import com.redhat.developers.transaction.model.event.AccountChangedEvent;
import com.sun.xml.bind.v2.model.core.ID;

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
    private Boolean active;

    protected Account() {
    }

    private Account(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static Account of(AccountNumber accountNumber) {
        checkNotNull(accountNumber);
        return new Account(accountNumber);
    }

    public void accept(AccountChangedEvent event) {
        this.active = event.isActive();
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

    public void setActive(boolean active) {
        this.active = active;
    }
}