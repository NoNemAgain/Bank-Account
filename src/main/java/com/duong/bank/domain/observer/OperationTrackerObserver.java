package com.duong.bank.domain.observer;

import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.Operation;
import org.springframework.stereotype.Component;

@Component
public class OperationTrackerObserver implements BankAccountOperationObserver{

    @Override
    public void onOperationCreated(BankAccount bankAccount, Operation operation){
        bankAccount.getOperationsHistory().add(operation);
    }
}
