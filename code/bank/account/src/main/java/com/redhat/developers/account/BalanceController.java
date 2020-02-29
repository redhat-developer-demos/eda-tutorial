package com.redhat.developers.account;

import com.redhat.developers.account.model.AccountNumber;
import com.redhat.developers.account.model.Balance;
import com.redhat.developers.account.model.repository.BalanceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class BalanceController {

    private BalanceRepository balanceRepository;

    public BalanceController(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    @RequestMapping("/{accountNumber}/balance")
    public Balance balance(@PathVariable("accountNumber") int accountNumber) {
        return balanceRepository.findByAccountNumber(AccountNumber.of(accountNumber))
                .orElseThrow(BalanceNotFoundException::new);
    }

}

@ResponseStatus(HttpStatus.NOT_FOUND)
class BalanceNotFoundException extends RuntimeException {

    public BalanceNotFoundException() {
        super("Balance not found or Account does not exist.");
    }

}