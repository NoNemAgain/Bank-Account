package com.duong.bank.domain.service.bankStatement.service;

import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.adapter.out.repository.BankStatementRepository;
import com.duong.bank.domain.exception.ObjectNotfoundException;
import com.duong.bank.domain.exception.ObjectType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.domain.model.BankStatement;
import com.duong.bank.domain.model.Operation;
import com.duong.bank.port.in.BankStatementPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.UUID;

@Slf4j
@Service
public class BankStatementService implements BankStatementPort {

    private final BankStatementRepository bankStatementRepository;

    private final BankAccountRepository bankAccountRepository;

    public BankStatementService(BankStatementRepository bankStatementRepository, BankAccountRepository bankAccountRepository) {
        this.bankStatementRepository = bankStatementRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankStatement createBankStatement(UUID id) {
        log.debug("Requested : Generated bankStatment, idAccount={}", id);

        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotfoundException(ObjectType.BANK));

        BankStatement bankStatement = createObjectBankStatement(bankAccount);
        log.debug("Successfully created object bankStatement");

        filterAndSortRecentOperations(bankAccount, bankStatement);
        log.debug("Successfully removed operations older than one month and sorted the list in descending order");

        bankStatementRepository.save(bankStatement);

        return bankStatement;
    }

    private BankStatement createObjectBankStatement(BankAccount bankAccount) {
        log.debug("Initializing bank statement for account {}", bankAccount.getId());
        BankStatement bankStatement = new BankStatement();
        bankStatement.setAccountType(bankAccount.getTypeBank());
        bankStatement.setBankAccount(bankAccount);
        bankStatement.setBalance(bankAccount.getBalance());
        bankStatement.setDate(LocalDateTime.now());
        bankStatement.setOperations(bankAccount.getOperationsHistory());

        return bankStatement;
    }

    private void filterAndSortRecentOperations(BankAccount bankAccount, BankStatement bankStatement) {
        log.debug("Requested Removing operations older than one month and sorted the list in descending order");
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);

        bankStatement.setOperations(
                bankAccount.getOperationsHistory().stream()
                        .filter(operation -> !operation.getDate().isBefore(oneMonthAgo))
                        .sorted(Comparator.comparing(Operation::getDate).reversed()).toList());
        bankStatement.getOperations().stream().forEach(operation -> operation.setBankStatement(bankStatement));

    }
}
