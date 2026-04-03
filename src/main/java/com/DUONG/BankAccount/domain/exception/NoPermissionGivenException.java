package com.DUONG.BankAccount.domain.exception;

public class NoPermissionGivenException extends RuntimeException {
    public NoPermissionGivenException(String message){
        super(message);
    }
}
