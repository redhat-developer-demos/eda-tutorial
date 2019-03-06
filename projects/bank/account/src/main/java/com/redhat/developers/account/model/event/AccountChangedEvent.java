package com.redhat.developers.account.model.event;

import com.redhat.developers.account.model.Account;
import org.json.JSONObject;

import static com.google.common.base.Preconditions.checkNotNull;

public class AccountChangedEvent {

    private final Account account;

    private AccountChangedEvent(Account account) {
        this.account = account;
    }

    public static AccountChangedEvent of(Account account) {
        checkNotNull(account);
        return new AccountChangedEvent(account);
    }

    public String toJsonString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", account.getId());
        jsonObject.put("account_number", account.getAccountNumber().toDatabaseValue());
        jsonObject.put("active", account.isActive());
        return jsonObject.toString();
    }

}
