package com.DUONG.BankAccount.old.unitTest.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BankStatementServiceTest {
    /*
    @Mock
    private OperationRepository operationRepository;


    @Mock
    private BankStatementRepository bankStatementRepository;

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankStatementService bankStatementService;

    @InjectMocks
    private OperationService operationService;


    @Test
    void generate_bank_statement_with_saving_account() {
        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(10L);
        savingAccount.setBalance(1000);
        Operation operation1 = new Operation();
        operation1.setId(8L);
        operation1.setDate(LocalDateTime.now().minusDays(7));
        savingAccount.addOperation(operation1);
        savingAccount.addOperation(operation1);

        //WHEN
        when(bankAccountRepository.findById(10L)).thenReturn(Optional.of(savingAccount));
        BankStatement bankStatement = bankStatementService.generateBankStatement(10L);

        //THEN
        assertEquals(10L, bankStatement.getBankAccount().getId());
        assertEquals(1000, bankStatement.getBalance());
        assertEquals("SavingAccount", bankStatement.getAccountType());
        assertEquals(8, bankStatement.getOperations().get(0).getId());
    }

    @Test
    void generate_bank_statement_with_checking_account() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(10L);
        checkingAccount.setBalance(1000);
        Operation operation1 = new Operation();
        operation1.setId(8L);
        operation1.setDate(LocalDateTime.now().minusDays(7));
        checkingAccount.addOperation(operation1);

        //WHEN
        when(bankAccountRepository.findById(10L)).thenReturn(Optional.of(checkingAccount));
        BankStatement bankStatement = bankStatementService.generateBankStatement(10L);

        //THEN
        assertEquals(10L, bankStatement.getBankAccount().getId());
        assertEquals(1000, bankStatement.getBalance());
        assertEquals("CheckingAccount", bankStatement.getAccountType());
        assertEquals(8, bankStatement.getOperations().get(0).getId());
    }

    @Test
    void generate_bank_statement_and_check_only_last_month_kept() {

        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(10L);

        Operation operation1 = new Operation();
        operation1.setId(35L);
        operation1.setDate(LocalDateTime.now().minusDays(35));
        checkingAccount.addOperation(operation1);


        Operation operation2 = new Operation();
        operation2.setId(7L);
        operation2.setDate(LocalDateTime.now().minusDays(7));
        checkingAccount.addOperation(operation2);


        Operation operation3 = new Operation();
        operation3.setId(60L);
        operation3.setDate(LocalDateTime.now().minusDays(60));
        checkingAccount.addOperation(operation3);

        Operation operation4 = new Operation();
        operation4.setId(29L);
        operation4.setDate(LocalDateTime.now().minusDays(21));
        checkingAccount.addOperation(operation4);

        //WHEN
        when(bankAccountRepository.findById(10L)).thenReturn(Optional.of(checkingAccount));
        BankStatement bankStatement = bankStatementService.generateBankStatement(10L);

        //THEN
        // Les opérations de plus d’un mois ont été supprimées
        assertEquals(2, bankStatement.getOperations().size());
    }

    @Test
    void generate_bank_statement_and_check_desc_date() {

        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(10L);


        // milieu
        Operation operation2 = new Operation();
        operation2.setId(7L);
        operation2.setDate(LocalDateTime.now().minusDays(7));
        checkingAccount.addOperation(operation2);

        // Le plus vieux
        Operation operation4 = new Operation();
        operation4.setId(29L);
        operation4.setDate(LocalDateTime.now().minusDays(21));
        checkingAccount.addOperation(operation4);

        // Le plus récent
        Operation operation1 = new Operation();
        operation1.setId(6L);
        operation1.setDate(LocalDateTime.now().minusDays(6));
        checkingAccount.addOperation(operation1);

        //WHEN
        when(bankAccountRepository.findById(10L)).thenReturn(Optional.of(checkingAccount));
        BankStatement bankStatement = bankStatementService.generateBankStatement(10L);

        //THEN
        // Les opérations sont triées dans l’ordre antéchronologique
        assertEquals(6L, bankStatement.getOperations().get(0).getId());
        assertEquals(7L, bankStatement.getOperations().get(1).getId());
        assertEquals(29L, bankStatement.getOperations().get(2).getId());


    }
     */
}
