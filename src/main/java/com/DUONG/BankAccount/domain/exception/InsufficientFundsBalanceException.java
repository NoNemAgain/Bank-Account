package com.DUONG.BankAccount.domain.exception;

public class InsufficientFundsBalanceException extends RuntimeException {

    private static String DEFAULT_MESSAGE = "You do not have sufficient funds in your account to complete this withdrawal.";
    public InsufficientFundsBalanceException(){
        super(DEFAULT_MESSAGE);
    }
    public InsufficientFundsBalanceException(String message){
        super(message);
    }
}
