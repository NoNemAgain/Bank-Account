package com.duong.bank.domain.useCase.withdraw.strategy;

import com.duong.bank.domain.model.AccountType;
import org.springframework.stereotype.Component;

@Component
public class SavingWithdrawStrategy extends AbstractWithdrawStrategy implements WithdrawStrategy {

    public AccountType getAccountType() {
        return AccountType.SAVING;
    }
}
