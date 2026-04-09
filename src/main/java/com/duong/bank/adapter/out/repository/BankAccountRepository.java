package com.duong.bank.adapter.out.repository;

import com.duong.bank.domain.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
    List<BankAccount> findByOwnerName(String ownerName);
}
