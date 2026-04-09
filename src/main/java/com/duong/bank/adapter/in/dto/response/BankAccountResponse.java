package com.duong.bank.adapter.in.dto.response;

import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankStatement;
import com.duong.bank.domain.model.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BankAccountResponse {
    private UUID id;

    private String owner;

    private BigDecimal balance;

    private List<Operation> operationsHistory = new ArrayList<>();

    private List<BankStatement> bankStatements = new ArrayList<>();

    public abstract AccountType getTypeBank();
}
