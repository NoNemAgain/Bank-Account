package com.duong.bank.unitTest.service;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.StrategyFactory;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.adapter.out.repository.OperationRepository;
import com.duong.bank.domain.exception.InsufficientFundsBalanceException;
import com.duong.bank.domain.exception.InvalidAmountException;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.OperationType;
import com.duong.bank.domain.model.SavingAccount;
import com.duong.bank.domain.service.withdraw.service.WithdrawService;
import com.duong.bank.domain.service.withdraw.strategy.WithdrawStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ActiveProfiles("test")
public class WithdrawTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private OperationRepository operationRepository;

    private WithdrawService withdrawService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<WithdrawStrategy> strategies = StrategyFactory.strategyListCreate(OperationType.WITHDRAW);

        withdrawService = new WithdrawService(bankAccountRepository, operationRepository, strategies);
    }

    @Test
    void withdrawSavingAccount_shouldDecreaseBalance_whenAmountIsPositive() {
        //GIVEN
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        UUID id = savingAccount.getId();
        savingAccount.setBalance(new BigDecimal("1000.00"));

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(savingAccount));
        withdrawService.withdraw(id, new BigDecimal("200.00"));

        //THEN
        assertEquals(new BigDecimal("800.00"), savingAccount.getBalance());
        assertEquals(OperationType.WITHDRAW, savingAccount.getOperationsHistory().get(0).getType());
        assertEquals(1, savingAccount.getOperationsHistory().size());
        assertEquals(new BigDecimal("-200.00"), savingAccount.getOperationsHistory().get(0).getAmount());
    }

    @Test
    void withdrawCheckingAccount_shouldDecreaseBalance_whenAmountIsPositiveAndExceedBalanceIsLower() {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        UUID id = checkingAccount.getId();
        checkingAccount.setBalance(new BigDecimal("1000.00"));
        checkingAccount.setOverdraftAllowed(true);
        checkingAccount.setOverdraftLimit(new BigDecimal("-2000.00"));

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(checkingAccount));
        withdrawService.withdraw(id, new BigDecimal("300.00"));

        //THEN
        assertEquals(new BigDecimal("700.00"), checkingAccount.getBalance());
    }

    @Test
    void withdrawSavingAccount_shouldThrowError_whenAmountIsNegative() {
        //GIVEN
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        UUID id = savingAccount.getId();
        savingAccount.setBalance(new BigDecimal("1000.00"));
        savingAccount.setBalanceLimit(new BigDecimal("3000.00"));

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(savingAccount));

        //THEN
        assertThrows(InvalidAmountException.class, () -> {
            withdrawService.withdraw(savingAccount.getId(), new BigDecimal("-200.00"));
        });
    }

    @Test
    void withdrawCheckingAccount_shouldThrowError_whenOverdaftIsExceeded() {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        UUID id = checkingAccount.getId();
        checkingAccount.setBalance(new BigDecimal("1000"));
        checkingAccount.setOverdraftAllowed(true);
        checkingAccount.setOverdraftLimit(new BigDecimal("-2000"));

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(checkingAccount));

        //THEN
        assertThrows(InsufficientFundsBalanceException.class, () -> {
            withdrawService.withdraw(id, new BigDecimal("5000.00"));
        });
    }

    @Test
    void withdrawCheckingAccount_shouldThrowError_whenOverdaftIsNotExceededButOverdraftIsNotAllowed() {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        UUID id = checkingAccount.getId();
        checkingAccount.setBalance(new BigDecimal("1000"));
        checkingAccount.setOverdraftAllowed(false);
        checkingAccount.setOverdraftLimit(new BigDecimal("-20000"));

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(checkingAccount));

        //THEN
        assertThrows(InsufficientFundsBalanceException.class, () -> {
            withdrawService.withdraw(id, new BigDecimal("5000.00"));
        });
    }
}
