package com.redhat.developers.eda;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class TransactionToJsonTransformer {

    private Jsonb jsonb = JsonbBuilder.create();

    @Incoming("internal")
    @Outgoing("transactions")
    public String process(Transaction transaction) {
        return jsonb.toJson(transaction);
    }

}
