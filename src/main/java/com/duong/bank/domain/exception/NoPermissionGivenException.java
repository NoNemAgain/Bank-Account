package com.duong.bank.domain.exception;

public class NoPermissionGivenException extends RuntimeException {
    public NoPermissionGivenException(String message) {
        super(message);
    }
}
