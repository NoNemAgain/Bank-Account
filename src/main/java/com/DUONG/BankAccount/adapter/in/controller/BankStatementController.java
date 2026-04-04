package com.DUONG.BankAccount.adapter.in.controller;


import com.DUONG.BankAccount.adapter.in.dto.BankStatementDTO;
import com.DUONG.BankAccount.adapter.mapper.BankStatementMapper;
import com.DUONG.BankAccount.port.in.BankStatementPort;
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

    private final BankStatementPort bankStatementPort;

    public BankStatementController(BankStatementPort bankStatementPort) {
        this.bankStatementPort = bankStatementPort;
    }

    @PostMapping("/{idBankAcc}")
    public ResponseEntity<BankStatementDTO> generateBankStatement(@PathVariable UUID idBankAcc) {

        BankStatementDTO bankStatementDTO = BankStatementMapper.toDTO(bankStatementPort.createBankStatement(idBankAcc));

        return ResponseEntity.created(URI.create("/bank-statements/" + bankStatementDTO.getId()))
                .body(bankStatementDTO);
    }

}
