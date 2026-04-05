package com.duong.bank.port.in;

import com.duong.bank.domain.model.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

public interface WithdrawPort {
    public BankAccount withdraw(UUID id, BigDecimal amount);
}
