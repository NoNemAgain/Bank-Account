package com.DUONG.BankAccount.domain.model;


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
public class CheckingAccount extends BankAccount {


    private boolean exceedAllowed;

    private BigDecimal exceedBalance;

    public CheckingAccount(UUID id, BigDecimal balance, boolean exceedAllowed,
                           List<Operation> operationsHistory, List<BankStatement> bankStatements,
                           BigDecimal exceedBalance) {
        super(id, balance, operationsHistory, bankStatements);
        this.exceedAllowed = exceedAllowed;
        this.exceedBalance = exceedBalance;
    }
}
