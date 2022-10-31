package com.test.accounts.validation;

import com.test.accounts.exception.AccountStatementException;
import com.test.accounts.util.Constant;
import com.test.accounts.util.Convert;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Objects;

@UtilityClass
public class Validation {
    public static void validateRequest(Map<String, String> searchParams, boolean isValidationForAdmin) throws AccountStatementException {
        if(!isValidationForAdmin && searchParams.size() > 0) {
            throw new AccountStatementException(AccountStatementException.UNAUTHORIZED, "Unauthorized access to perform this operation");
        }else {
            validateRequestForAdmin(searchParams);
        }
    }

    private static void validateRequestForAdmin(Map<String, String> searchParams) throws AccountStatementException {
        if(!searchParams.containsKey(Constant.ACCOUNT_NUMBER)) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "Account number is not present in search request");
        }
        if(Objects.nonNull(searchParams.get(Constant.START_DATE)) && Objects.isNull(searchParams.get(Constant.END_DATE))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "End Date is not present");
        }
        if(Objects.isNull(searchParams.get(Constant.START_DATE)) && Objects.nonNull(searchParams.get(Constant.END_DATE))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "Start Date is not present");
        }
        if(Objects.isNull(searchParams.get(Constant.START_AMOUNT)) && Objects.nonNull(searchParams.get(Constant.END_AMOUNT))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "End Amount is not present");
        }
        if(Objects.isNull(searchParams.get(Constant.START_AMOUNT)) && Objects.nonNull(searchParams.get(Constant.END_AMOUNT))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "Start Amount is not present");
        }
        if(Objects.nonNull(searchParams.get(Constant.START_DATE)) && !Convert.validationDate(searchParams.get(Constant.START_DATE))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "Start date format should be 'dd.MM.yyyy'");
        }
        if(Objects.nonNull(searchParams.get(Constant.END_DATE)) && !Convert.validationDate(searchParams.get(Constant.END_DATE))) {
            throw new AccountStatementException(AccountStatementException.INVALID_REQUEST_ERROR, "End date format should be 'dd.MM.yyyy'");
        }
    }
}
