package com.DUONG.BankAccount.domain.service.deposit.strategy;

import com.DUONG.BankAccount.domain.exception.ExceedLimitBalanceException;
import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.SavingAccount;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class SavingDepositStrategy extends AbstractDepositStrategy implements DepositStrategy {

    public Class<? extends BankAccount> getAccountType() {
        return SavingAccount.class;
    }

    private void checkIfExceedBalance(BankAccount bankAccount, BigDecimal amount) {
        SavingAccount savingAccount = (SavingAccount) bankAccount;

        BigDecimal balanceLimit = savingAccount.getBalanceLimit();
        log.debug("Checking if limit would be exceeded:amount={}, exceed={}", amount, balanceLimit);

        BigDecimal finalBalance = savingAccount.getBalance().add(amount);

        if (finalBalance.compareTo(balanceLimit) > 0) {
            throw new ExceedLimitBalanceException
                    ("The account balance limit would be exceeded; you cannot deposit this amount.");
        }
    }

    @Override
    protected void checkIfDepositIsPossible(BankAccount bankAccount, BigDecimal amount) {
        super.checkIfAmountIsPositive(amount);
        checkIfExceedBalance(bankAccount, amount);
    }
}
