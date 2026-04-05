package com.duong.bank.domain.exception;

public class ObjectNotfoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Couldn't find a";

    public ObjectNotfoundException(ObjectType objectType) {
        super(DEFAULT_MESSAGE + objectType.toString());
    }

    public ObjectNotfoundException(String message) {
        super(message);
    }
}
