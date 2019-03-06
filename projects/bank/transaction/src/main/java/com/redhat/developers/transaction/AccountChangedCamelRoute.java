package com.redhat.developers.transaction;

import com.redhat.developers.transaction.model.Account;
import com.redhat.developers.transaction.model.AccountNumber;
import com.redhat.developers.transaction.model.event.AccountChangedEvent;
import com.redhat.developers.transaction.model.repository.AccountRepository;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountChangedCamelRoute extends RouteBuilder {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void configure() throws Exception {
        from(String.format("amqps:account-topic?subscriptionName=transaction-sub"))
                .routeId("account-changed-route")
                .transacted()
                .log(LoggingLevel.INFO, "Received Account message ${id}")
                .process(e -> {
                    String stringValue = e.getIn().getBody(String.class);
                    JSONObject jsonObject = new JSONObject(stringValue);
                    e.getIn().setBody(AccountChangedEvent.of(jsonObject));
                })
                .process(e -> {
                    AccountChangedEvent event = e.getIn().getBody(AccountChangedEvent.class);
                    AccountNumber accountNumber = event.getAccountNumber();
                    Account account = accountRepository.findByAccountNumber(accountNumber)
                            .orElseGet(() -> accountRepository.save(Account.of(accountNumber)));
                    account.accept(event);
                });
    }

}