package com.DUONG.BankAccount.domain.service.withdraw.strategy;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.SavingAccount;

public class SavingWithdrawStrategy extends AbstractWithdrawStrategy implements WithdrawStrategy {

    public Class<? extends BankAccount> getAccountType() {
        return SavingAccount.class;
    }
}
