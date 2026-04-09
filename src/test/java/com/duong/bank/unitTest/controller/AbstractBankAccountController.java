package com.duong.bank.unitTest.controller;

import com.duong.bank.adapter.in.controller.BankAccountController;
import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.port.in.CreateBankAccountPort;
import com.duong.bank.port.in.DepositPort;
import com.duong.bank.port.in.GetBankAccountPort;
import com.duong.bank.port.in.WithdrawPort;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AbstractBankAccountController {
    protected MockMvc mockMvc;
    protected BankAccountRepository bankAccountRepository;
    protected DepositPort depositPort;
    protected WithdrawPort withdrawPort;
    protected CreateBankAccountPort createBankAccountPort;
    protected GetBankAccountPort getBankAccountPort;
    protected BankAccountController bankAccountController;

    @BeforeEach
    void setUp() {
        depositPort = Mockito.mock(DepositPort.class);
        withdrawPort = Mockito.mock(WithdrawPort.class);
        createBankAccountPort = Mockito.mock(CreateBankAccountPort.class);
        getBankAccountPort = Mockito.mock(GetBankAccountPort.class);

        bankAccountController = new BankAccountController(depositPort, withdrawPort
                , getBankAccountPort, createBankAccountPort);

        mockMvc = MockMvcBuilders.standaloneSetup(bankAccountController).build();
    }
}
