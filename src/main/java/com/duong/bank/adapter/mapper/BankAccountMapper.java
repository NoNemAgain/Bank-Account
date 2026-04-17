package com.duong.bank.adapter.mapper;

import com.duong.bank.adapter.in.dto.request.BankAccountRequest;
import com.duong.bank.adapter.in.dto.request.CheckingAccountRequest;
import com.duong.bank.adapter.in.dto.request.SavingAccountRequest;
import com.duong.bank.adapter.in.dto.response.BankAccountResponse;
import com.duong.bank.adapter.in.dto.response.CheckingAccountResponse;
import com.duong.bank.adapter.in.dto.response.SavingAccountResponse;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;

public class BankAccountMapper {

    private BankAccountMapper() {
    }

    public static BankAccountResponse toResponse(BankAccount bankAccount) {
        if (bankAccount == null) return null;

        switch (bankAccount.getTypeBank()) {
            case CHECKING -> {
                CheckingAccount checkingAccount = (CheckingAccount) bankAccount;
                return new CheckingAccountResponse(
                        checkingAccount.getId(),
                        checkingAccount.getOwner(),
                        checkingAccount.getBalance(),
                        checkingAccount.isOverdraftAllowed(),
                        checkingAccount.getOperationsHistory(),
                        checkingAccount.getBankStatements(),
                        checkingAccount.getOverdraftLimit()
                );
            }
            case SAVING -> {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return new SavingAccountResponse(
                        savingAccount.getId(),
                        savingAccount.getOwner(),
                        savingAccount.getBalance(),
                        savingAccount.getOperationsHistory(),
                        savingAccount.getBankStatements(),
                        savingAccount.getBalanceLimit()
                );
            }
            default -> throw new IllegalArgumentException("Unknown account type: " + bankAccount.getTypeBank());
        }
    }


    public static BankAccount responseToEntity(BankAccountResponse bankAccountResponse) {
        if (bankAccountResponse == null) return null;

        switch (bankAccountResponse.getTypeBank()) {
            case CHECKING -> {
                CheckingAccountResponse checkingAccountDTO = (CheckingAccountResponse) bankAccountResponse;
                return new CheckingAccount(
                        checkingAccountDTO.getId(),
                        checkingAccountDTO.getOwner(),
                        checkingAccountDTO.getBalance(),
                        checkingAccountDTO.isOverdraftAllowed(),
                        checkingAccountDTO.getOperationsHistory(),
                        checkingAccountDTO.getBankStatements(),
                        checkingAccountDTO.getOverdraftLimit()
                );
            }
            case SAVING -> {
                SavingAccountResponse savingAccountDTO = (SavingAccountResponse) bankAccountResponse;
                return new SavingAccount(
                        savingAccountDTO.getId(),
                        savingAccountDTO.getOwner(),
                        savingAccountDTO.getBalance(),
                        savingAccountDTO.getOperationsHistory(),
                        savingAccountDTO.getBankStatements(),
                        savingAccountDTO.getBalanceLimit()
                );
            }
            default -> throw new IllegalArgumentException("Unknown account type: " + bankAccountResponse.getTypeBank());
        }
    }


    public static BankAccount requestToEntity(BankAccountRequest bankAccountRequest) {
        if (bankAccountRequest == null) return null;

        switch (bankAccountRequest.getTypeBank()) {
            case CHECKING -> {
                CheckingAccountRequest checkingAccountRequest = (CheckingAccountRequest) bankAccountRequest;
                CheckingAccount checkingAccount = new CheckingAccount();

                checkingAccount.setOwner(checkingAccountRequest.getOwner());
                checkingAccount.setBalance(checkingAccountRequest.getBalance());
                checkingAccount.setOverdraftAllowed(checkingAccountRequest.isOverdraftAllowed());
                checkingAccount.setOverdraftLimit(checkingAccountRequest.getOverdraftLimit());

                return checkingAccount;
            }
            case SAVING -> {
                SavingAccountRequest savingAccountRequest = (SavingAccountRequest) bankAccountRequest;
                SavingAccount savingAccount = new SavingAccount();

                savingAccount.setOwner(savingAccountRequest.getOwner());
                savingAccount.setBalance(savingAccountRequest.getBalance());
                savingAccount.setBalanceLimit(savingAccountRequest.getBalanceLimit());

                return savingAccount;
            }
            default -> throw new IllegalArgumentException("Unknown account type: " + bankAccountRequest.getTypeBank());
        }
    }
}
