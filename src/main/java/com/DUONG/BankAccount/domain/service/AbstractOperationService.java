package com.DUONG.BankAccount.domain.service;

import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.domain.exception.ObjectNotfoundException;
import com.DUONG.BankAccount.domain.model.BankAccount;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class AbstractOperationService<S extends OperationStrategy> {
    private final BankAccountRepository accountRepository;
    private final Map<Class<? extends BankAccount>, S> strategies;

    public AbstractOperationService(BankAccountRepository bankAccountRepository, List<S> strategyList) {
        this.accountRepository = bankAccountRepository;
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
}
