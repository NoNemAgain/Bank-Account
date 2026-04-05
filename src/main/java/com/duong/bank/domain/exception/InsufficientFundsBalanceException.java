package com.duong.bank.domain.exception;

public class InsufficientFundsBalanceException extends RuntimeException {

    private final static String DEFAULT_MESSAGE = "You do not have sufficient funds in your account to complete this withdrawal.";
    public InsufficientFundsBalanceException(){
        super(DEFAULT_MESSAGE);
    }
    public InsufficientFundsBalanceException(String message){
        super(message);
    }
}
