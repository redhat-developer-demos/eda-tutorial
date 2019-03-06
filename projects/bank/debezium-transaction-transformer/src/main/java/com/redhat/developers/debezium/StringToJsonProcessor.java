package com.redhat.developers.debezium;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

public class StringToJsonProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String stringBody = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(new JSONObject(stringBody));
    }

}
