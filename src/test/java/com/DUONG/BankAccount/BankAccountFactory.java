package com.DUONG.BankAccount;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.CheckingAccount;
import com.DUONG.BankAccount.domain.model.SavingAccount;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccountFactory {
    public static BankAccount bankAccountCreateTest(String type) {
        BankAccount account;

        switch (type) {
            case "CHECKING" -> {
                account = new CheckingAccount();
                ((CheckingAccount) account).setOverdraftLimit(new BigDecimal("-2000"));
            }
            case "SAVING" -> {
                account = new SavingAccount();
                ((SavingAccount) account).setBalanceLimit(new BigDecimal("22500"));
            }
            default -> throw new IllegalArgumentException("Unknow account type" + type);
        }

        account.setId(UUID.randomUUID());
        account.setBalance(new BigDecimal("1000"));
        return account;
    }
}
