package com.duong.bank.unitTest.controller;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetBankAccountTest extends AbstractBankAccountController {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void getBankAccountById_ShouldReturn200() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.CHECKING);
        checkingAccount.setId(UUID.randomUUID());
        UUID id = checkingAccount.getId();

        //WHEN
        when(getBankAccountPort.getBankAccountById(id))
                .thenReturn(checkingAccount);

        //THEN
        mockMvc.perform(get("/api/bank-accounts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.balance").value(checkingAccount.getBalance()));
    }

    @Test
    void getAllBankAccounts_ShouldReturn200() throws Exception {
        // GIVEN
        CheckingAccount account1 = (CheckingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.CHECKING);
        account1.setId(UUID.randomUUID());

        SavingAccount account2 = (SavingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.SAVING);
        account2.setId(UUID.randomUUID());

        List<BankAccount> accounts = List.of(account1, account2);

        // WHEN
        when(getBankAccountPort.getBankAccounts()).thenReturn(accounts);

        // THEN
        mockMvc.perform(get("/api/bank-accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(accounts.size()))
                .andExpect(jsonPath("$[0].id").value(account1.getId().toString()))
                .andExpect(jsonPath("$[1].id").value(account2.getId().toString()));
    }

    @Test
    void getAllBankAccountsByOwner_ShouldReturn200() throws Exception {
        // GIVEN
        CheckingAccount account1 = (CheckingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.CHECKING);
        account1.setId(UUID.randomUUID());
        account1.setOwner("toto");

        SavingAccount account2 = (SavingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.SAVING);
        account2.setId(UUID.randomUUID());
        account2.setOwner("toto");

        SavingAccount account3 = (SavingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.SAVING);
        account2.setId(UUID.randomUUID());

        List<BankAccount> accounts = List.of(account1, account2);

        // WHEN
        when(getBankAccountPort.getBanksAccountsByOwner(account1.getOwner())).thenReturn(accounts);

        // THEN
        MvcResult result = mockMvc.perform(get("/api/bank-accounts/owner")
                        .queryParam("owner", account1.getOwner()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(account1.getId().toString()))
                .andReturn();

    }
}
