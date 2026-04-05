package com.duong.bank;

import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;

import java.math.BigDecimal;

public class BankAccountFactory {
    public static BankAccount bankAccountCreateTest(AccountType accountType) {
        BankAccount account;

        switch (accountType) {
            case CHECKING -> {
                account = new CheckingAccount();
                ((CheckingAccount) account).setOverdraftAllowed(true);
                ((CheckingAccount) account).setOverdraftLimit(new BigDecimal("-2000"));
            }
            case SAVING -> {
                account = new SavingAccount();
                ((SavingAccount) account).setBalanceLimit(new BigDecimal("22500"));
            }
            default -> throw new IllegalArgumentException("Unknow account type");
        }

        account.setBalance(new BigDecimal("1000"));
        return account;
    }
}
