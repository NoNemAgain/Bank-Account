package com.DUONG.BankAccount.domain.service.deposit.strategy;

import com.DUONG.BankAccount.domain.model.AccountType;
import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.CheckingAccount;
import org.springframework.stereotype.Component;

@Component
public class CheckingDepositStrategy extends AbstractDepositStrategy implements DepositStrategy {

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
