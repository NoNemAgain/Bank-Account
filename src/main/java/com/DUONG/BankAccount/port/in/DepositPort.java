package com.DUONG.BankAccount.port.in;

import com.DUONG.BankAccount.domain.model.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

public interface DepositPort {
    public BankAccount deposit(UUID id, BigDecimal amount);
}
