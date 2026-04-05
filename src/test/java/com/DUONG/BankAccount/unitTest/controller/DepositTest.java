package com.DUONG.BankAccount.unitTest.controller;

import com.DUONG.BankAccount.BankAccountFactory;
import com.DUONG.BankAccount.adapter.in.controller.BankAccountController;
import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.domain.model.AccountType;
import com.DUONG.BankAccount.domain.model.CheckingAccount;
import com.DUONG.BankAccount.port.in.DepositPort;
import com.DUONG.BankAccount.port.in.WithdrawPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DepositTest {

    private MockMvc mockMvc;
    private BankAccountRepository bankAccountRepository;
    private DepositPort depositPort;
    private WithdrawPort withdrawPort;

    private BankAccountController bankAccountController;

    @BeforeEach
    void setUp() {
        depositPort = Mockito.mock(DepositPort.class);
        withdrawPort = Mockito.mock(WithdrawPort.class);
        bankAccountController = new BankAccountController(depositPort, withdrawPort);

        mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
    }

    @Test
    void deposit_ShouldHave200() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.CHECKING);
        checkingAccount.setId(UUID.randomUUID());
        UUID id = checkingAccount.getId();

        //WHEN
        when(depositPort.deposit(id, new BigDecimal("200")))
                .thenReturn(checkingAccount);

        //THEN
        mockMvc.perform(patch("/api/bank-accounts/" + id + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }
}
