package com.DUONG.BankAccount.domain.service.oldService;

import org.springframework.stereotype.Service;


@Service
public class SavingAccountService extends BankAccountService {
    /*
    @Autowired
    private SavingAccountRepository savingAccountRepository;


    @Override
    public BankAccount depositMoney(Long id, double amount) {

        SavingAccount savingAccount = savingAccountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotfoundException("Livre introuvable."));
        if (amount < 0) {
            throw new InvalidAmountException("Le montant à déposer doit être positif.");
        }
        double finalBalance = savingAccount.getBalance() + amount;


        if (finalBalance > (savingAccount).getBalanceLimit()) {
            throw new ExceedLimitBalanceException
                    ("Le plafond de votre compte sera dépassé, vous ne pouvez pas déposer cet argent.");
        }
        savingAccount.setBalance(finalBalance);
        operationService.createOperationAndArchive(savingAccount, amount);

        return savingAccount;
    }
    */
}
