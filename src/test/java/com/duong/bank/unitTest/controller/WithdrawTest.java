package com.duong.bank.unitTest.controller;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.CheckingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WithdrawTest extends AbstractBankAccountController {

    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    void withdraw_ShouldHave200() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.CHECKING);
        checkingAccount.setId(UUID.randomUUID());
        UUID id = checkingAccount.getId();

        //WHEN
        when(withdrawPort.withdraw(id, new BigDecimal("200")))
                .thenReturn(checkingAccount);

        //THEN

        mockMvc.perform(patch("/api/bank-accounts/" + id + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(checkingAccount.getId().toString()));
    }
}
