package com.redhat.developers.transaction.model.repository;

import com.redhat.developers.transaction.model.Account;
import com.redhat.developers.transaction.model.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByAccountNumber(AccountNumber accountNumber);

}
