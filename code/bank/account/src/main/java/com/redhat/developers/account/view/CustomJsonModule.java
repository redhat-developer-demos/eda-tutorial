package com.redhat.developers.account.view;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.redhat.developers.account.model.AccountNumber;
import org.springframework.stereotype.Component;

@Component
public class CustomJsonModule extends SimpleModule {

    public CustomJsonModule() {
        addSerializer(AccountNumber.class, new AccountNumberJsonSerializer());
        addDeserializer(AccountNumber.class, new AccountNumberJsonDeserializer());
    }

}
