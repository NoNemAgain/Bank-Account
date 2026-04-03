package com.DUONG.BankAccount.domain.service.oldService;

import org.springframework.stereotype.Service;

@Service
public class BankStatementService {
    /*
    @Autowired
    BankStatementRepository bankStatementRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankStatement generateBankStatement(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotfoundException("Compte Bancaire introuvable."));

        BankStatement bankStatement = new BankStatement();
        bankStatement.setAccountType(bankAccount.getTypeBank());
        bankStatement.setBankAccount(bankAccount);
        bankStatement.setBalance(bankAccount.getBalance());
        bankStatement.setDate(LocalDateTime.now());

        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        bankStatement.setOperations(
                bankAccount.getOperationsHistory().stream()
                        .filter(operation -> !operation.getDate().isBefore(oneMonthAgo))
                        .sorted(Comparator.comparing(Operation::getDate).reversed()).toList());
        bankStatementRepository.save(bankStatement);
        return bankStatement;

    }
    */

}
