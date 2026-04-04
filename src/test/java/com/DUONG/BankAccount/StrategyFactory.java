package com.DUONG.BankAccount;

import com.DUONG.BankAccount.domain.model.OperationType;
import com.DUONG.BankAccount.domain.service.OperationStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.CheckingDepositStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.DepositStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.SavingDepositStrategy;
import com.DUONG.BankAccount.domain.service.withdraw.strategy.CheckingWithdrawStrategy;
import com.DUONG.BankAccount.domain.service.withdraw.strategy.SavingWithdrawStrategy;
import com.DUONG.BankAccount.domain.service.withdraw.strategy.WithdrawStrategy;

import java.util.ArrayList;
import java.util.List;

public class StrategyFactory {
    public static <T extends OperationStrategy> List<T> strategyListCreate(OperationType operationType) {
        List<OperationStrategy> strategies = new ArrayList<>();
        switch (operationType) {
            case DEPOSIT -> {
                strategies.add(new CheckingDepositStrategy());
                strategies.add(new SavingDepositStrategy());

            }
            case WITHDRAW -> {
                strategies.add(new CheckingWithdrawStrategy());
                strategies.add(new SavingWithdrawStrategy());
            }
            default -> throw new IllegalArgumentException("Unknow strategies type");
        }
        return (List<T>)strategies;
    }
}
