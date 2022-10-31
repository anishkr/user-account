package com.test.accounts.exception.handler;

import com.test.accounts.exception.AccountStatementException;
import com.test.accounts.model.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AccountStatementExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());


        return new ResponseEntity<>(new ErrorResponse(AccountStatementException.INVALID_REQUEST_ERROR, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountStatementException.class)
    public ResponseEntity<ErrorResponse> handleProductRetailException(HttpServletRequest req, AccountStatementException ex) {

        HttpStatus httpStatus;
        if (ex.getExceptionName().equals(AccountStatementException.INVALID_REQUEST_ERROR)
                || ex.getExceptionName().equals(AccountStatementException.BUSINESS_EXCEPTION)) {

            httpStatus = HttpStatus.BAD_REQUEST;

        } else {

            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        }

        String exceptionName = ex.getExceptionName();

        if (AccountStatementException.REST_CALL_EXCEPTION.equals(exceptionName)) {

            exceptionName = AccountStatementException.TECHNICAL_ERROR;

        }
        return new ResponseEntity<>(new ErrorResponse(exceptionName, ex.getMessage()), httpStatus);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleParentException(HttpServletRequest req, Throwable ex) {
        return new ResponseEntity<>(new ErrorResponse(AccountStatementException.TECHNICAL_ERROR, "Please contact support team"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
