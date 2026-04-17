package com.duong.bank.adapter.in.dto.request;

import com.duong.bank.domain.model.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String owner;

    private BigDecimal balance;

    @JsonIgnore
    public abstract AccountType getTypeBank();

}
