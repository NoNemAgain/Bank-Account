package com.DUONG.BankAccount.domain.model;

import java.math.BigDecimal;

public enum OperationType {
    DEPOSIT {
        @Override
        public BigDecimal adjustAmount(BigDecimal amount) { return amount; }
    },
    WITHDRAW {
        @Override
        public BigDecimal adjustAmount(BigDecimal amount) { return amount.negate(); }
    };

    public abstract BigDecimal adjustAmount(BigDecimal amount);
}
