package com.duong.bank.domain.exception;

public class ExceedLimitBalanceException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "The deposit amount exceeds the account’s balance limit and cannot be processed.";

    public ExceedLimitBalanceException() {
        super(DEFAULT_MESSAGE);
    }
    public ExceedLimitBalanceException(String message){
        super(message);
    }
}
