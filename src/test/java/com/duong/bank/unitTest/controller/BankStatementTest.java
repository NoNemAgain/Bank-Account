package com.duong.bank.unitTest.controller;

import com.duong.bank.BankAccountFactory;
import com.duong.bank.adapter.in.controller.BankStatementController;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.domain.model.AccountType;
import com.duong.bank.domain.model.BankStatement;
import com.duong.bank.domain.model.CheckingAccount;
import com.duong.bank.port.in.CreateBankStatementPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BankStatementTest {

    private MockMvc mockMvc;

    private BankAccountRepository bankAccountRepository;
    private CreateBankStatementPort createBankStatementPort;

    private BankStatementController bankStatementController;

    @BeforeEach
    void setUp() {
        createBankStatementPort = Mockito.mock(CreateBankStatementPort.class);
        bankAccountRepository = Mockito.mock(BankAccountRepository.class);

        bankStatementController = new BankStatementController(createBankStatementPort);

        mockMvc = MockMvcBuilders.standaloneSetup(bankStatementController).build();
    }

    @Test
    void createBankStatement_shouldHave201() throws Exception {
        //Given
        CheckingAccount checkingAccount = (CheckingAccount) BankAccountFactory
                .bankAccountCreateTest(AccountType.CHECKING);
        checkingAccount.setId(UUID.randomUUID());
        BankStatement bankStatement = new BankStatement();
        bankStatement.setId(UUID.randomUUID());
        bankStatement.setDate(LocalDateTime.now());
        bankStatement.setBankAccount(checkingAccount);
        bankStatement.setBalance(checkingAccount.getBalance());
        bankStatement.setAccountType(checkingAccount.getTypeBank());
        bankStatement.setOperations(checkingAccount.getOperationsHistory());

        //WHEN
        when(createBankStatementPort.createBankStatement(checkingAccount.getId()))
                .thenReturn(bankStatement);

        //THEN
        mockMvc.perform(post("/api/bankStatements/" + checkingAccount.getId()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountType").value(AccountType.CHECKING.toString()));
    }
}
