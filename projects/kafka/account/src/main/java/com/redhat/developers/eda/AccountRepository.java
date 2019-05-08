package com.redhat.developers.eda;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class AccountRepository {

    private final Map<Integer, Account> accounts = new HashMap<>();

    public Account findById(int id) {
        return accounts.computeIfAbsent(id, Account::of);
    }

    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    @Incoming("internal")
    public void processTransaction(Transaction transaction) {
         findById(transaction.getAccount()).add(transaction);
    }

}