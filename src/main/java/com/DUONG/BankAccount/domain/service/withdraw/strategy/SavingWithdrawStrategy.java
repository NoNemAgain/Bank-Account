package com.DUONG.BankAccount.domain.service.withdraw.strategy;

import com.DUONG.BankAccount.domain.model.AccountType;
import org.springframework.stereotype.Component;

@Component
public class SavingWithdrawStrategy extends AbstractWithdrawStrategy implements WithdrawStrategy {

    public AccountType getAccountType() {
        return AccountType.SAVING;
    }
}
