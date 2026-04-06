package com.duong.bank.IntegrationTest;

import com.duong.bank.AbstractIntegrationTest;
import com.duong.bank.BankAccountFactory;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DepositTest extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        bankAccountRepository = context.getBean(BankAccountRepository.class);
    }

    @Test
    void deposit_ShouldHave200() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        CheckingAccount saveCheckingAccount = bankAccountRepository.save(checkingAccount);


        //WHEN && THEN
        mockMvc.perform(patch("/api/bank-accounts/" + saveCheckingAccount.getId() + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("1200.0"));
    }

    @Test
    void deposit_ShouldHave400_WhenAmountIsNegative() throws Exception {
        //GIVEN
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        SavingAccount saveSavingAccount = bankAccountRepository.save(savingAccount);

        //WHEN && THEN
        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("-200"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("The amount must be positive"));
    }
}
