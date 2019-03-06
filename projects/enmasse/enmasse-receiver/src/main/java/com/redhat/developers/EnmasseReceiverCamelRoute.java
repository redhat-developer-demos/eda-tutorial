package com.redhat.developers;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnmasseReceiverCamelRoute extends RouteBuilder {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void configure() throws Exception {
        from(String.format("amqps:my-topic"))
                .routeId("enmasse-receiver-route")
                .log(LoggingLevel.INFO, "Received Enmasse message ${id}")
                .process(e -> messageRepository.add(e.getIn().getMandatoryBody(String.class)));
    }

}