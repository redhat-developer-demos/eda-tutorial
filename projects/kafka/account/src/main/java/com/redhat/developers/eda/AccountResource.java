package com.redhat.developers.eda;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/account")
public class AccountResource {

    @Inject
    AccountRepository accountRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> transaction() {
        return accountRepository.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account transaction(@PathParam("id") Integer id) {
        return accountRepository.findById(id);
    }

}