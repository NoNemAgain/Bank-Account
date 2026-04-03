package com.DUONG.BankAccount.domain.observer;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.Operation;

public interface BankAccountOperationObserver {
    void onOperationCreated(BankAccount bankAccount,Operation operation);
}
