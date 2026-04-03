package com.DUONG.BankAccount.adapter.out.repository;

import com.DUONG.BankAccount.domain.model.CheckingAccount;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, UUID> {
}
