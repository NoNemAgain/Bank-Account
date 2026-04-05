package com.duong.bank.domain.service.withdraw.strategy;

import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.service.OperationStrategy;

import java.math.BigDecimal;

public interface WithdrawStrategy extends OperationStrategy {
    public void withdraw(BankAccount bankAccount, BigDecimal amount);

    public AccountType getAccountType();
}
