package com.redhat.developers.transaction;

import com.redhat.developers.transaction.model.AccountNumber;
import com.redhat.developers.transaction.model.Transaction;
import com.redhat.developers.transaction.model.repository.AccountRepository;
import com.redhat.developers.transaction.model.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class TransactionController {

    private AccountRepository accountRepository;

    private TransactionRepository transactionRepository;

    public TransactionController(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @RequestMapping("/{accountNumber}/credit/{amount}")
    public ResponseEntity<Transaction> credit(@PathVariable("accountNumber") Integer account, @PathVariable("amount") BigDecimal amount) {
        if (!accountRepository.existsAccountByAccountNumberAndActiveIsTrue(AccountNumber.of(account))) {
            throw new AccountNumberDoesNotExistException();
        }
        Transaction transaction = transactionRepository.save(Transaction.credit(AccountNumber.of(account), amount));
        return ResponseEntity.ok(transaction);
    }

    @RequestMapping("/{accountNumber}/debit/{amount}")
    public ResponseEntity<Transaction> debit(@PathVariable("accountNumber") Integer account, @PathVariable("amount") BigDecimal amount) {
        if (!accountRepository.existsAccountByAccountNumberAndActiveIsTrue(AccountNumber.of(account))) {
            throw new AccountNumberDoesNotExistException();
        }
        Transaction transaction = transactionRepository.save(Transaction.debit(AccountNumber.of(account), amount));
        return ResponseEntity.ok(transaction);
    }

    @RequestMapping("/{account}")
    public List<Transaction> find(@PathVariable("account") Integer account) {
        return transactionRepository.findTop10ByAccountNumberOrderByTime(AccountNumber.of(account));
    }

}

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class AccountNumberDoesNotExistException extends RuntimeException {

    AccountNumberDoesNotExistException() {
        super("AccountNumber does not exist.");
    }

}