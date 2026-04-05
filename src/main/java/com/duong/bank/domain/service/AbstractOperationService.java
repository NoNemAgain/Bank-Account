package com.duong.bank.domain.service;

import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.adapter.out.repository.OperationRepository;
import com.duong.bank.domain.exception.ObjectNotfoundException;
import com.duong.bank.domain.exception.ObjectType;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.Operation;
import com.duong.bank.domain.model.OperationType;
import com.duong.bank.domain.observer.BankAccountOperationObserver;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public abstract class AbstractOperationService<S extends OperationStrategy> {
    protected final BankAccountRepository accountRepository;
    protected final Map<AccountType, S> strategies;
    private final List<BankAccountOperationObserver> observers = new ArrayList<>();
    protected final OperationRepository operationRepository;

    protected AbstractOperationService(BankAccountRepository bankAccountRepository,
                                       OperationRepository operationRepository,
                                       List<S> strategyList) {
        this.accountRepository = bankAccountRepository;
        this.operationRepository = operationRepository;
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(OperationStrategy::getAccountType, s -> s));
    }

    public S getStrategyFor(BankAccount bankAccount) {
        return this.strategies.get(bankAccount.getTypeBank());
    }

    public BankAccount getAccountById(UUID bankAccountId) {
        BankAccount bankAccount = accountRepository.findById(bankAccountId)
                .orElseThrow(() -> new ObjectNotfoundException(ObjectType.BANK));

        return bankAccount;
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
