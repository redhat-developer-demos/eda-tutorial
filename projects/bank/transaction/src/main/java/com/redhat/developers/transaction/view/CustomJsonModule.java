package com.redhat.developers.transaction.view;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.redhat.developers.transaction.model.AccountNumber;
import org.springframework.stereotype.Component;

@Component
public class CustomJsonModule extends SimpleModule {

    public CustomJsonModule() {
        addSerializer(AccountNumber.class, new AccountNumberJsonSerializer());
        addDeserializer(AccountNumber.class, new AccountNumberJsonDeserializer());
    }

}
