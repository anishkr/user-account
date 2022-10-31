package com.test.accounts.service;

import com.test.accounts.dto.AccountStatementDTO;
import com.test.accounts.exception.AccountStatementException;
import com.test.accounts.model.request.SearchRequest;
import org.springframework.stereotype.Service;

@Service
public interface AccountStatementService {
    AccountStatementDTO getAccountStatements(SearchRequest searchRequest) throws AccountStatementException;
}
