package com.DUONG.BankAccount.adapter.in.dto;


import com.DUONG.BankAccount.domain.model.BankStatement;
import com.DUONG.BankAccount.domain.model.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CheckingAccountDTO extends BankAccountDTO {

    private boolean overdraftAllowed;

    private BigDecimal overdraftLimit;

    public CheckingAccountDTO(UUID id, BigDecimal balance, boolean overdraftAllowed,
                              List<Operation> operationsHistory, List<BankStatement> bankStatements,
                              BigDecimal overdraftLimit) {
        super(id, balance, operationsHistory, bankStatements);
        this.overdraftAllowed = overdraftAllowed;
        this.overdraftLimit = overdraftLimit;
    }
}
