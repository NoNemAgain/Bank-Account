package com.duong.bank.domain.exception;

public class InvalidAmountException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "The amount must be positive";

    public InvalidAmountException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidAmountException(String message) {
        super(message);
    }
}
