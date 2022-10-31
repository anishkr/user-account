package com.test.accounts.exception;

public class AccountStatementException extends Exception{

    public static final String BUSINESS_EXCEPTION = "Business Exception";
    public static final String TECHNICAL_ERROR = "Technical Error";
    public static final String REST_CALL_EXCEPTION = "Rest Call Exception";
    public static final String INVALID_REQUEST_ERROR = "Invalid Request Error";
    public static final String NOT_FOUND = "Not Found";
    public static final String UNAUTHORIZED = "UNAUTHORIZED Access";

    private final String exceptionName;
    private final String exceptionMsg;

    public AccountStatementException(String exceptionName, String exceptionMsg) {

        super(exceptionMsg);
        this.exceptionName = exceptionName;
        this.exceptionMsg = exceptionMsg;
    }

    public AccountStatementException(String exceptionName, String exceptionMsg, Throwable cause) {

        super(exceptionMsg, cause);
        this.exceptionName = exceptionName;
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

}
