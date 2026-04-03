package com.DUONG.BankAccount.port.in;

import java.math.BigDecimal;
import java.util.UUID;

public interface DepositPort {
    public void deposit(UUID id, BigDecimal amount);
}
