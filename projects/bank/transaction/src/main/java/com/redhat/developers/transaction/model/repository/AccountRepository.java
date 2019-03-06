package com.redhat.developers.transaction.model.repository;

import com.redhat.developers.transaction.model.Account;
import com.redhat.developers.transaction.model.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    public boolean existsAccountByAccountNumberAndActiveIsTrue(AccountNumber accountNumber);

}
