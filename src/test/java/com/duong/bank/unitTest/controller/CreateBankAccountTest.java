package com.duong.bank.unitTest.controller;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.adapter.in.dto.request.BankAccountRequest;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.CheckingAccount;
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
    void createBankAccount_ShouldReturn200() throws Exception {
        // GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        checkingAccount.setId(UUID.randomUUID());

        BankAccountRequest bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setTypeBank(checkingAccount.getTypeBank());
        bankAccountRequest.setBalance(checkingAccount.getBalance());

        String json = new ObjectMapper().writeValueAsString(bankAccountRequest);

        // WHEN
        when(createBankAccountPort.createBankAccount(any(BankAccount.class)))
                .thenReturn(checkingAccount);

        // THEN
        mockMvc.perform(post("/api/bank-accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(checkingAccount.getId().toString()))
                .andExpect(jsonPath("$.balance").value(checkingAccount.getBalance()));
    }
}
