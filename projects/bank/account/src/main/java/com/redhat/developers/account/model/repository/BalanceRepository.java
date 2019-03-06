package com.redhat.developers.account.model.repository;

import com.redhat.developers.account.model.AccountNumber;
import com.redhat.developers.account.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    public Optional<Balance> findByAccountNumber(AccountNumber number);

}
