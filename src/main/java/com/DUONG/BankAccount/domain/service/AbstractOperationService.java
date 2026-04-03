package com.DUONG.BankAccount.domain.service;

import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.adapter.out.repository.OperationRepository;
import com.DUONG.BankAccount.domain.exception.ObjectNotfoundException;
import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.Operation;
import com.DUONG.BankAccount.domain.model.OperationType;
import com.DUONG.BankAccount.domain.observer.BankAccountOperationObserver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractOperationService<S extends OperationStrategy> {
    protected final BankAccountRepository accountRepository;
    private final Map<Class<? extends BankAccount>, S> strategies;
    private final List<BankAccountOperationObserver> observers = new ArrayList<>();
    protected final OperationRepository operationRepository;

    public AbstractOperationService(BankAccountRepository bankAccountRepository, OperationRepository operationRepository, List<S> strategyList) {
        this.accountRepository = bankAccountRepository;
        this.operationRepository = operationRepository;
        strategies = strategyList.stream().collect(
                Collectors.toMap(s -> s.getAccountType(), s -> s));
    }

    public BankAccount getAccountById(UUID bankAccountId) {
        BankAccount bankAccount = accountRepository.findById(bankAccountId)
                .orElseThrow(() -> new ObjectNotfoundException("Account not found"));

        return bankAccount;
    }

    public S getStrategyFor(BankAccount bankAccount) {
        return strategies.get(bankAccount.getClass());
    }

    public void saveBankAccount(BankAccount bankAccount) {
        accountRepository.save(bankAccount);
    }

    protected void notifyObservers(BankAccount account, Operation operation) {
        for (BankAccountOperationObserver observer : observers) {
            observer.onOperationCreated(account, operation);
        }
    }

    protected void addObserver(BankAccountOperationObserver observe) {
        observers.add(observe);
    }

    protected Operation createOperation(BankAccount bankAccount, BigDecimal amount, OperationType operationType) {
        Operation operation = new Operation();
        operation.setType(operationType);
        operation.setDate(LocalDateTime.now());
        operation.setAmount(operationType.adjustAmount(amount));
        operation.setBankAccount(bankAccount);
        bankAccount.addOperation(operation);
        return operation;
    }
}
