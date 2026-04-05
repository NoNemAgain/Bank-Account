package com.duong.bank.adapter.out.repository;

import com.duong.bank.domain.model.BankStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BankStatementRepository extends JpaRepository<BankStatement, UUID> {
}
