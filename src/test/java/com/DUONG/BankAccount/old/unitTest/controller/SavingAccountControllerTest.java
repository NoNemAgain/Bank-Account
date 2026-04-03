package com.DUONG.BankAccount.old.unitTest.controller;

import com.DUONG.BankAccount.adapter.in.controller.SavingAccountController;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SavingAccountController.class)
public class SavingAccountControllerTest {
    /*

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SavingAccountService savingAccountService;


    @Test
    void deposit_should_have_200() throws Exception {

        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(1L);
        //WHEN
        when(savingAccountService.depositMoney(1L, 200)).thenReturn(savingAccount);

        //THEN
        mockMvc.perform(post("/api/savingAccount/deposit/1")
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deposit_should_have_400() throws Exception {

        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(1L);
        //WHEN
        when(savingAccountService.depositMoney(1L, -200)).thenReturn(savingAccount);

        //THEN
        mockMvc.perform(post("/api/savingAccount/deposit/toto")
                        .param("amount", "toto"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void withdraw_should_have_200() throws Exception {

        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(1L);
        //WHEN
        when(savingAccountService.withdrawMoney(1L, 200)).thenReturn(savingAccount);

        //THEN
        mockMvc.perform(post("/api/savingAccount/withdraw/1")
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void withdraw_should_have_400() throws Exception {

        //GIVEN
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(1L);
        //WHEN
        when(savingAccountService.withdrawMoney(1L, 200)).thenReturn(savingAccount);

        //THEN
        mockMvc.perform(post("/api/savingAccount/withdraw/toto")
                        .param("amount", "toto"))
                .andExpect(status().isBadRequest());
    }

    */
}
