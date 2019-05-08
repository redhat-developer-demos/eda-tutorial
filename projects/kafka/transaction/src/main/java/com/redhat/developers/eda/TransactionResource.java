package com.redhat.developers.eda;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transaction")
public class TransactionResource {

    private Jsonb jsonb;

    @Inject
    @Stream("internal")
    Emitter<Transaction> emitter;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transaction(Transaction transaction) {
        emitter.send(transaction);
        return Response.accepted(transaction).build();
    }

}