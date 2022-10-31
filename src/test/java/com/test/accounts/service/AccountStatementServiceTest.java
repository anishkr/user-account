package com.test.accounts.service;

import com.test.accounts.dto.AccountStatementDTO;
import com.test.accounts.entity.Account;
import com.test.accounts.exception.AccountStatementException;
import com.test.accounts.model.request.SearchRequest;
import com.test.accounts.repository.AccountRepository;
import com.test.accounts.service.impl.AccountStatementServiceImpl;
import com.test.accounts.util.Convert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AccountStatementServiceTest {

    private static AccountStatementService accountStatementService;

    @BeforeAll
    public static void init() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        accountStatementService = new AccountStatementServiceImpl(accountRepository);
        Account dummyAccount = new Account(1, "savings", "123456", "100", "02.02.2020", 1);
        List<Account> dummyACcounts = List.of(dummyAccount);
        when(accountRepository.findAccountByAccountNumber(any())).thenReturn(Optional.of(dummyACcounts));
    }

    @Test
    void testGetAccountStatementsByAccountNumber() throws AccountStatementException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setAccountNumber("123");
        searchRequest.setUser(false);
        AccountStatementDTO accountStatementDTO = accountStatementService.getAccountStatements(searchRequest);
        assertEquals(Convert.getSHA256Hash("123456"), accountStatementDTO.getHashedAccountNumber());
        assertEquals(1, accountStatementDTO.getStatements().get(0).getStatementId());
    }

    @Test
    void testGetAccountStatementsByAccountNumberWithDates() throws AccountStatementException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setAccountNumber("123");
        searchRequest.setUser(false);
        searchRequest.setStartDate("01.01.2020");
        searchRequest.setEndDate("03.03.2020");
        AccountStatementDTO accountStatementDTO = accountStatementService.getAccountStatements(searchRequest);
        assertEquals(1, accountStatementDTO.getStatements().size());
        assertEquals(Convert.getSHA256Hash("123456"), accountStatementDTO.getHashedAccountNumber());
        assertEquals(1, accountStatementDTO.getStatements().get(0).getStatementId());
    }

    @Test
    void testGetAccountStatementsByAccountNumberWithAmounts() throws AccountStatementException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setAccountNumber("123");
        searchRequest.setUser(false);
        searchRequest.setStartAmount("95.00");
        searchRequest.setEndAmount("105.00");
        AccountStatementDTO accountStatementDTO = accountStatementService.getAccountStatements(searchRequest);
        assertEquals(1, accountStatementDTO.getStatements().size());
        assertEquals(Convert.getSHA256Hash("123456"), accountStatementDTO.getHashedAccountNumber());
        assertEquals(1, accountStatementDTO.getStatements().get(0).getStatementId());
    }

    @Test
    void testGetAccountStatementsByAccountNumberWithUserRequest() throws AccountStatementException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setAccountNumber("123");
        searchRequest.setUser(true);
        AccountStatementDTO accountStatementDTO = accountStatementService.getAccountStatements(searchRequest);
        assertEquals(0, accountStatementDTO.getStatements().size());
        assertEquals(Convert.getSHA256Hash("123456"), accountStatementDTO.getHashedAccountNumber());
    }
}
