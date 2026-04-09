package com.duong.bank.adapter.in.dto.response;

import com.duong.bank.domain.model.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse {
    private UUID id;

    private OperationType type;

    private LocalDateTime date;

    private BigDecimal montant;

    private UUID bankAccountId;

    private UUID bankStatementId;
}
