package com.DUONG.BankAccount.domain.service.oldService;

import org.springframework.stereotype.Service;

@Service
public class CheckingAccountService extends BankAccountService {
    /*@Autowired
    private CheckingAccountRepository checkingAccountRepository;


    @Override
    public BankAccount withdrawMoney(Long id, double amount) {

        CheckingAccount checkingAccount = checkingAccountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotfoundException("Compte épargne introuvable."));


        if (amount < 0) {
            throw new InvalidAmountException("Le montant à retirer doit être positif.");
        }
        double finalBalance = checkingAccount.getBalance() - amount;

        if (!checkingAccount.isExceedAllowed() && finalBalance < 0) {
            throw new NoPermissionGivenException("Vous n’avez pas le droit d’être à découvert.");

        }
        if (checkingAccount.isExceedAllowed() && finalBalance < checkingAccount.getExceedBalance()) {
            throw new InsufficientFundsBalanceException("Vous n’avez pas assez d’argent sur votre compte : vous ne pouvez pas retirer cet argent.");

        }
        checkingAccount.setBalance(finalBalance);
        operationService.createOperationAndArchive(checkingAccount, amount * -1);

        return checkingAccount;
    }
     */
}
