package com.redhat.developers.transaction.view;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.redhat.developers.transaction.model.AccountNumber;

import java.io.IOException;

public class AccountNumberJsonDeserializer extends JsonDeserializer<AccountNumber> {

    @Override
    public AccountNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Integer value = jsonParser.readValueAs(Integer.class);
        return AccountNumber.of(value);
    }

}
