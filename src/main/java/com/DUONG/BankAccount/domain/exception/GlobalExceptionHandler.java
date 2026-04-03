package com.DUONG.BankAccount.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidAmountException(InvalidAmountException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(InsufficientFundsBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInsufficientFundsBalanceException(InsufficientFundsBalanceException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(ExceedLimitBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleExceedLimitBalanceException(ExceedLimitBalanceException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(NoPermissionGivenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleNoPermissionGivenException(NoPermissionGivenException ex) {
        return Map.of("message", ex.getMessage());
    }

    @ExceptionHandler(ObjectNotfoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleObjectNotfoundException(ObjectNotfoundException ex) {
        return Map.of("message", ex.getMessage());
    }
}
