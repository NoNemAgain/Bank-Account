package com.DUONG.BankAccount.domain.service.withdraw.service;

import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.adapter.out.repository.OperationRepository;
import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.model.Operation;
import com.DUONG.BankAccount.domain.model.OperationType;
import com.DUONG.BankAccount.domain.service.AbstractOperationService;
import com.DUONG.BankAccount.domain.service.withdraw.strategy.WithdrawStrategy;
import com.DUONG.BankAccount.port.in.WithdrawPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class WithdrawService extends AbstractOperationService<WithdrawStrategy> implements WithdrawPort {

    public WithdrawService(BankAccountRepository accountRepository, OperationRepository operationRepository, List<WithdrawStrategy> strategyList) {
        super(accountRepository, operationRepository, strategyList);
    }

    @Override
    public void withdraw(UUID accountId, BigDecimal amount) {
        BankAccount bankAccount = getAccountById(accountId);
        WithdrawStrategy strategy = getStrategyFor(bankAccount);

        strategy.withdraw(bankAccount, amount);

        Operation operation = createOperation(bankAccount, amount, OperationType.WITHDRAW);
        notifyObservers(bankAccount, operation);
        operationRepository.save(operation);

        saveBankAccount(bankAccount);
    }


}
