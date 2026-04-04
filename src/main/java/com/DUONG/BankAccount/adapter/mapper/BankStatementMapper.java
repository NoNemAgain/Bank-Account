package com.DUONG.BankAccount.adapter.mapper;

import com.DUONG.BankAccount.adapter.in.dto.BankStatementDTO;
import com.DUONG.BankAccount.domain.model.BankStatement;

public class BankStatementMapper {

    private BankStatementMapper() {
    }

    public static BankStatementDTO toDTO(BankStatement bankStatement) {
        if (bankStatement == null) return null;
        return new BankStatementDTO(
                bankStatement.getId(),
                bankStatement.getAccountType(),
                bankStatement.getBalance(),
                bankStatement.getDate(),
                bankStatement.getOperations().stream()
                        .map(operation -> OperationMapper.toDTO(operation)).toList(),
                bankStatement.getBankAccount().getId()
        );
    }
}
