package com.DUONG.BankAccount.adapter.in.controller;


import com.DUONG.BankAccount.adapter.mapper.BankAccountMapper;
import com.DUONG.BankAccount.port.in.DepositPort;
import com.DUONG.BankAccount.port.in.WithdrawPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {

    private final DepositPort depositPort;
    private final WithdrawPort withdrawPort;

    public BankAccountController(DepositPort depositPort, WithdrawPort withdrawPort) {
        this.depositPort = depositPort;
        this.withdrawPort = withdrawPort;
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<?> depositMoney(@PathVariable UUID id, @RequestParam double amount) {
        return ResponseEntity.ok(BankAccountMapper.toDTO(depositPort.deposit(id, new BigDecimal(amount))));
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<?> withdrawMoeny(@PathVariable UUID id, @RequestParam double amount) {
        return ResponseEntity.ok(BankAccountMapper.toDTO(withdrawPort.withdraw(id, new BigDecimal(amount))));
    }
}
