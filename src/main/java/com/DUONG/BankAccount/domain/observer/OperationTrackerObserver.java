package com.DUONG.BankAccount.domain.observer;

import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.Operation;

public class OperationTrackerObserver implements BankAccountOperationObserver{

    @Override
    public void onOperationCreated(BankAccount bankAccount, Operation operation){
        bankAccount.getOperationsHistory().add(operation);
    }
}
