package com.redhat.developers.transaction.persistence;

import com.redhat.developers.transaction.model.AccountNumber;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountNumberAttributeConverter implements AttributeConverter<AccountNumber, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountNumber attribute) {
        return attribute.toDatabaseValue();
    }

    @Override
    public AccountNumber convertToEntityAttribute(Integer dbData) {
        return AccountNumber.of(dbData);
    }

}
