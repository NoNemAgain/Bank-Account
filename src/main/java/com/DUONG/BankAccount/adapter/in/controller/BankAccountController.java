package com.DUONG.BankAccount.adapter.in.controller;


import com.DUONG.BankAccount.adapter.in.dto.BankAccountDTO;
import com.DUONG.BankAccount.adapter.mapper.BankAccountMapper;
import com.DUONG.BankAccount.port.in.DepositPort;
import com.DUONG.BankAccount.port.in.WithdrawPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final DepositPort depositPort;
    private final WithdrawPort withdrawPort;

    public BankAccountController(DepositPort depositPort, WithdrawPort withdrawPort) {
        this.depositPort = depositPort;
        this.withdrawPort = withdrawPort;
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<BankAccountDTO> deposit(@PathVariable UUID id, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(BankAccountMapper.toDTO(depositPort.deposit(id, amount)));
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<BankAccountDTO> withdraw(@PathVariable UUID id, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(BankAccountMapper.toDTO(withdrawPort.withdraw(id, amount)));
    }
}
