package com.test.accounts.service.impl;

import com.test.accounts.dto.AccountStatementDTO;
import com.test.accounts.dto.StatementDTO;
import com.test.accounts.entity.Account;
import com.test.accounts.exception.AccountStatementException;
import com.test.accounts.model.request.SearchRequest;
import com.test.accounts.repository.AccountRepository;
import com.test.accounts.service.AccountStatementService;
import com.test.accounts.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountStatementServiceImpl implements AccountStatementService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountStatementServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountStatementDTO getAccountStatements(SearchRequest searchRequest) throws AccountStatementException {
        var accounts = accountRepository.findAccountByAccountNumber(searchRequest.getAccountNumber());

        if(accounts.isEmpty()) {
            throw new AccountStatementException(AccountStatementException.NOT_FOUND,
                    String.format("Account number %s not found", searchRequest.getAccountNumber()));
        }
        /* This constructing request for User*/
        if(searchRequest.isUser()) {
            searchRequest.setStartDate(Convert.dateToString(LocalDate.now().minusMonths(3)));
            searchRequest.setEndDate(Convert.dateToString(LocalDate.now()));
        }
        return convertAccount(accounts.get(),
                searchRequest.getStartDate(),
                searchRequest.getEndDate(),
                searchRequest.getStartAmount(),
                searchRequest.getEndAmount());
    }

    private AccountStatementDTO convertAccount(List<Account> accounts,
                                               String startDate,
                                               String endDate,
                                               String startAmount,
                                               String endAmount) {
        Stream<StatementDTO> statementDTOStream = accounts.stream().map(account -> new StatementDTO(
                    account.getStatementId(),
                    Float.parseFloat(account.getAmount()),
                    Convert.stringToDate(account.getStatementDate()))
        );
        if(Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            statementDTOStream = filterStatementsBasedOnDates(statementDTOStream, startDate, endDate);
        }
        if(Objects.nonNull(startAmount) && Objects.nonNull(endAmount)) {
            statementDTOStream = filterStatementsBasedOnAmount(statementDTOStream, startAmount, endAmount);
        }

        AccountStatementDTO accountStatementDTO = new AccountStatementDTO();
        accountStatementDTO.setHashedAccountNumber(Convert.getSHA256Hash(accounts.get(0).getAccountNumber()));
        accountStatementDTO.setAccountType(accounts.get(0).getAccountType());
        accountStatementDTO.setStatements(statementDTOStream.collect(Collectors.toList()));

        return accountStatementDTO;
    }

    private Stream<StatementDTO> filterStatementsBasedOnDates(Stream<StatementDTO> statementDTOStream, String startDate, String endDate) {
        return statementDTOStream.filter(statementDTO ->
                statementDTO.getStatementDate().isAfter(Convert.stringToDate(startDate)) &&
                        statementDTO.getStatementDate().isBefore(Convert.stringToDate(endDate)));
    }

    private Stream<StatementDTO> filterStatementsBasedOnAmount(Stream<StatementDTO> statementDTOStream, String startAmount, String endAmount) {
        return statementDTOStream.filter(statementDTO ->
                statementDTO.getAmount() >= Float.parseFloat(startAmount) &&
                        statementDTO.getAmount() <= Float.parseFloat(endAmount));
    }
}
