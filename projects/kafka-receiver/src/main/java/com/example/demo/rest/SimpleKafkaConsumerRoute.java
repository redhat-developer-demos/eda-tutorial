package com.example.demo.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleKafkaConsumerRoute extends RouteBuilder {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void configure() throws Exception {
        from("kafka:my-topic-user1")
                .routeId("kafka-receiver-route")
                .log(LoggingLevel.INFO, "Received kafka message ${id}")
                .process(e -> messageRepository.add(e.getIn().getMandatoryBody(String.class)));
    }

}
