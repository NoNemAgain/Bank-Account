package com.duong.bank.port.in;

import com.duong.bank.domain.model.BankStatement;

import java.util.UUID;

public interface CreateBankStatementPort {
    BankStatement createBankStatement(UUID id);
}
