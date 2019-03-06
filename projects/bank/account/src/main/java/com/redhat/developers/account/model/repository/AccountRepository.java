package com.redhat.developers.account.model.repository;

import com.redhat.developers.account.model.Account;
import com.redhat.developers.account.model.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Optional<Account> findByAccountNumber(AccountNumber accountNumber);

}
