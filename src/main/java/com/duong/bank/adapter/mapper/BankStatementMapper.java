package com.duong.bank.adapter.mapper;

import com.duong.bank.adapter.in.dto.response.BankStatementResponse;
import com.duong.bank.domain.model.BankStatement;

public class BankStatementMapper {

    private BankStatementMapper() {
    }

    public static BankStatementResponse toDTO(BankStatement bankStatement) {
        if (bankStatement == null) return null;
        return new BankStatementResponse(
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
