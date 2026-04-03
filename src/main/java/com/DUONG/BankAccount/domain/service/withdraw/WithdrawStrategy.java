package com.DUONG.BankAccount.domain.service.withdraw;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.service.OperationStrategy;

import java.math.BigDecimal;

public interface WithdrawStrategy extends OperationStrategy {
    public void withdraw(BankAccount account, BigDecimal amount);

    public Class<? extends BankAccount> getAccountType();
}
