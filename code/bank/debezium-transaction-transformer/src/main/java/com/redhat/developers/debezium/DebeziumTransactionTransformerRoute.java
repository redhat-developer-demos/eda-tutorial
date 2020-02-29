package com.redhat.developers.debezium;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DebeziumTransactionTransformerRoute extends RouteBuilder {

    private static final String WORKSHOP_USER = System.getenv().getOrDefault("WORKSHOP_USER", "UNKNOWN_USER");

    @Override
    public void configure() throws Exception {
        from(String.format("kafka:mysql-%s.transaction.transaction", WORKSHOP_USER))
                .routeId("debezium-transaction-transformer-receiver-route")
                .log(LoggingLevel.INFO, "Received kafka message ${id}")
                .process(new StringToJsonProcessor())
                .filter(e -> "c".equals(e.getIn().getBody(JSONObject.class).getJSONObject("payload").getString("op")))
                .process(new DebeziumToTransactionProcessor())
                .to(String.format("kafka:transaction-%s", WORKSHOP_USER));
    }

}
