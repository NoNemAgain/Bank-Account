package com.duong.bank.adapter.in.dto.request;

import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankStatement;
import com.duong.bank.domain.model.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccountRequest extends BankAccountRequest {
    private BigDecimal balanceLimit;

    public SavingAccountRequest(String owner,BigDecimal balance,
                                 BigDecimal balanceLimit) {
        super(owner,balance);
        this.balanceLimit = balanceLimit;
    }

    @Override
    public AccountType getTypeBank() {
        return AccountType.SAVING;
    }
}
