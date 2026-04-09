package com.duong.bank;

import com.duong.bank.domain.model.OperationType;
import com.duong.bank.domain.useCase.OperationStrategy;
import com.duong.bank.domain.useCase.deposit.strategy.CheckingDepositStrategy;
import com.duong.bank.domain.useCase.deposit.strategy.DepositStrategy;
import com.duong.bank.domain.useCase.deposit.strategy.SavingDepositStrategy;
import com.duong.bank.domain.useCase.withdraw.strategy.CheckingWithdrawStrategy;
import com.duong.bank.domain.useCase.withdraw.strategy.SavingWithdrawStrategy;
import com.duong.bank.domain.useCase.withdraw.strategy.WithdrawStrategy;

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
