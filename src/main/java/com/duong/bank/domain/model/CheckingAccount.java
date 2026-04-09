package com.duong.bank.domain.model;


import jakarta.persistence.DiscriminatorValue;
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
@DiscriminatorValue("CHECKNG")
public class CheckingAccount extends BankAccount {


    private boolean overdraftAllowed;

    private BigDecimal overdraftLimit;

    public CheckingAccount(UUID id, String owner, BigDecimal balance, boolean overdraftAllowed,
                           List<Operation> operationsHistory, List<BankStatement> bankStatements,
                           BigDecimal overdraftLimit) {
        super(id,owner, balance, operationsHistory, bankStatements);
        this.overdraftAllowed = overdraftAllowed;
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public AccountType getTypeBank() {
        return AccountType.CHECKING;
    }
}
