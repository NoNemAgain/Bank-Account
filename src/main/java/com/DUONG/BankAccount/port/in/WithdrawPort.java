package com.DUONG.BankAccount.port.in;

import java.math.BigDecimal;
import java.util.UUID;

public interface WithdrawPort {
    public void withdraw(UUID id, BigDecimal amount);
}
