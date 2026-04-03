package com.DUONG.BankAccount.adapter.in.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/savingAccounts")
public class SavingAccountController {
    /*
    private final SavingAccountService savingAccountService;

    public SavingAccountController(SavingAccountService savingAccountService) {
        this.savingAccountService = savingAccountService;
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<BankAccount> depositMoney(@PathVariable Long id, @RequestParam double amount) {
        return ResponseEntity.ok(savingAccountService.depositMoney(id, amount));
    }

    @PostMapping("/withdraw/{id}")
    public ResponseEntity<BankAccount> withdrawMoney(@PathVariable Long id, @RequestParam double amount) {
        return ResponseEntity.ok(savingAccountService.withdrawMoney(id, amount));
    }

    */
}
