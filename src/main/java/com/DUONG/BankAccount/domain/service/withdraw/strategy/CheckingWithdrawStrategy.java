package com.DUONG.BankAccount.domain.service.withdraw.strategy;

import com.DUONG.BankAccount.domain.exception.InsufficientFundsBalanceException;
import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.CheckingAccount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class CheckingWithdrawStrategy extends AbstractWithdrawStrategy implements WithdrawStrategy {

    @Override
    protected void checkIfEnoughMoneyInBalance(BankAccount account, BigDecimal amount) {
        BigDecimal overdraftLimit = ((CheckingAccount) account).getOverdraftLimit();
        Boolean overdraftAllowed = ((CheckingAccount) account).isOverdraftAllowed();
        BigDecimal finalBalance = account.getBalance().subtract(amount);

        log.debug("Check if the balance will exceed overdaft limit : overdraftLimit={}, amount={}", overdraftLimit, amount);

        if ((finalBalance.compareTo(overdraftLimit) < 0) && (overdraftAllowed)) {
            throw new InsufficientFundsBalanceException("Vous n’avez pas assez d’argent sur votre compte : vous ne pouvez pas retirer cet argent.");

        }
    }

    protected void checkIfOverdraftAllowed(BankAccount account, BigDecimal amount) {
        BigDecimal overdraftLimit = ((CheckingAccount) account).getOverdraftLimit();
        Boolean overdraftAllowed = ((CheckingAccount) account).isOverdraftAllowed();
        BigDecimal finalBalance = account.getBalance().subtract(amount);

        log.debug("Check if overdraft is allowed : overdraftAllowed={}", overdraftAllowed);

        if ((finalBalance.compareTo(new BigDecimal("0")) < 0) && (!overdraftAllowed)) {
            throw new InsufficientFundsBalanceException("Vous n’avez pas assez d’argent sur votre compte : vous ne pouvez pas retirer cet argent.");

        }
    }

    public Class<? extends BankAccount> getAccountType() {
        return CheckingAccount.class;
    }

    @Override
    protected void checkIfWithdrawIsPossible(BankAccount account, BigDecimal amount) {
        super.checkIfWithdrawIsPossible(account, amount);
        checkIfOverdraftAllowed(account, amount);
    }

}
