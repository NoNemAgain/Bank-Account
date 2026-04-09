package com.duong.bank.adapter.in.dto.response;


import com.duong.bank.domain.model.AccountType;
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
public class SavingAccountResponse extends BankAccountResponse {
    private BigDecimal balanceLimit;

    public SavingAccountResponse(UUID id, String owner, BigDecimal balance,
                                 List<Operation> operationsHistory, List<BankStatement> bankStatements,
                                 BigDecimal balanceLimit) {
        super(id, owner, balance, operationsHistory, bankStatements);
        this.balanceLimit = balanceLimit;
    }

    @Override
    public AccountType getTypeBank() {
        return AccountType.SAVING;
    }
}
