package com.DUONG.BankAccount.adapter.out.repository;

import com.DUONG.BankAccount.domain.model.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount, UUID> {
}
