package com.example.demo.rest;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaReceiverCamelRoute extends RouteBuilder {

    private static final String WORKSHOP_USER = System.getenv().getOrDefault("WORKSHOP_USER", "UNKNOWN_USER");

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void configure() throws Exception {
        from(String.format("kafka:my-topic-%s", WORKSHOP_USER))
                .routeId("kafka-receiver-route")
                .log(LoggingLevel.INFO, "Received Kafka message ${id}")
                .process(e -> messageRepository.add(e.getIn().getMandatoryBody(String.class)));
    }

}
