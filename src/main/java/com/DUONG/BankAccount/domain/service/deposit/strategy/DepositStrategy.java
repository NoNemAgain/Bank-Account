package com.DUONG.BankAccount.domain.service.deposit.strategy;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.service.OperationStrategy;

import java.math.BigDecimal;

public interface DepositStrategy extends OperationStrategy {
    public void deposit(BankAccount account, BigDecimal amount);

    public Class<? extends BankAccount> getAccountType();
}
