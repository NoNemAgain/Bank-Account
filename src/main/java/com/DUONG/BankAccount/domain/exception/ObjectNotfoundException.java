package com.DUONG.BankAccount.domain.exception;

public class ObjectNotfoundException extends RuntimeException {
    public ObjectNotfoundException(String message){
        super(message);
    }
}
