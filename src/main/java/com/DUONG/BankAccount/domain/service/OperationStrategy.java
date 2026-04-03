package com.DUONG.BankAccount.domain.service;

import com.DUONG.BankAccount.domain.model.BankAccount;

public interface OperationStrategy {
    Class<? extends BankAccount> getAccountType();
}
