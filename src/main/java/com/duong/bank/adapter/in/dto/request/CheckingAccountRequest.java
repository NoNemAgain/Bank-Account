package com.duong.bank.adapter.in.dto.request;

import com.duong.bank.domain.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckingAccountRequest extends BankAccountRequest {
    private boolean overdraftAllowed;

    private BigDecimal overdraftLimit;

    public CheckingAccountRequest(String owner,BigDecimal balance, boolean overdraftAllowed,
                                  BigDecimal overdraftLimit) {
        super(owner,balance);
        this.overdraftAllowed = overdraftAllowed;
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public AccountType getTypeBank() {
        return AccountType.CHECKING;
    }
}
