package com.duong.bank.adapter.in.controller;


import com.duong.bank.adapter.in.dto.response.BankStatementResponse;
import com.duong.bank.adapter.mapper.BankStatementMapper;
import com.duong.bank.port.in.CreateBankStatementPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/bankStatements")
public class BankStatementController {

    private final CreateBankStatementPort createBankStatementPort;

    public BankStatementController(CreateBankStatementPort createBankStatementPort) {
        this.createBankStatementPort = createBankStatementPort;
    }

    @PostMapping("/{idBankAcc}")
    public ResponseEntity<BankStatementResponse> generateBankStatement(@PathVariable UUID idBankAcc) {

        BankStatementResponse bankStatementResponse = BankStatementMapper.toDTO(createBankStatementPort.createBankStatement(idBankAcc));

        return ResponseEntity.created(URI.create("/bank-statements/" + bankStatementResponse.getId()))
                .body(bankStatementResponse);
    }

}
