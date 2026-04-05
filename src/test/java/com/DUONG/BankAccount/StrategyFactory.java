package com.DUONG.BankAccount;

import com.DUONG.BankAccount.domain.model.OperationType;
import com.DUONG.BankAccount.domain.service.OperationStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.CheckingDepositStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.DepositStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.SavingDepositStrategy;
import com.DUONG.BankAccount.domain.service.withdraw.strategy.CheckingWithdrawStrategy;
import com.DUONG.BankAccount.domain.service.withdraw.strategy.SavingWithdrawStrategy;
import com.DUONG.BankAccount.domain.service.withdraw.strategy.WithdrawStrategy;

import java.util.List;

public class StrategyFactory {
    public static List<DepositStrategy> allDepositStrategies() {
        return List.of(
                new CheckingDepositStrategy(),
                new SavingDepositStrategy()
        );
    }

    public static List<WithdrawStrategy> allWithdrawStrategies() {
        return List.of(
                new CheckingWithdrawStrategy(),
                new SavingWithdrawStrategy()
        );
    }

    // Méthode générique si tu veux garder le switch
    public static <S extends OperationStrategy> List<S> strategyListCreate(OperationType operationType) {
        switch (operationType) {
            case DEPOSIT:
                return (List<S>) allDepositStrategies();
            case WITHDRAW:
                return (List<S>) allWithdrawStrategies();
            default:
                throw new IllegalArgumentException("Unknown strategies type");
        }
    }
}
