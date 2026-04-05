package com.duong.bank.domain.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SavingAccount extends BankAccount {
    private BigDecimal balanceLimit;


    public SavingAccount(UUID id, BigDecimal balance,
                         List<Operation> operationsHistory, List<BankStatement> bankStatements,
                         BigDecimal balanceLimit) {
        super(id, balance, operationsHistory, bankStatements);
        this.balanceLimit = balanceLimit;
    }

    @Override
    public AccountType getTypeBank() {
        return AccountType.SAVING;
    }
}
