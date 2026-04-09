package com.duong.bank.unitTest.controller;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.adapter.in.dto.request.BankAccountRequest;
import com.duong.bank.adapter.in.dto.request.CheckingAccountRequest;
import com.duong.bank.adapter.in.dto.request.SavingAccountRequest;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateBankAccountTest extends AbstractBankAccountController {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void createCheckingAccount_ShouldReturn200() throws Exception {
        // GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        checkingAccount.setId(UUID.randomUUID());

        CheckingAccountRequest checkingAccountRequest = new CheckingAccountRequest();
        checkingAccountRequest.setBalance(checkingAccount.getBalance());
        checkingAccountRequest.setOverdraftLimit(checkingAccount.getOverdraftLimit());
        checkingAccountRequest.setOverdraftAllowed(checkingAccount.isOverdraftAllowed());

        String json = new ObjectMapper().writeValueAsString(checkingAccountRequest);

        // WHEN
        when(createBankAccountPort.createBankAccount(any(CheckingAccount.class)))
                .thenReturn(checkingAccount);

        // THEN
        mockMvc.perform(post("/api/bank-accounts/checking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(checkingAccount.getId().toString()))
                .andExpect(jsonPath("$.balance").value(checkingAccount.getBalance()));
    }
    @Test
    void createSavingAccount_ShouldReturn200() throws Exception {
        // GIVEN
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        savingAccount.setId(UUID.randomUUID());

        SavingAccountRequest savingAccountRequest = new SavingAccountRequest();
        savingAccountRequest.setBalance(savingAccount.getBalance());
        savingAccountRequest.setBalanceLimit(savingAccount.getBalanceLimit());


        String json = new ObjectMapper().writeValueAsString(savingAccountRequest);

        // WHEN
        when(createBankAccountPort.createBankAccount(any(SavingAccount.class)))
                .thenReturn(savingAccount);

        // THEN
        mockMvc.perform(post("/api/bank-accounts/saving")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(savingAccount.getId().toString()))
                .andExpect(jsonPath("$.balance").value(savingAccount.getBalance()));
    }
}
