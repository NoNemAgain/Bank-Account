package com.DUONG.BankAccount.adapter.mapper;

import com.DUONG.BankAccount.adapter.in.dto.CheckingAccountDTO;
import com.DUONG.BankAccount.domain.model.CheckingAccount;

public class CheckingAccountMapper {

    private CheckingAccountMapper() {
    }

    public static CheckingAccountDTO toDTO(CheckingAccount checkingAccount) {
        if (checkingAccount == null) return null;
        return new CheckingAccountDTO(
                checkingAccount.getId(),
                checkingAccount.getBalance(),
                checkingAccount.isOverdraftAllowed(),
                checkingAccount.getOperationsHistory(),
                checkingAccount.getBankStatements(),
                checkingAccount.getOverdraftLimit()
        );
    }
}
