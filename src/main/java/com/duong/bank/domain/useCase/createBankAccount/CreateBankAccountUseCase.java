package com.duong.bank.domain.useCase.createBankAccount;

import com.duong.bank.adapter.out.repository.BankAccountRepository;
import com.duong.bank.domain.model.BankAccount;
import com.duong.bank.port.in.CreateBankAccountPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CreateBankAccountUseCase implements CreateBankAccountPort {
    BankAccountRepository bankAccountRepository;

    public CreateBankAccountUseCase(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}
