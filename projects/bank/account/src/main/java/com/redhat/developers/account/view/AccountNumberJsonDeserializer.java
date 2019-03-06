package com.redhat.developers.account.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.redhat.developers.account.model.AccountNumber;

import java.io.IOException;

public class AccountNumberJsonDeserializer extends JsonDeserializer<AccountNumber> {

    @Override
    public AccountNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Integer value = jsonParser.readValueAs(Integer.class);
        return AccountNumber.of(value);
    }

}
