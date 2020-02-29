package com.redhat.developers.debezium;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.json.simple.JsonObject;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;

public class JsonToTransactionTransformer {

    public JSONObject transform(JSONObject jsonObject) {
        JSONObject after = jsonObject.getJSONObject("payload").getJSONObject("after");
        String amount = after.getString("amount");
        byte[] decode = Base64.getDecoder().decode(amount);
        after.put("amount", new BigDecimal(new BigInteger(decode), 2).toString());
        return after;
    }

}
