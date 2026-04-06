package com.duong.bank.E2E;

import com.duong.bank.AbstractIntegrationTest;
import com.duong.bank.BankAccountFactory;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.domain.exception.ObjectNotfoundException;
import com.duong.bank.domain.exception.ObjectType;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.domain.model.SavingAccount;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankE2ETest extends AbstractIntegrationTest {

    private MockMvc mockMvc;

    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        bankAccountRepository = context.getBean(BankAccountRepository.class);
    }

    @Test
    void testCheckingAccount_WhenSimulatingNormalUser() throws Exception {
        //1 On crée le compte epargne pour Alice
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.CHECKING);
        checkingAccount.setBalance(new BigDecimal("400.00"));
        checkingAccount.setOverdraftAllowed(false);
        checkingAccount.setOverdraftLimit(new BigDecimal("-22000.00"));

        CheckingAccount saveCheckingAccount = bankAccountRepository.save(checkingAccount);

        // 2. Alice a reçu son salaire (1 opération)
        mockMvc.perform(patch("/api/bank-accounts/" + saveCheckingAccount.getId() + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2600.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(3000));

        // 3. Alice effectue des dépenses (1 opération)
        mockMvc.perform(patch("/api/bank-accounts/" + saveCheckingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2200.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(800));

        // 4. Alice veut acheter une voiture, mais l’opération est bloquée car son compte passerait en négatif
        mockMvc.perform(patch("/api/bank-accounts/" + saveCheckingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("12000.00"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(Matchers.containsString("sufficient funds")));

        // 5. Alice contacte sa banque pour demander une autorisation de découvert
        saveCheckingAccount = (CheckingAccount) bankAccountRepository.findById(saveCheckingAccount.getId())
                .orElseThrow(() -> new ObjectNotfoundException(ObjectType.BANK));
        saveCheckingAccount.setOverdraftAllowed(true);

        saveCheckingAccount = bankAccountRepository.save(saveCheckingAccount);

        // 6. Alice achète sa voiture (1 opération)
        mockMvc.perform(patch("/api/bank-accounts/" + saveCheckingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("12000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(-11200.00));

        // 7. Alice consulte son bilan mensuel (4 opérations au total)
        mockMvc.perform(post("/api/bankStatements/" + saveCheckingAccount.getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.operations.length()").value("3"))
                .andExpect(jsonPath("$.operations.[0].montant").value("-12000.0"))
                .andExpect(jsonPath("$.balance").value(-11200.00));
    }

    @Test
    void testSavingAccount_WhenSimulatingNormalUser() throws Exception {
        // 1. On crée le compte épargne pour Mathilde
        SavingAccount savingAccount = (SavingAccount) BankAccountFactory.bankAccountCreateTest(AccountType.SAVING);
        savingAccount.setBalance(new BigDecimal("20000"));
        savingAccount.setBalanceLimit(new BigDecimal("22500"));

        SavingAccount saveSavingAccount = bankAccountRepository.save(savingAccount);

        // 2. Mathilde a reçu son salaire, mais cela fait dépasser son plafond
        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("3000.00"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(Matchers.containsString("balance limit")));

        // 3. Mathilde demande à sa banque d'augmenter son plafond pour recevoir son salaire
        saveSavingAccount = (SavingAccount) bankAccountRepository.findById(saveSavingAccount.getId())
                .orElseThrow(() -> new ObjectNotfoundException(ObjectType.BANK));
        saveSavingAccount.setBalanceLimit(new BigDecimal("40000"));

        saveSavingAccount = bankAccountRepository.save(saveSavingAccount);

        // 4. Mathilde dépose son salaire (1 opération)
        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("3000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(23000));

        // 5. Mathilde dépense son argent (3 opérations)
        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("200.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(22800));

        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("600.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(22200));

        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("4200.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(18000));

        // 6. Mathilde dépose de l'argent à nouveau (1 opération)
        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1000.00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(19000));

        // 7. Mathilde veut acheter une voiture deluxe, mais l'opération est bloquée

        mockMvc.perform(patch("/api/bank-accounts/" + saveSavingAccount.getId() + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("50000.00"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(Matchers.containsString("sufficient funds")));

        // 8. Mathilde consulte son bilan mensuel (5 opérations au total)
        mockMvc.perform(post("/api/bankStatements/" + saveSavingAccount.getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.operations.length()").value("5"))
                .andExpect(jsonPath("$.operations.[0].montant").value(1000))
                .andExpect(jsonPath("$.balance").value(19000.00));
    }
}
