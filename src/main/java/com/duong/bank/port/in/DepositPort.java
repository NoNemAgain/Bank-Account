package com.duong.bank.port.in;

import com.duong.bank.domain.model.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

public interface DepositPort {
    public BankAccount deposit(UUID id, BigDecimal amount);
}
