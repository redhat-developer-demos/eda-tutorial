package com.redhat.developers;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EnmasseSenderCamelRoute extends RouteBuilder {

    private static final String RESPONSE_STRING_FORMAT = "message from '%s': %s";

    private static final String HOSTNAME = System.getenv().getOrDefault("HOSTNAME", "unknown");

    private int count = 0;

    @Override
    public void configure() throws Exception {

        from("timer://simple?period=3s")
                .routeId("enmasse-sender-route")
                .log(LoggingLevel.INFO, String.format("recommendation request from %s: ${id}", HOSTNAME))
                .process(e -> e.getOut().setBody(String.format(RESPONSE_STRING_FORMAT, HOSTNAME, count++)))
                .to("amqps:my-topic");
    }

}