package com.duong.bank.domain.useCase.deposit.strategy;

import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.useCase.OperationStrategy;

import java.math.BigDecimal;

public interface DepositStrategy extends OperationStrategy {
    public void deposit(BankAccount bankAccount, BigDecimal amount);

    public AccountType getAccountType();
}
