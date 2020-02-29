package com.redhat.developers.transaction.model.event;

import com.redhat.developers.transaction.model.AccountNumber;
import org.json.JSONObject;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountChangedEvent {

    private final long id;

    private final AccountNumber accountNumber;

    private final boolean active;

    private AccountChangedEvent(long id, AccountNumber accountNumber, boolean active) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.active = active;
    }

    public static AccountChangedEvent of(JSONObject jsonObject) {
        checkNotNull(jsonObject);
        return new AccountChangedEvent(
                jsonObject.getLong("id"),
                AccountNumber.of(jsonObject.getInt("account_number")),
                jsonObject.getBoolean("active")
        );
    }

    public long getId() {
        return id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public boolean isActive() {
        return active;
    }

}
