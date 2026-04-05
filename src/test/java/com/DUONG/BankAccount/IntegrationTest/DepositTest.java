package com.DUONG.BankAccount.IntegrationTest;

import com.DUONG.BankAccount.StrategyFactory;
import com.DUONG.BankAccount.adapter.out.repository.BankAccountRepository;
import com.DUONG.BankAccount.adapter.out.repository.OperationRepository;
import com.DUONG.BankAccount.domain.model.OperationType;
import com.DUONG.BankAccount.domain.service.deposit.service.DepositService;
import com.DUONG.BankAccount.domain.service.deposit.strategy.DepositStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class DepositTest {

    MockMvc mockMvc;

    DepositService depositService;

    OperationRepository operationRepository;

    BankAccountRepository bankAccountRepository;

    List<DepositStrategy> strategyList;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        operationRepository = context.getBean(OperationRepository.class);
        bankAccountRepository = context.getBean(BankAccountRepository.class);
        strategyList = StrategyFactory.strategyListCreate(OperationType.DEPOSIT);

        depositService = new DepositService(bankAccountRepository, operationRepository, strategyList);


    }
    /*
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CheckingAccountService checkingAccountService;

    @Autowired
    private CheckingAccountRepository checkingAccountRepository;

    @Test
    void withdraw_should_have_200() throws Exception {
        //Given
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);
        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("800.0"));
    }

    @Test
    void withdraw_negative_amount_should_have_400() throws Exception {
        //Given
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);
        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "-200"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Le montant à retirer doit être positif."));
    }

    @Test
    void withdraw_exceed_limit_amount_should_have_400() throws Exception {
        //Given
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);
        checkingAccount.setExceedAllowed(true);
        checkingAccount.setExceedBalance(400.0);
        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);

        //WHEN //THEN
        mockMvc.perform(post("/api/checkingAccount/withdraw/" + saveCheckingAccount.getId())
                        .param("amount", "2000"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Vous n’avez pas assez d’argent sur votre compte : vous ne pouvez pas retirer cet argent."));
    }

    @Test
    void deposit_should_have_200() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);

        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);


        //WHEN && THEN
        mockMvc.perform(post("/api/checkingAccount/deposit/" + saveCheckingAccount.getId())
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value("1200.0"));
    }

    @Test
    void deposit_negative_amount_should_have_400() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setBalance(1000);

        CheckingAccount saveCheckingAccount = checkingAccountRepository.save(checkingAccount);


        //WHEN && THEN
        mockMvc.perform(post("/api/checkingAccount/deposit/" + saveCheckingAccount.getId())
                        .param("amount", "-200"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Le montant à déposer doit être positif."));
    }
    */

}
