package com.duong.bank.domain.service.withdraw.service;

import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.adapter.out.repository.OperationRepository;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.Operation;
import com.duong.bank.domain.model.OperationType;
import com.duong.bank.domain.service.AbstractOperationService;
import com.duong.bank.domain.service.withdraw.strategy.WithdrawStrategy;
import com.duong.bank.port.in.WithdrawPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
public class WithdrawService extends AbstractOperationService<WithdrawStrategy> implements WithdrawPort {

    public WithdrawService(BankAccountRepository accountRepository, OperationRepository operationRepository, List<WithdrawStrategy> strategyList) {
        super(accountRepository, operationRepository, strategyList);
    }

    @Override
    public BankAccount withdraw(UUID accountId, BigDecimal amount) {

        BankAccount bankAccount = getAccountById(accountId);
        WithdrawStrategy strategy = getStrategyFor(bankAccount);

        strategy.withdraw(bankAccount, amount);

        Operation operation = createOperation(bankAccount, amount, OperationType.WITHDRAW);
        notifyObservers(bankAccount, operation);
        operationRepository.save(operation);

        saveBankAccount(bankAccount);
        return bankAccount;
    }


}
