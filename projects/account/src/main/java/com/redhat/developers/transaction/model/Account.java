package com.redhat.developers.transaction.model;

import com.sun.xml.bind.v2.model.core.ID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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