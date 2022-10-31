package com.test.accounts.repository.impl;

import com.test.accounts.entity.Account;
import com.test.accounts.repository.AccountRepository;
import com.test.accounts.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Slf4j
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<List<Account>> findAccountByAccountNumber(String accountNumber) {

        var accountResultSets = jdbcTemplate.queryForList(
                String.format(Constant.FIND_STATEMENTS_BY_ACCOUNT_NUMBER, accountNumber));

        var accounts = accountResultSets.stream().map(result -> new Account(
                (Integer) result.get("id"),
                (String)result.get("accountType"),
                (String) result.get("accountNumber"),
                (String) result.get("amount"),
                (String) result.get("statementDate"),
                (Integer) result.get("statementId")
        )).collect(Collectors.toList());

        return Optional.of(accounts);
    }
}
