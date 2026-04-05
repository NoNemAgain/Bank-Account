package com.duong.bank.IntegrationTest;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WithdrawTest {

    MockMvc mockMvc;

    BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        bankAccountRepository = context.getBean(BankAccountRepository.class);
    }

    @Test
    void withdraw_ShouldHave200() throws Exception {
        //Given
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        CheckingAccount saveCheckingAccount = bankAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(patch("/api/bank-accounts/" + saveCheckingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("800.0"));
    }

    @Test
    void withdraw_ShouldHave400_WhenAmountIsNegative() throws Exception {
        //Given
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        SavingAccount saveSavingAccount = bankAccountRepository.save(savingAccount);

        //WHEN //THEN
        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("-200"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("The amount must be positive"));
    }
}
