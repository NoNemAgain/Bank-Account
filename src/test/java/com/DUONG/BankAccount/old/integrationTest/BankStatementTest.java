package com.DUONG.BankAccount.old.integrationTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class BankStatementTest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BankStatementService bankStatementService;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Test
    void test_generate_bank_statement_should_have_200() throws Exception {
        //Given
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);
        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/bankStatement/generate/" + saveCheckingAccount.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountType").value("CheckingAccount"));

    }

    @Test
    void test_generate_bank_statement_should_have_400() throws Exception {
        //Given
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);
        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/bankStatement/generate/" + saveCheckingAccount.getId() + 1))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Compte Bancaire introuvable."));

    }
    */
}
