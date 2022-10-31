package com.test.accounts.model.response;

import java.util.List;

public class ErrorResponse {
    private String errorName;
    private String errorMessage;
    private List<String> errorMessages;

    public ErrorResponse() {
    }

    public ErrorResponse(String errorName, String errorMessage) {
        this.errorName = errorName;
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(String errorName, List<String> errorMessages) {
        this.errorName = errorName;
        this.errorMessages = errorMessages;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
