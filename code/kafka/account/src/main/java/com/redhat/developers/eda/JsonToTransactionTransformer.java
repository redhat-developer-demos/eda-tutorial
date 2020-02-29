package com.redhat.developers.eda;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class JsonToTransactionTransformer {

    private Jsonb jsonb = JsonbBuilder.create();

    @Incoming("transactions")
    @Outgoing("internal")
    public Flowable<Transaction> process(Flowable<String> value) {
        return value.map(s -> jsonb.fromJson(s, Transaction.class));
    }

}
