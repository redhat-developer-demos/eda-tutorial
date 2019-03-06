package com.example.demo.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class SimpleKafkaSenderRoute extends RouteBuilder {

    private static final String RESPONSE_STRING_FORMAT = "message from '%s': %s";

    private static final String HOSTNAME = System.getenv().getOrDefault("HOSTNAME", "unknown");

    private static final String WORKSHOP_USER = System.getenv().getOrDefault("WORKSHOP_USER", "UNKNOWN_USER");

    private int count = 0;

    @Override
    public void configure() throws Exception {
        from("timer://simple?period=3s")
                .routeId("kafka-sender-route")
                .log(LoggingLevel.INFO, String.format("message from %s: ${id}", HOSTNAME))
                .process(e -> e.getOut().setBody(String.format(RESPONSE_STRING_FORMAT, HOSTNAME, count++)))
                .to(String.format("kafka:my-topic-%s", WORKSHOP_USER));
    }

}
