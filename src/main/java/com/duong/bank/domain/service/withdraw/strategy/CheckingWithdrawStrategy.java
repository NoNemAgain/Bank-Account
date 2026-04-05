package com.duong.bank.domain.service.withdraw.strategy;

import com.duong.bank.domain.exception.InsufficientFundsBalanceException;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.CheckingAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class CheckingWithdrawStrategy extends AbstractWithdrawStrategy implements WithdrawStrategy {

    @Override
    protected void checkIfEnoughMoneyInBalance(BankAccount bankAccount, BigDecimal amount) {
        BigDecimal overdraftLimit = ((CheckingAccount) bankAccount).getOverdraftLimit();
        Boolean overdraftAllowed = ((CheckingAccount) bankAccount).isOverdraftAllowed();
        BigDecimal finalBalance = bankAccount.getBalance().subtract(amount);

        log.debug("Check if the balance will exceed overdaft limit : overdraftLimit={}, amount={}", overdraftLimit, amount);

        if ((finalBalance.compareTo(overdraftLimit) < 0) && (overdraftAllowed)) {
            throw new InsufficientFundsBalanceException();

        }
    }

    protected void checkIfOverdraftAllowed(BankAccount bankAccount, BigDecimal amount) {
        BigDecimal overdraftLimit = ((CheckingAccount) bankAccount).getOverdraftLimit();
        Boolean overdraftAllowed = ((CheckingAccount) bankAccount).isOverdraftAllowed();
        BigDecimal finalBalance = bankAccount.getBalance().subtract(amount);

        log.debug("Check if overdraft is allowed : overdraftAllowed={}", overdraftAllowed);

        if ((finalBalance.compareTo(new BigDecimal("0")) < 0) && (!overdraftAllowed)) {
            throw new InsufficientFundsBalanceException();

        }
    }

    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }

    @Override
    protected void checkIfWithdrawIsPossible(BankAccount bankAccount, BigDecimal amount) {
        super.checkIfWithdrawIsPossible(bankAccount, amount);
        checkIfOverdraftAllowed(bankAccount, amount);
    }

}
