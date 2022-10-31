package com.test.accounts.controller;

import com.test.accounts.dto.AccountStatementDTO;
import com.test.accounts.exception.AccountStatementException;
import com.test.accounts.model.ERole;
import com.test.accounts.model.request.SearchRequest;
import com.test.accounts.service.AccountStatementService;
import com.test.accounts.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/${version}/account")
@Slf4j
public class AccountStatementRestController {

    private AccountStatementService accountStatementService;

    @Autowired
    public AccountStatementRestController(AccountStatementService accountStatementService) {
        this.accountStatementService = accountStatementService;
    }

    @GetMapping("/statements")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<AccountStatementDTO> getAccountStatements(HttpServletRequest request,
                                              @RequestParam Map<String,String> searchParams) throws AccountStatementException {
        validateRequest(request, searchParams);
        SearchRequest searchRequest = buildRequest(searchParams, request.isUserInRole(ERole.ROLE_USER.name()));
        return new ResponseEntity<>(accountStatementService.getAccountStatements(searchRequest), HttpStatus.OK);
    }

    private void validateRequest(HttpServletRequest request, Map<String,String> searchParams) throws AccountStatementException {
        if(request.isUserInRole(ERole.ROLE_USER.name()) && searchParams.size() > 0) {
            throw new AccountStatementException(AccountStatementException.UNAUTHORIZED, "Unauthorized access to perform this operation");
        }
        if(request.isUserInRole(ERole.ROLE_ADMIN.name()) && !searchParams.containsKey(Constant.ACCOUNT_NUMBER)) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "Account number is not present in search request");
        }
        if(request.isUserInRole(ERole.ROLE_ADMIN.name()) &&
                (Objects.nonNull(searchParams.get(Constant.START_DATE)) && Objects.isNull(searchParams.get(Constant.END_DATE)))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "End Date is not present");
        }
        if(request.isUserInRole(ERole.ROLE_ADMIN.name()) &&
                (Objects.isNull(searchParams.get(Constant.START_DATE)) && Objects.nonNull(searchParams.get(Constant.END_DATE)))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "Start Date is not present");
        }
        if(request.isUserInRole(ERole.ROLE_ADMIN.name()) &&
                (Objects.isNull(searchParams.get(Constant.START_AMOUNT)) && Objects.nonNull(searchParams.get(Constant.END_AMOUNT)))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "End Amount is not present");
        }
        if(request.isUserInRole(ERole.ROLE_ADMIN.name()) &&
                (Objects.isNull(searchParams.get(Constant.START_AMOUNT)) && Objects.nonNull(searchParams.get(Constant.END_AMOUNT)))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "Start Amount is not present");
        }
    }

    private SearchRequest buildRequest(Map<String,String> searchParams, boolean isUser) {
        return new SearchRequest(
                isUser ? "0012250016002" : searchParams.get(Constant.ACCOUNT_NUMBER),
                searchParams.get(Constant.START_DATE),
                searchParams.get(Constant.END_DATE),
                searchParams.get(Constant.START_AMOUNT),
                searchParams.get(Constant.END_AMOUNT),
                isUser
        );
    }
}
