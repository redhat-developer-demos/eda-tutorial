package com.redhat.developers.account;

import com.redhat.developers.account.model.Account;
import com.redhat.developers.account.model.AccountNumber;
import com.redhat.developers.account.model.Balance;
import com.redhat.developers.account.model.repository.AccountRepository;
import com.redhat.developers.account.model.repository.BalanceRepository;
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

    private BalanceRepository balanceRepository;

    public AccountController(AccountRepository accountRepository, BalanceRepository balanceRepository) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
    }

    @RequestMapping("/{accountNumber}")
    public Account account(@PathVariable("accountNumber") int accountNumber) {
        return accountRepository.findByAccountNumber(AccountNumber.of(accountNumber));
    }

    @RequestMapping("/{accountNumber}/balance")
    public Balance balance(@PathVariable("accountNumber") int accountNumber) {
        return balanceRepository.findByAccountNumber(AccountNumber.of(accountNumber)).orElseThrow(() -> new BalanceNotFoundException());
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class BalanceNotFoundException extends RuntimeException {

    public BalanceNotFoundException() {
        super("Balance not found or Account does not exist.");
    }

}