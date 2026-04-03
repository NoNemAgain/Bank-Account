package com.DUONG.BankAccount.domain.service.withdraw.strategy;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.service.OperationStrategy;

import java.math.BigDecimal;

public interface WithdrawStrategy extends OperationStrategy {
    public void withdraw(BankAccount bankAccount, BigDecimal amount);

    public Class<? extends BankAccount> getAccountType();
}
