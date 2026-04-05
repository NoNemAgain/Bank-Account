package com.duong.bank;

import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.Operation;

import java.time.LocalDateTime;
import java.util.UUID;

public class OperationFactory {
    public static Operation operationCreateTest(BankAccount bankAccount, int minusDay) {
        Operation operation = new Operation();
        operation.setId(UUID.randomUUID());
        operation.setDate(LocalDateTime.now().minusDays(minusDay));
        bankAccount.addOperation(operation);

        return operation;
    }
}
