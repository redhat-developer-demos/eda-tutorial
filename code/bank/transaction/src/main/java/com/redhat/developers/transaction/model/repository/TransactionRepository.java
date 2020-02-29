package com.redhat.developers.transaction.model.repository;

import com.redhat.developers.transaction.model.AccountNumber;
import com.redhat.developers.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    public List<Transaction> findTop10ByAccountNumberOrderByTime(AccountNumber accountNumber);
}
