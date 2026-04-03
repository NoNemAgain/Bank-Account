package com.DUONG.BankAccount.domain.service.oldService;


import org.springframework.stereotype.Service;

@Service
public class OperationService {
    /*@Autowired
    private OperationRepository operationRepository;

    public Operation createOperationAndArchive(BankAccount bankAccount, double amount) {
        Operation operation = new Operation();
        if (amount >= 0) operation.setType("Dépot");
        if (amount < 0) operation.setType("Retrait");
        operation.setDate(LocalDateTime.now());
        operation.setMontant(amount);
        operation.setBankAccount(bankAccount);
        bankAccount.addOperation(operation);
        operationRepository.save(operation);
        return operation;
    }*/
}
