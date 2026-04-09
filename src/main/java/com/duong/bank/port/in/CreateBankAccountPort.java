package com.duong.bank.port.in;

import com.duong.bank.domain.model.BankAccount;

public interface CreateBankAccountPort {
    BankAccount createBankAccount(BankAccount bankAccount);
}
