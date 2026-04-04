package com.DUONG.BankAccount.domain.service.withdraw.strategy;

import com.DUONG.BankAccount.domain.exception.InsufficientFundsBalanceException;
import com.DUONG.BankAccount.domain.exception.InvalidAmountException;
import com.DUONG.BankAccount.domain.model.BankAccount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public abstract class AbstractWithdrawStrategy implements WithdrawStrategy {

    @Override
    public void withdraw(BankAccount bankAccount, BigDecimal amount) {
        log.info("Withdraw requested: accountId={}, amount={}", bankAccount.getId(), amount);

        log.debug("Check if withdraw is possible : accountId={}, amount={}", bankAccount.getId(), amount);
        checkIfWithdrawIsPossible(bankAccount, amount);
        log.debug("Successfully checked ! Withdraw is possible : accountId={}, amount={}", bankAccount.getId(), amount);

        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));
    }

    @Override
    public abstract Class<? extends BankAccount> getAccountType();


    protected void checkIfAmountIsPositive(BigDecimal amount) {
        log.debug("Checking if amount is positive:amount={}", amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }
    }

    protected void checkIfEnoughMoneyInBalance(BankAccount bankAccount, BigDecimal amount) {
        BigDecimal balanceAcc = bankAccount.getBalance();

        log.debug("Checking if there is enough money in the bank account:amount={},:balance={}", amount, balanceAcc);
        if (amount.compareTo(balanceAcc) > 0) {
            throw new InsufficientFundsBalanceException();
        }
    }

    protected void checkIfWithdrawIsPossible(BankAccount bankAccount, BigDecimal amount) {
        checkIfAmountIsPositive(amount);
        checkIfEnoughMoneyInBalance(bankAccount, amount);
    }
}
