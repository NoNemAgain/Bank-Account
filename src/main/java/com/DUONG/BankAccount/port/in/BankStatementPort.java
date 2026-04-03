package com.DUONG.BankAccount.port.in;

import com.DUONG.BankAccount.domain.model.BankStatement;

import java.util.UUID;

public interface BankStatementPort {
    BankStatement createBankStatement(UUID id);
}
