package com.redhat.developers.eda;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class KafkaReceiverRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kafka:my-topic")
                .routeId("kafka-receiver-route")
                .log(LoggingLevel.INFO, "Received Kafka message ${id}")
                .process(e -> System.out.println(e.getIn().getMandatoryBody(String.class)));
    }

}
