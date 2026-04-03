package com.DUONG.BankAccount.old.unitTest.controller;

import com.DUONG.BankAccount.adapter.in.controller.CheckingAccountController;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(CheckingAccountController.class)
public class CheckingAccountControllerTest {
    /*
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CheckingAccountService checkingAccountService;


    @Test
    void withdraw_should_have_200() throws Exception {
        //Given
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);


        //WHEN
        when(checkingAccountService.withdrawMoney(1L, 200)).thenReturn(checkingAccount);

        //THEN
        mockMvc.perform(post("/api/checkingAccount/withdraw/1")
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    void withdraw_should_have_400() throws Exception {
        //Given
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);


        //WHEN
        when(checkingAccountService.withdrawMoney(1L, 200)).thenReturn(checkingAccount);

        //THEN
        mockMvc.perform(post("/api/checkingAccount/withdraw/toto")
                        .param("amount", "toto"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deposit_should_have_200() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        //WHEN
        when(checkingAccountService.depositMoney(1L, 200)).thenReturn(checkingAccount);


        //THEN

        mockMvc.perform(post("/api/checkingAccount/deposit/1")
                        .param("amount", "200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void deposit_should_have_400() throws Exception {
        //GIVEN
        CheckingAccount checkingAccount = new CheckingAccount();
        checkingAccount.setId(1L);
        //WHEN
        when(checkingAccountService.depositMoney(1L, 200)).thenReturn(checkingAccount);


        //THEN

        mockMvc.perform(post("/api/checkingAccount/deposit/toto")
                        .param("amount", "toto"))
                .andExpect(status().isBadRequest());

    }

    */
}
