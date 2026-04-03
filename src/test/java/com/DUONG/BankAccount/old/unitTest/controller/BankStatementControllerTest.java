package com.DUONG.BankAccount.old.unitTest.controller;

import com.DUONG.BankAccount.adapter.in.controller.BankStatementController;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(BankStatementController.class)
public class BankStatementControllerTest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BankStatementService bankStatementService;

    @Test
    void test_generate_bank_statement_should_have_200() throws Exception {
        //Given
        BankStatement statement = new BankStatement();
        statement.setId(1L);

        //WHEN
        when(bankStatementService.generateBankStatement(1L)).thenReturn(statement);

        //THEN
        mockMvc.perform(post("/api/bankStatement/generate/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

    }

    @Test
    void test_generate_bank_statement_should_have_400() throws Exception {
        //Given
        BankStatement statement = new BankStatement();
        statement.setId(1L);

        //WHEN
        when(bankStatementService.generateBankStatement(1L)).thenReturn(statement);

        //THEN
        mockMvc.perform(post("/api/bankStatement/generate/toto"))
                .andExpect(status().isBadRequest());

    }

     */

}
