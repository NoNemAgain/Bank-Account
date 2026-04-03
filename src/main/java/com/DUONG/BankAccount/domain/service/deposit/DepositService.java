package com.DUONG.BankAccount.domain.service.deposit;

import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.service.AbstractOperationService;
import com.DUONG.BankAccount.port.in.DepositPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class DepositService extends AbstractOperationService<DepositStrategy> implements DepositPort {
    public DepositService(BankAccountRepository accountRepository, List<DepositStrategy> strategyList) {
        super(accountRepository, strategyList);
    }

    @Override
    public void deposit(UUID accountId, BigDecimal amount) {
        BankAccount bankAccount = getAccountById(accountId);
        DepositStrategy strategy = getStrategyFor(bankAccount);

        strategy.deposit(bankAccount, amount);

        saveBankAccount(bankAccount);
    }
}
