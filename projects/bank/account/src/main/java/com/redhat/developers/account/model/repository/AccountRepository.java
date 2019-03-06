package com.redhat.developers.account.model.repository;

import com.redhat.developers.account.model.Account;
import com.redhat.developers.account.model.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByAccountNumber(AccountNumber accountNumber);

}
