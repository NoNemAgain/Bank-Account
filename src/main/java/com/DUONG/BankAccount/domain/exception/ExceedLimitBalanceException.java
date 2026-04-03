package com.DUONG.BankAccount.domain.exception;

public class ExceedLimitBalanceException extends RuntimeException {
    public ExceedLimitBalanceException(String message){
        super(message);
    }
}
