package com.redhat.developers.account;

import com.redhat.developers.account.model.event.AccountChangedEvent;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AccountChangedEventCamelRoute extends RouteBuilder {

    private static final String HOSTNAME = System.getenv().getOrDefault("HOSTNAME", "unknown");

    @Override
    public void configure() throws Exception {

        from("seda:account-changed")
                .routeId("account-changed-route")
                .log(LoggingLevel.INFO, String.format("Sending AccountChangedEvent from %s: ${id}", HOSTNAME))
                .process(e -> {
                    AccountChangedEvent event = e.getIn().getBody(AccountChangedEvent.class);
                    e.getOut().setBody(event.toJsonString());
                })
                .to("amqps:account-topic");
    }

}