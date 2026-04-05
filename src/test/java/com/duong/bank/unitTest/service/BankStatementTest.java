package com.duong.bank.unitTest.service;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.OperationFactory;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.adapter.out.repository.BankStatementRepository;
import com.duong.bank.domain.model.*;
import com.duong.bank.domain.service.bankStatement.service.BankStatementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BankStatementTest {

    @Mock
    private BankStatementRepository bankStatementRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    private BankStatementService bankStatementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bankStatementService = new BankStatementService(bankStatementRepository, bankAccountRepository);
    }

    @Test
    void createBankStatement_shouldCreateABankStatement() {
        //GIVEN
        SavingAccount bankAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        UUID id = bankAccount.getId();
        Operation operation1 = OperationFactory.operationCreateTest(bankAccount, 7);

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(bankAccount));
        BankStatement bankStatement = bankStatementService.createBankStatement(id);

        //THEN
        assertEquals(id, bankStatement.getBankAccount().getId());
        assertEquals(bankAccount.getBalance(), bankStatement.getBalance());
        assertEquals(AccountType.SAVING, bankStatement.getAccountType());
        assertEquals(1, bankStatement.getOperations().size());
        assertEquals(operation1.getId(), bankStatement.getOperations().get(0).getId());
    }

    @Test
    void createBankStatement_shouldFilterOperationAndKeepOnlyFromLastMonth() {
        //GIVEN
        CheckingAccount bankAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        UUID id = bankAccount.getId();

        Operation operation1 = OperationFactory.operationCreateTest(bankAccount, 40);
        Operation operation2 = OperationFactory.operationCreateTest(bankAccount, 7);
        Operation operation3 = OperationFactory.operationCreateTest(bankAccount, 60);
        Operation operation4 = OperationFactory.operationCreateTest(bankAccount, 29);

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(bankAccount));
        BankStatement bankStatement = bankStatementService.createBankStatement(id);

        //THEN
        assertEquals(2, bankStatement.getOperations().size());
    }

    @Test
    void createBankStatement_shouldSortByTheMostRecent() {

        //GIVEN
        CheckingAccount bankAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        UUID id = bankAccount.getId();

        Operation operation1 = OperationFactory.operationCreateTest(bankAccount, 40);
        Operation operation2 = OperationFactory.operationCreateTest(bankAccount, 29);
        Operation operation3 = OperationFactory.operationCreateTest(bankAccount, 7);
        Operation operation4 = OperationFactory.operationCreateTest(bankAccount, 15);

        //WHEN
        when(bankAccountRepository.findById(id)).thenReturn(Optional.of(bankAccount));
        BankStatement bankStatement = bankStatementService.createBankStatement(id);

        //THEN
        assertEquals(operation3.getId(), bankStatement.getOperations().get(0).getId());
        assertEquals(operation4.getId(), bankStatement.getOperations().get(1).getId());
        assertEquals(operation2.getId(), bankStatement.getOperations().get(2).getId());
    }
}
