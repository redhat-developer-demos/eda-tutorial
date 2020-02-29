package com.redhat.developers.transaction.persistence;

import com.redhat.developers.transaction.model.Operation;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OperationAttributeConverter implements AttributeConverter<Operation, String> {

    @Override
    public String convertToDatabaseColumn(Operation attribute) {
        return attribute.toDatabaseValue();
    }

    @Override
    public Operation convertToEntityAttribute(String dbData) {
        return Operation.fromDatabaseValue(dbData);
    }

}
