package com.DUONG.BankAccount.adapter.in.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bankStatements")
public class BankStatementController {
    /*
    private final BankStatementService bankStatementService;

    public BankStatementController (BankStatementService bankStatementService){
        this.bankStatementService=bankStatementService;
    }

    @PostMapping("/{idBankAcc}")
    public ResponseEntity<BankStatement> generateBankStatement(@PathVariable Long idBankAcc){
        return ResponseEntity.ok(bankStatementService.generateBankStatement(idBankAcc));
    }
    */
}
