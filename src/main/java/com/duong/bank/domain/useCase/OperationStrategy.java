package com.duong.bank.domain.useCase;

import com.duong.bank.domain.model.AccountType;

public interface OperationStrategy {
    AccountType getAccountType();
}
