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

    private boolean exceedAllowed;

    private BigDecimal exceedBalance;

    public CheckingAccountDTO(UUID id, BigDecimal balance, boolean exceedAllowed,
                              List<Operation> operationsHistory, List<BankStatement> bankStatements,
                              BigDecimal exceedBalance) {
        super(id, balance, operationsHistory, bankStatements);
        this.exceedAllowed = exceedAllowed;
        this.exceedBalance = exceedBalance;
    }
}
