package com.DUONG.BankAccount.adapter.mapper;

import com.DUONG.BankAccount.adapter.in.dto.SavingAccountDTO;
import com.DUONG.BankAccount.domain.model.SavingAccount;

public class SavingAccountMapper {

    private SavingAccountMapper() {
    }

    public static SavingAccountDTO toDTO(SavingAccount savingAccount) {
        if (savingAccount == null) return null;
        return new SavingAccountDTO(
                savingAccount.getId(),
                savingAccount.getBalance(),
                savingAccount.getOperationsHistory(),
                savingAccount.getBankStatements(),
                savingAccount.getBalanceLimit()
        );
    }
}
