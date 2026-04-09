package com.duong.bank.domain.useCase.getBankAccount;

import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.domain.exception.ObjectNotfoundException;
import com.duong.bank.domain.exception.ObjectType;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.port.in.GetBankAccountPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class GetBankAccountUseCase implements GetBankAccountPort {
    BankAccountRepository bankAccountRepository;

    public GetBankAccountUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccount getBankAccountById(UUID id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotfoundException(ObjectType.BANK));
    }

    @Override
    public List<BankAccount> getBanksAccountsByOwner(String owner) {
        return bankAccountRepository.findByOwnerName(owner);
    }
}
