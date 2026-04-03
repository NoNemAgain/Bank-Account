package com.DUONG.BankAccount.old.e2E;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BankE2ETest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Autowired
    private SavingAccountRepository savingAccountRepository;


    @Test
    void test_bank_flow_checking_account_with_exceed_allowed() throws Exception {
        // 1. On crée le compte épargne pour Tom
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);
        checkingAccount.setExceedAllowed(true);
        checkingAccount.setExceedBalance(-2000);
        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);

        // 2. Tom a reçu son salaire (1 opération)
        mockMvc.perform(post("/api/checkingAccount/deposit/" + saveCheckingAccount.getId())
                        .param("amount", "2000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("3000.0"));

        // 3. Tom effectue des dépenses (3 opérations)
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "600"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("2400.0"));

        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("1600.0"));

        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "50"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("1550.0"));

        //4 Tom recoit de l'argent de sa famille (1 opération)
        mockMvc.perform(post("/api/checkingAccount/deposit/" + saveCheckingAccount.getId())
                        .param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("1650.0"));

        // 5. Tentative d’achat d’un ordinateur performant, mais le montant est trop élevé
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "4650"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Vous n’avez pas assez d’argent sur votre compte : vous ne pouvez pas retirer cet argent."));


        // 6. Tom se rabat sur un modèle moins onéreux, mais passe à découvert (1 opération)
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "2650"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("-1000.0"));


        // 7. Tom souhaite consulter son bilan mensuel (6 opérations au total)

        mockMvc.perform(post("/api/bankStatement/generate/" + saveCheckingAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operations.length()").value("6"))
                .andExpect(jsonPath("$.operations.[0].montant").value("-2650.0"));


    }

    @Test
    void test_bank_flow_checking_account_without_exceed_allowed() throws Exception {
        //1 On crée le compte epargne pour Alice
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(400);
        checkingAccount.setExceedAllowed(false);
        checkingAccount.setExceedBalance(-9000);
        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);


        // 2. Alice a reçu son salaire (1 opération)
        mockMvc.perform(post("/api/checkingAccount/deposit/" + saveCheckingAccount.getId())
                        .param("amount", "2600"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("3000.0"));


        // 3. Alice effectue des dépenses (1 opération)
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "600"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("2400.0"));


        // 4. Alice veut acheter une voiture, mais l’opération est bloquée car son compte passerait en négatif
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "8000"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Vous n’avez pas le droit d’être à découvert."));


        // 5. Alice contacte sa banque pour demander une autorisation de découvert
        saveCheckingAccount = checkingAccountRepository.findById(saveCheckingAccount.getId())
                .orElseThrow(() -> new ObjectNotfoundException("Compte Bancaire introuvable."));
        saveCheckingAccount.setExceedAllowed(true);

        saveCheckingAccount = checkingAccountRepository.save(saveCheckingAccount);


        // 6. Alice achète sa voiture (1 opération)
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "8000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("-5600.0"));


        // 7. Alice consulte son bilan mensuel (4 opérations au total)
        mockMvc.perform(post("/api/bankStatement/generate/" + saveCheckingAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operations.length()").value("3"))
                .andExpect(jsonPath("$.operations.[0].montant").value("-8000.0"))
                .andExpect(jsonPath("$.balance").value("-5600.0"));


    }

    @Test
    void test_bank_flow_saving_account() throws Exception {
        // 1. On crée le compte épargne pour Mathilde
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(20000);
        savingAccount.setBalanceLimit(22500);

        SavingAccount saveSavingAccount = savingAccountRepository.save(savingAccount);

        // 2. Mathilde a reçu son salaire, mais cela fait dépasser son plafond
        mockMvc.perform(post("/api/savingAccount/deposit/" + saveSavingAccount.getId())
                        .param("amount", "3000"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Le plafond de votre compte sera dépassé, vous ne pouvez pas déposer cet argent."));

        // 3. Mathilde demande à sa banque d'augmenter son plafond pour recevoir son salaire
        saveSavingAccount = savingAccountRepository.findById(saveSavingAccount.getId())
                .orElseThrow(() -> new ObjectNotfoundException("Compte Bancaire introuvable."));
        saveSavingAccount.setBalanceLimit(40000);

        saveSavingAccount = savingAccountRepository.save(saveSavingAccount);

        // 4. Mathilde dépose son salaire (1 opération)
        mockMvc.perform(post("/api/savingAccount/deposit/" + saveSavingAccount.getId())
                        .param("amount", "3000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("23000.0"));

        // 5. Mathilde dépense son argent (3 opérations)
        mockMvc.perform(post("/api/savingAccount/withdraw/" + saveSavingAccount.getId())
                        .param("amount", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("22900.0"));

        mockMvc.perform(post("/api/savingAccount/withdraw/" + saveSavingAccount.getId())
                        .param("amount", "500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("22400.0"));


        mockMvc.perform(post("/api/savingAccount/withdraw/" + saveSavingAccount.getId())
                        .param("amount", "4200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("18200.0"));

        // 6. Mathilde dépose de l'argent à nouveau (1 opération)
        mockMvc.perform(post("/api/savingAccount/deposit/" + saveSavingAccount.getId())
                        .param("amount", "800"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("19000.0"));


        // 7. Mathilde veut acheter une voiture deluxe, mais l'opération est bloquée

        mockMvc.perform(post("/api/savingAccount/withdraw/" + saveSavingAccount.getId())
                        .param("amount", "30000"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Vous ne pouvez pas retirer cet argent : vous n’avez pas assez d’argent sur votre compte."));

        // 8. Mathilde consulte son bilan mensuel (5 opérations au total)
        mockMvc.perform(post("/api/bankStatement/generate/" + saveSavingAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operations.length()").value("5"))
                .andExpect(jsonPath("$.operations.[0].montant").value("800.0"))
                .andExpect(jsonPath("$.balance").value("19000.0"));
    }
    */
}
