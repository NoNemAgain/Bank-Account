package com.DUONG.BankAccount.adapter.mapper;

import com.DUONG.BankAccount.adapter.in.dto.OperationDTO;
import com.DUONG.BankAccount.domain.model.Operation;

public class OperationMapper {

    private OperationMapper() {
    }

    public static OperationDTO toDTO(Operation operation) {
        if (operation == null) return null;
        return new OperationDTO(
                operation.getId(),
                operation.getType(),
                operation.getDate(),
                operation.getAmount(),
                operation.getBankAccount().getId(),
                operation.getBankStatement().getId()
        );
    }
}
