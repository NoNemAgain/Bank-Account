package com.DUONG.BankAccount.domain.service.deposit.strategy;

import com.DUONG.BankAccount.domain.exception.InvalidAmountException;
import com.DUONG.BankAccount.domain.model.BankAccount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@Slf4j
public abstract class AbstractDepositStrategy implements DepositStrategy {

    public void deposit(BankAccount bankAccount, BigDecimal amount) {
        log.info("Deposit requested: accountId={}, amount={}", bankAccount.getId(), amount);

        log.debug("Check if deposit is possible : accountId={}, amount={}", bankAccount.getId(), amount);
        checkIfDepositIsPossible(bankAccount, amount);
        log.debug("Successfully checked ! Deposit is possible : accountId={}, amount={}", bankAccount.getId(), amount);

        bankAccount.setBalance(bankAccount.getBalance().add(amount));
    }

    @Override
    public abstract Class<? extends BankAccount> getAccountType();

    protected void checkIfAmountIsPositive(BigDecimal amount) {
        log.debug("Checking if amount is positive:amount={}", amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("The deposit amount must be positive");
        }
    }

    protected void checkIfDepositIsPossible(BankAccount bankAccount, BigDecimal amount) {
        log.debug("Checking if amount is valid:amount={}", amount);
        checkIfAmountIsPositive(amount);
    }
}
