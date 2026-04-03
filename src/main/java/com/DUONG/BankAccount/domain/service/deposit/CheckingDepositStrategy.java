package com.DUONG.BankAccount.domain.service.deposit;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.CheckingAccount;

public class CheckingDepositStrategy extends AbstractDepositStrategy implements DepositStrategy {

    public Class<? extends BankAccount> getAccountType() {
        return CheckingAccount.class;
    }
}
