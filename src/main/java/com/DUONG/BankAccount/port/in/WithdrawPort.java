package com.DUONG.BankAccount.port.in;

import com.DUONG.BankAccount.domain.model.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

public interface WithdrawPort {
    public BankAccount withdraw(UUID id, BigDecimal amount);
}
