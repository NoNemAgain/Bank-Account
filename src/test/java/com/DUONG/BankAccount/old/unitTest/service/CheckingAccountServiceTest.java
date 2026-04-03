package com.DUONG.BankAccount.old.unitTest.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class CheckingAccountServiceTest {
    /*
    @Mock
    private OperationRepository operationRepository;


    @Mock
    private BankAccountRepository bankAccountRepository;

    @Mock
    private CheckingAccountRepository checkingAccountRepository;

    @Mock
    private OperationService operationService;

    @InjectMocks
    private CheckingAccountService checkingAccountService;


    @Test
    void withdraw_should_decrease_balance_with_balance_being_positive() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        checkingAccount.setBalance(1000);


        //WHEN
        when(checkingAccountRepository.findById(1L)).thenReturn(Optional.of(checkingAccount));

        checkingAccountService.withdrawMoney(1L, 200);
        //THEN
        assertEquals(800, checkingAccount.getBalance());

    }

    @Test
    void withdraw_should_decrease_balance_with_balance_being_negative_() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        checkingAccount.setBalance(1000);
        checkingAccount.setExceedAllowed(true);
        checkingAccount.setExceedBalance(-2000);

        //WHEN
        when(checkingAccountRepository.findById(1L)).thenReturn(Optional.of(checkingAccount));
        checkingAccountService.withdrawMoney(1L, 2000);

        //THEN
        assertEquals(-1000, checkingAccount.getBalance());
    }

    @Test
    void withdraw_balance_not_allowed_negative_should_generate_error() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        checkingAccount.setBalance(1000);
        checkingAccount.setExceedAllowed(false);
        //WHEN & THEN
        when(checkingAccountRepository.findById(1L)).thenReturn(Optional.of(checkingAccount));

        assertThrows(NoPermissionGivenException.class, () -> {
            checkingAccountService.withdrawMoney(1L, 20000);
        });
    }

    @Test
    void withdraw_exceed_limit_balance_should_generate_error() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        checkingAccount.setBalance(1000);
        checkingAccount.setExceedAllowed(true);
        checkingAccount.setExceedBalance(-2000);
        //WHEN && THEN
        when(checkingAccountRepository.findById(1L)).thenReturn(Optional.of(checkingAccount));
        assertThrows(InsufficientFundsBalanceException.class, () -> {
            checkingAccountService.withdrawMoney(1L, 100000);
        });
    }

    @Test
    void withdraw_negative_amount_should_generate_error() {
        // GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        checkingAccount.setBalance(1000);

        //WHEN && THEN
        when(checkingAccountRepository.findById(1L)).thenReturn(Optional.of(checkingAccount));
        assertThrows(InvalidAmountException.class, () -> {
            checkingAccountService.withdrawMoney(1L, -200);
        });
    }

    @Test
    void deposit_should_increase_balance() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        checkingAccount.setBalance(1000);

        //WHEN
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(checkingAccount));
        checkingAccountService.depositMoney(1L, 200);

        //THEN
        assertEquals(1200, checkingAccount.getBalance());
    }

    @Test
    void deposit_with_negative_amount_should_generate_error() {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        //WHEN && THEN
        when(bankAccountRepository.findById(1L)).thenReturn(Optional.of(checkingAccount));
        assertThrows(InvalidAmountException.class, () -> {
            checkingAccountService.depositMoney(1L, -200);
        });
    }

     */

}
