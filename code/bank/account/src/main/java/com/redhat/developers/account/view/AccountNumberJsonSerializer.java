package com.redhat.developers.account.view;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.redhat.developers.account.model.AccountNumber;

import java.io.IOException;

public class AccountNumberJsonSerializer extends JsonSerializer<AccountNumber> {

    @Override
    public void serialize(AccountNumber accountNumber, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(String.format("%s", accountNumber));
    }

}
