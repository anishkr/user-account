package com.test.accounts.repository;

import com.test.accounts.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository {
    Optional<List<Account>> findAccountByAccountNumber(String accountNumber);
}
