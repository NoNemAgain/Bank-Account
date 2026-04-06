package com.duong.bank.IntegrationTest;

import com.duong.bank.AbstractIntegrationTest;
import com.duong.bank.BankAccountFactory;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.CheckingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankStatementTest extends AbstractIntegrationTest {

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
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);

        CheckingAccount saveCheckingAccount = bankAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/bankStatements/" + saveCheckingAccount.getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountType").value(AccountType.CHECKING.toString()));

    }

    @Test
    void createBankStatement_shouldHave400_whenIdIsWrong() throws Exception {
        //Given
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);

        CheckingAccount saveCheckingAccount = bankAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/bankStatements/" + saveCheckingAccount.getId() + 1))
                .andExpect(status().isBadRequest());

    }
}
