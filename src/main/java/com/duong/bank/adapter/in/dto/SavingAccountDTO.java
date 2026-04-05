package com.duong.bank.adapter.in.dto;


import com.duong.bank.domain.model.BankStatement;
import com.duong.bank.domain.model.Operation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SavingAccountDTO extends BankAccountDTO {
    private BigDecimal balanceLimit;


    public SavingAccountDTO(UUID id, BigDecimal balance,
                            List<Operation> operationsHistory, List<BankStatement> bankStatements,
                            BigDecimal balanceLimit) {
        super(id, balance, operationsHistory, bankStatements);
        this.balanceLimit = balanceLimit;
    }
}
