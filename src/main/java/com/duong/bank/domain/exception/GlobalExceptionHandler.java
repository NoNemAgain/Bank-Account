package com.duong.bank.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            InvalidAmountException.class,
            InsufficientFundsBalanceException.class,
            ExceedLimitBalanceException.class,
            NoPermissionGivenException.class,
            ObjectNotfoundException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBankAccountExceptions(RuntimeException ex) {
        return Map.of("message", ex.getMessage());
    }
}
