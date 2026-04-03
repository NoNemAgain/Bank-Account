package com.DUONG.BankAccount.domain.exception;

public class InsufficientFundsBalanceException extends RuntimeException {
    public InsufficientFundsBalanceException(String message){
        super(message);
    }
}
