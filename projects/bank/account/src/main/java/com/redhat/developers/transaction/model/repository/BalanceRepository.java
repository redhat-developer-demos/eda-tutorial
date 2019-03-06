package com.redhat.developers.transaction.model.repository;

import com.redhat.developers.transaction.model.AccountNumber;
import com.redhat.developers.transaction.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    public Optional<Balance> findByAccountNumber(AccountNumber number);

}
