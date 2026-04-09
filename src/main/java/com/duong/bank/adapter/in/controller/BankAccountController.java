package com.duong.bank.adapter.in.controller;


import com.duong.bank.adapter.in.dto.request.BankAccountRequest;
import com.duong.bank.adapter.in.dto.request.CheckingAccountRequest;
import com.duong.bank.adapter.in.dto.request.SavingAccountRequest;
import com.duong.bank.adapter.in.dto.response.BankAccountResponse;
import com.duong.bank.adapter.mapper.BankAccountMapper;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.port.in.CreateBankAccountPort;
import com.duong.bank.port.in.DepositPort;
import com.duong.bank.port.in.GetBankAccountPort;
import com.duong.bank.port.in.WithdrawPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final DepositPort depositPort;
    private final WithdrawPort withdrawPort;
    private final GetBankAccountPort getBankAccountPort;
    private final CreateBankAccountPort createBankAccountPort;

    public BankAccountController(DepositPort depositPort, WithdrawPort withdrawPort
            , GetBankAccountPort getBankAccountPort, CreateBankAccountPort createBankAccountPort) {
        this.depositPort = depositPort;
        this.withdrawPort = withdrawPort;
        this.getBankAccountPort = getBankAccountPort;
        this.createBankAccountPort = createBankAccountPort;

    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<BankAccountResponse> deposit(@PathVariable UUID id, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(BankAccountMapper.toResponse(depositPort.deposit(id, amount)));
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<BankAccountResponse> withdraw(@PathVariable UUID id, @RequestBody BigDecimal amount) {
        return ResponseEntity.ok(BankAccountMapper.toResponse(withdrawPort.withdraw(id, amount)));
    }

    @GetMapping("")
    public ResponseEntity<List<BankAccountResponse>> getBankAccById() {
        return ResponseEntity.ok(getBankAccountPort.getBankAccounts().stream()
                .map(bankAccount -> BankAccountMapper.toResponse(bankAccount)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> getBankAccById(@PathVariable UUID id) {
        return ResponseEntity.ok(BankAccountMapper.toResponse(getBankAccountPort.getBankAccountById(id)));
    }

    /*@PostMapping()
    public ResponseEntity<BankAccountResponse> createBankAcc(@RequestBody BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = createBankAccountPort.createBankAccount(BankAccountMapper.requestToEntity(bankAccountRequest));

        return ResponseEntity.created(URI.create("/bank-statements/" + bankAccount.getId()))
                .body(BankAccountMapper.toResponse(bankAccount));
    }*/
    @PostMapping("/checking")
    public ResponseEntity<BankAccountResponse> createCheckAcc(@RequestBody CheckingAccountRequest checkingAccountRequest) {
        BankAccount bankAccount = createBankAccountPort.createBankAccount(BankAccountMapper.requestToEntity(checkingAccountRequest));

        return ResponseEntity.created(URI.create("/bank-statements/" + bankAccount.getId()))
                .body(BankAccountMapper.toResponse(bankAccount));
    }

    @PostMapping("/saving")
    public ResponseEntity<BankAccountResponse> createSavingAcc(@RequestBody SavingAccountRequest savingAccountRequest) {
        BankAccount bankAccount = createBankAccountPort.createBankAccount(BankAccountMapper.requestToEntity(savingAccountRequest));

        return ResponseEntity.created(URI.create("/bank-statements/" + bankAccount.getId()))
                .body(BankAccountMapper.toResponse(bankAccount));
    }


}
