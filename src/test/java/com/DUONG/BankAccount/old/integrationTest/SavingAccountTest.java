package com.DUONG.BankAccount.old.integrationTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class SavingAccountTest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SavingAccountService savingAccountService;

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    @Test
    void withdraw_should_have_200() throws Exception {
        //Given
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(1000);
        SavingAccount saveSavingAccount = savingAccountRepository.save(savingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/savingAccount/withdraw/" + saveSavingAccount.getId())
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("800.0"));
    }

    @Test
    void withdraw__negative_amount_should_have_400() throws Exception {
        //Given
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(1000);
        SavingAccount saveSavingAccount = savingAccountRepository.save(savingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/savingAccount/withdraw/" + saveSavingAccount.getId())
                        .param("amount", "-200"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Le montant à retirer doit être positif."));
    }

    @Test
    void deposit_should_have_200() throws Exception {
        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(1000);
        savingAccount.setBalanceLimit(10000);
        SavingAccount saveSavingAccount = savingAccountRepository.save(savingAccount);

        //WHEN && THEN
        mockMvc.perform(post("/api/savingAccount/deposit/" + saveSavingAccount.getId())
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("1200.0"));
    }

    @Test
    void deposit_negative_amount_should_have_400() throws Exception {
        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(1000);
        SavingAccount saveSavingAccount = savingAccountRepository.save(savingAccount);

        //WHEN && THEN
        mockMvc.perform(post("/api/savingAccount/deposit/" + saveSavingAccount.getId())
                        .param("amount", "-200"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Le montant à déposer doit être positif."));
    }

    @Test
    void deposit_exceed_balance_limit_should_have_400() throws Exception {
        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(1000);
        savingAccount.setBalanceLimit(4000);
        SavingAccount saveSavingAccount = savingAccountRepository.save(savingAccount);

        //WHEN && THEN
        mockMvc.perform(post("/api/savingAccount/deposit/" + saveSavingAccount.getId())
                        .param("amount", "6000"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Le plafond de votre compte sera dépassé, vous ne pouvez pas déposer cet argent."));
    }
    */

}
