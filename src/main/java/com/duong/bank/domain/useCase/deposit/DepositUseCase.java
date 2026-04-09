package com.duong.bank.domain.useCase.deposit;

import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.adapter.out.repository.OperationRepository;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.Operation;
import com.duong.bank.domain.model.OperationType;
import com.duong.bank.domain.useCase.AbstractOperationService;
import com.duong.bank.domain.useCase.deposit.strategy.DepositStrategy;
import com.duong.bank.port.in.DepositPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Service
public class DepositUseCase extends AbstractOperationService<DepositStrategy> implements DepositPort {
    public DepositUseCase(BankAccountRepository accountRepository, OperationRepository operationRepository, List<DepositStrategy> strategyList) {
        super(accountRepository, operationRepository, strategyList);
    }

    @Override
    public BankAccount deposit(UUID accountId, BigDecimal amount) {
        BankAccount bankAccount = getAccountById(accountId);
        DepositStrategy strategy = getStrategyFor(bankAccount);

        strategy.deposit(bankAccount, amount);

        Operation operation = createOperation(bankAccount, amount, OperationType.DEPOSIT);
        notifyObservers(bankAccount, operation);
        operationRepository.save(operation);

        saveBankAccount(bankAccount);
        return bankAccount;
    }
}
