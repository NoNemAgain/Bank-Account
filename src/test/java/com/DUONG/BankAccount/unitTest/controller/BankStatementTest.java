package com.DUONG.BankAccount.unitTest.controller;

import com.DUONG.BankAccount.BankAccountFactory;
import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.domain.model.AccountType;
import com.DUONG.BankAccount.domain.model.CheckingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BankStatementTest {

    private MockMvc mockMvc;


    private BankAccountRepository bankAccountRepository;


    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        bankAccountRepository = context.getBean(BankAccountRepository.class);
    }

    @Test
    void createBankStatement_shouldHave201() throws Exception {
        //Given
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.CHECKING);

        CheckingAccount saveCheckingAccount = bankAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/bankStatements/" + saveCheckingAccount.getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountType").value(AccountType.CHECKING.toString()));

    }
}
