package com.DUONG.BankAccount.domain.service.oldService;

import org.springframework.stereotype.Service;

@Service
public abstract class BankAccountService {

    /*
    @Autowired
    OperationService operationService;

    @Autowired
    BankAccountRepository bankAccountRepository;


    // Effectuer un retrait sur le compte bancaire
    public BankAccount withdrawMoney(Long id, double amount) {

        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotfoundException("Compte Bancaire introuvable."));

        if (amount < 0) {
            throw new InvalidAmountException("Le montant à retirer doit être positif.");
        }
        if (amount > bankAccount.getBalance()) {
            throw new InsufficientFundsBalanceException("Vous ne pouvez pas retirer cet argent : vous n’avez pas assez d’argent sur votre compte.");
        }

        bankAccount.setBalance(bankAccount.getBalance() - amount);
        operationService.createOperationAndArchive(bankAccount, amount * -1);

        return bankAccount;
    }

    // Effectuer un dépôt sur le compte bancaire
    public BankAccount depositMoney(Long id, double amount) {

        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotfoundException("Compte Bancaire introuvable."));

        if (amount < 0) {
            throw new InvalidAmountException("Le montant à déposer doit être positif.");
        }

        bankAccount.setBalance(bankAccount.getBalance() + amount);
        operationService.createOperationAndArchive(bankAccount, amount);

        return bankAccount;
    }
    */

}
