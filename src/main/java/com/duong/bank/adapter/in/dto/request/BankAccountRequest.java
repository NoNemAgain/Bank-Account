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
public abstract class BankAccountRequest {

    private BigDecimal balance;

    public abstract AccountType getTypeBank();

}
