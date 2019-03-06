package com.redhat.developers.account;

import com.redhat.developers.account.model.Account;
import com.redhat.developers.account.model.AccountNumber;
import com.redhat.developers.account.model.Balance;
import com.redhat.developers.account.model.event.AccountChangedEvent;
import com.redhat.developers.account.model.repository.AccountRepository;
import com.redhat.developers.account.model.repository.BalanceRepository;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountRepository accountRepository;

    private CamelContext camelContext;

    public AccountController(AccountRepository accountRepository, CamelContext camelContext) {
        this.accountRepository = accountRepository;
        this.camelContext = camelContext;
    }

    @RequestMapping("/{accountNumber}")
    public Account account(@PathVariable("accountNumber") int accountNumber) {
        return accountRepository.findByAccountNumber(AccountNumber.of(accountNumber))
                .orElseThrow(AccountNotFoundException::new);
    }

    @RequestMapping("/new/{accountNumber}")
    public Account newAccount(@PathVariable("accountNumber") int accountNumber) {
        return accountRepository.save(Account.of(AccountNumber.of(accountNumber)));
    }

    @RequestMapping("/{accountNumber}/activate")
    public Account activate(@PathVariable("accountNumber") int accountNumber) {
        Account account = accountRepository.findByAccountNumber(AccountNumber.of(accountNumber))
                .orElseThrow(AccountNotFoundException::new);
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.requestBody("direct:account-changed", AccountChangedEvent.of(account.activate()));
        return account;
    }

    @RequestMapping("/{accountNumber}/inactivate")
    public Account inactivate(@PathVariable("accountNumber") int accountNumber) {
        Account account = accountRepository.findByAccountNumber(AccountNumber.of(accountNumber))
                .orElseThrow(AccountNotFoundException::new);
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.requestBody("direct:account-changed", AccountChangedEvent.of(account.inactivate()));
        return account;
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super("Account does not exist.");
    }

}