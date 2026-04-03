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

    public AbstractOperationService(BankAccountRepository accountRepository, List<S> strategyList) {
        this.accountRepository = accountRepository;
        strategies = strategyList.stream().collect(
                Collectors.toMap(s -> s.getAccountType(), s -> s));
    }

    public BankAccount getAccountById(UUID accountId) {
        BankAccount bankAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new ObjectNotfoundException("Account not found"));

        return bankAccount;
    }

    public S getStrategyFor(BankAccount account) {
        return strategies.get(account.getClass());
    }
    public void saveBankAccount(BankAccount bankAccount) {
        accountRepository.save(bankAccount);
    }
}
