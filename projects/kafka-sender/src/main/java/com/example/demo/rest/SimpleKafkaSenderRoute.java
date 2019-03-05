package com.example.demo.rest;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class SimpleKafkaSenderRoute extends RouteBuilder {

    private static final String RESPONSE_STRING_FORMAT = "recommendation v1 from '%s': %s";

    private static final String HOSTNAME = parseContainerIdFromHostname(
            System.getenv().getOrDefault("HOSTNAME", "unknown")
    );

    static String parseContainerIdFromHostname(String hostname) {
        return hostname.replaceAll("recommendation-v\\d+-", "");
    }

    private int count = 0;

    @Override
    public void configure() throws Exception {
        from("timer://simple?period=3s")
                .routeId("kafka-sender-route")
                .log(LoggingLevel.INFO, String.format("recommendation request from %s: ${id}", HOSTNAME))
                .process(e -> e.getOut().setBody(String.format(RESPONSE_STRING_FORMAT, HOSTNAME, count++)))
//                .setHeader(KafkaConstants.PARTITION_KEY, simple("1"))
                .setHeader(KafkaConstants.KEY, simple("1"))
                .to("kafka:my-topic-user1");
    }

}
