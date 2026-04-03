package com.DUONG.BankAccount.unitTest.service;

import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.adapter.out.repository.OperationRepository;
import com.DUONG.BankAccount.domain.exception.ExceedLimitBalanceException;
import com.DUONG.BankAccount.domain.exception.InvalidAmountException;
import com.DUONG.BankAccount.domain.model.CheckingAccount;
import com.DUONG.BankAccount.domain.model.OperationType;
import com.DUONG.BankAccount.domain.model.SavingAccount;
import com.DUONG.BankAccount.domain.service.deposit.service.DepositService;
import com.DUONG.BankAccount.domain.service.deposit.strategy.CheckingDepositStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.DepositStrategy;
import com.DUONG.BankAccount.domain.service.deposit.strategy.SavingDepositStrategy;
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

        List<DepositStrategy> strategies = List.of(
                new CheckingDepositStrategy(),
                new SavingDepositStrategy()
        );

        depositService = new DepositService(bankAccountRepository,operationRepository, strategies);
    }

    @Test
    void depositCheckingAccount_shouldIncreaseBalance_whenAmountIsPositive() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(UUID.randomUUID());
        checkingAccount.setBalance(new BigDecimal("1000.00"));

        //WHEN
        when(bankAccountRepository.findById(checkingAccount.getId())).thenReturn(Optional.of(checkingAccount));
        depositService.deposit(checkingAccount.getId(), new BigDecimal("200.00"));

        //THEN
        assertEquals(new BigDecimal("1200.00"), checkingAccount.getBalance());
        assertEquals(OperationType.DEPOSIT, checkingAccount.getOperationsHistory().get(0).getType());
        assertEquals(1, checkingAccount.getOperationsHistory().size());
        assertEquals(new BigDecimal("200.00"), checkingAccount.getOperationsHistory().get(0).getAmount());
    }

    @Test
    void depositSavingAccount_shouldIncreaseBalance_whenAmountIsPositiveAndBalanceLimitIsHigher() {
        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID());
        savingAccount.setBalance(new BigDecimal("1000.00"));
        savingAccount.setBalanceLimit(new BigDecimal("3000"));

        //WHEN
        when(bankAccountRepository.findById(savingAccount.getId())).thenReturn(Optional.of(savingAccount));
        depositService.deposit(savingAccount.getId(), new BigDecimal("200.00"));

        //THEN
        assertEquals(new BigDecimal("1200.00"), savingAccount.getBalance());
    }

    @Test
    void depositSavingAccount_shouldThrowError_whenAmountIsNegative() {
        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID());
        savingAccount.setBalance(new BigDecimal("1000.00"));
        savingAccount.setBalanceLimit(new BigDecimal("3000"));

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
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(UUID.randomUUID());
        savingAccount.setBalance(new BigDecimal("1000.00"));
        savingAccount.setBalanceLimit(new BigDecimal("1400"));

        //WHEN
        when(bankAccountRepository.findById(savingAccount.getId())).thenReturn(Optional.of(savingAccount));

        //THEN
        assertThrows(ExceedLimitBalanceException.class, () -> {
            depositService.deposit(savingAccount.getId(), new BigDecimal("500.00"));
        });
    }
}
