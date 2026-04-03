package com.DUONG.BankAccount.domain.service.deposit;

import com.DUONG.BankAccount.domain.exception.InvalidAmountException;
import com.DUONG.BankAccount.domain.model.BankAccount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;


@Slf4j
public abstract class AbstractDepositStrategy implements DepositStrategy {

    public void deposit(BankAccount account, BigDecimal amount) {
        log.info("Deposit requested: accountId={}, amount={}", account.getId(), amount);
        checkIfDepositIsPossible(account, amount);
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public abstract Class<? extends BankAccount> getAccountType();

    protected void checkIfAmountIsPositive(BigDecimal amount) {
        log.debug("Checking if amount is positive:amount={}", amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("The deposit amount must be positive");
        }
    }

    protected void checkIfDepositIsPossible(BankAccount account, BigDecimal amount) {
        log.info("Checking if amount is valid:amount={}", amount);
        checkIfAmountIsPositive(amount);
    }
}
