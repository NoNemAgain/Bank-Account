package com.duong.bank.adapter.mapper;

import com.duong.bank.adapter.in.dto.BankAccountDTO;
import com.duong.bank.adapter.in.dto.CheckingAccountDTO;
import com.duong.bank.adapter.in.dto.SavingAccountDTO;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;

public class BankAccountMapper {

    private BankAccountMapper() {
    }

    public static BankAccountDTO toDTO(BankAccount bankAccount) {
        if (bankAccount == null) return null;

        switch (bankAccount.getTypeBank()) {
            case CHECKING -> {
                CheckingAccount checkingAccount = (CheckingAccount) bankAccount;
                return new CheckingAccountDTO(
                        checkingAccount.getId(),
                        checkingAccount.getBalance(),
                        checkingAccount.isOverdraftAllowed(),
                        checkingAccount.getOperationsHistory(),
                        checkingAccount.getBankStatements(),
                        checkingAccount.getOverdraftLimit()
                );
            }
            case SAVING -> {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return new SavingAccountDTO(
                        savingAccount.getId(),
                        savingAccount.getBalance(),
                        savingAccount.getOperationsHistory(),
                        savingAccount.getBankStatements(),
                        savingAccount.getBalanceLimit()
                );
            }
            default -> throw new IllegalArgumentException("Unknown account type: " + bankAccount.getTypeBank());
        }
    }
}
