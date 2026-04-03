package com.DUONG.BankAccount.adapter.out.repository;

import com.DUONG.BankAccount.domain.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
}
