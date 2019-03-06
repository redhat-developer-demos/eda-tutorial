package com.redhat.developers.debezium;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import org.apache.camel.Exchange;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.MockSettings;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class JsonToTransactionTransformerTest {

    @Test
    public void process() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/debezium-message.json");
        String message = new String(ByteStreams.toByteArray(inputStream), Charsets.UTF_8);
        JsonToTransactionTransformer transformer = new JsonToTransactionTransformer();
        JSONObject transformed = transformer.transform(new JSONObject(message));
        assertEquals(transformed.getString("operation"), "C");
        assertEquals(transformed.getBigDecimal("amount"), new BigDecimal("1000.00"));
        System.out.println(transformed);
    }

}