package com.DUONG.BankAccount.domain.service.withdraw;

import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.domain.model.BankAccount;
import com.DUONG.BankAccount.domain.service.AbstractOperationService;
import com.DUONG.BankAccount.port.in.WithdrawPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class WithdrawService extends AbstractOperationService<WithdrawStrategy> implements WithdrawPort {

    public WithdrawService(BankAccountRepository accountRepository, List<WithdrawStrategy> strategyList) {
        super(accountRepository, strategyList);
    }

    @Override
    public void withdraw(UUID accountId, BigDecimal amount) {
        BankAccount bankAccount = getAccountById(accountId);
        WithdrawStrategy strategy = getStrategyFor(bankAccount);

        strategy.withdraw(bankAccount, amount);

        saveBankAccount(bankAccount);
    }
}
