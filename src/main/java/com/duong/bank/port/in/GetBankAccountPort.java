package com.duong.bank.port.in;

import com.duong.bank.domain.model.BankAccount;

import java.util.List;
import java.util.UUID;

public interface GetBankAccountPort {
    BankAccount getBankAccountById(UUID id);

    List<BankAccount> getBankAccounts();

    List<BankAccount> getBanksAccountsByOwner(String owner);
}
