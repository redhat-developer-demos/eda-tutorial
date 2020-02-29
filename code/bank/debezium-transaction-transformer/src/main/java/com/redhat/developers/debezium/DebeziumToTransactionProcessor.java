package com.redhat.developers.debezium;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONObject;

public class DebeziumToTransactionProcessor implements Processor {

    private JsonToTransactionTransformer jsonToTransactionTransformer = new JsonToTransactionTransformer();

    @Override
    public void process(Exchange exchange) throws Exception {
        JSONObject body = exchange.getIn().getBody(JSONObject.class);
        JSONObject transformed = jsonToTransactionTransformer.transform(body);
        exchange.getIn().setBody(transformed);
    }

}
