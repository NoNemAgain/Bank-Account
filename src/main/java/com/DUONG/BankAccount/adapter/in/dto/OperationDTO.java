package com.DUONG.BankAccount.adapter.in.dto;

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
public class OperationDTO {
    private UUID id;

    private String type;

    private LocalDateTime date;

    private BigDecimal montant;

    private UUID bankAccountId;

    private UUID bankStatementId;
}
