package com.duong.bank.adapter.mapper;

import com.duong.bank.adapter.in.dto.response.OperationResponse;
import com.duong.bank.domain.model.Operation;

public class OperationMapper {

    private OperationMapper() {
    }

    public static OperationResponse toDTO(Operation operation) {
        if (operation == null) return null;
        return new OperationResponse(
                operation.getId(),
                operation.getType(),
                operation.getDate(),
                operation.getAmount(),
                operation.getBankAccount().getId(),
                operation.getBankStatement().getId()
        );
    }
}
