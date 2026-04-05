package com.duong.bank.unitTest.service;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.StrategyFactory;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.adapter.out.repository.OperationRepository;
import com.duong.bank.domain.exception.ExceedLimitBalanceException;
import com.duong.bank.domain.exception.InvalidAmountException;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.OperationType;
import com.duong.bank.domain.model.SavingAccount;
import com.duong.bank.domain.service.deposit.service.DepositService;
import com.duong.bank.domain.service.deposit.strategy.DepositStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DepositTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private OperationRepository operationRepository;

    private DepositService depositService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<DepositStrategy> strategies = StrategyFactory.strategyListCreate(OperationType.DEPOSIT);

        depositService = new DepositService(bankAccountRepository, operationRepository, strategies);
    }

    @Test
    void depositCheckingAccount_shouldIncreaseBalance_whenAmountIsPositive() {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        UUID id = checkingAccount.getId();
        checkingAccount.setBalance(new BigDecimal("1000.00"));

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(checkingAccount));
        depositService.deposit(id, new BigDecimal("200.00"));

        //THEN
        assertEquals(new BigDecimal("1200.00"), checkingAccount.getBalance());
        assertEquals(OperationType.DEPOSIT, checkingAccount.getOperationsHistory().get(0).getType());
        assertEquals(1, checkingAccount.getOperationsHistory().size());
        assertEquals(new BigDecimal("200.00"), checkingAccount.getOperationsHistory().get(0).getAmount());
    }

    @Test
    void depositSavingAccount_shouldIncreaseBalance_whenAmountIsPositiveAndBalanceLimitIsHigher() {
        //GIVEN
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        UUID id = savingAccount.getId();
        savingAccount.setBalance(new BigDecimal("1000.00"));
        savingAccount.setBalanceLimit(new BigDecimal("22500.00"));

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(savingAccount));
        depositService.deposit(id, new BigDecimal("200.00"));

        //THEN
        assertEquals(new BigDecimal("1200.00"), savingAccount.getBalance());
    }

    @Test
    void depositSavingAccount_shouldThrowError_whenAmountIsNegative() {
        //GIVEN
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);

        //WHEN
        when(bankAccountRepository.findById(savingAccount.getId())).thenReturn(Optional.of(savingAccount));

        //THEN
        assertThrows(InvalidAmountException.class, () -> {
            depositService.deposit(savingAccount.getId(), new BigDecimal("-200.00"));
        });
    }

    @Test
    void depositSavingAccount_shouldThrowError_whenBalanceLimitIsExceeded() {
        //GIVEN
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        savingAccount.setBalanceLimit(new BigDecimal("-2000.00"));

        //WHEN
        when(bankAccountRepository.findById(savingAccount.getId())).thenReturn(Optional.of(savingAccount));

        //THEN
        assertThrows(ExceedLimitBalanceException.class, () -> {
            depositService.deposit(savingAccount.getId(), new BigDecimal("500.00"));
        });
    }
}
