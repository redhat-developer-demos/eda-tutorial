package com.redhat.developers.transaction;

import com.redhat.developers.transaction.model.AccountNumber;
import com.redhat.developers.transaction.model.Balance;
import com.redhat.developers.transaction.model.Transaction;
import com.redhat.developers.transaction.model.repository.AccountRepository;
import com.redhat.developers.transaction.model.repository.BalanceRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TransactionReceiverRoute extends RouteBuilder {

    private static final String WORKSHOP_USER = System.getenv().getOrDefault("WORKSHOP_USER", "UNKNOWN_USER");

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public void configure() throws Exception {
        from(String.format("kafka:transaction-%s", WORKSHOP_USER))
                .routeId("transaction-receiver-route")
                .log(LoggingLevel.INFO, "Received Transaction ${id}")
                .transacted()
                .process(e -> {
                    String stringValue = e.getIn().getBody(String.class);
                    JSONObject jsonObject = new JSONObject(stringValue);
                    Transaction transaction = Transaction.of(jsonObject);
                    e.getIn().setBody(transaction);
                })
                .process(e -> {
                    Transaction transaction = e.getIn().getBody(Transaction.class);
                    AccountNumber accountNumber = transaction.getAccountNumber();
                    Balance balance = balanceRepository.findByAccountNumber(accountNumber)
                            .orElseGet(() -> balanceRepository.save(Balance.of(accountNumber)));
                    balance.accept(transaction);
                });
    }

}