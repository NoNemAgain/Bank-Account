package com.DUONG.BankAccount.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private BigDecimal balance;

    @OneToMany(mappedBy = "bankAccount")
    @JsonManagedReference
    private List<Operation> operationsHistory = new ArrayList<>();

    @OneToMany(mappedBy = "bankAccount")
    @JsonManagedReference
    private List<BankStatement> bankStatements = new ArrayList<>();

    public String getTypeBank() {
        return this.getClass().getSimpleName();
    }

    public void addOperation(Operation operation) {
        operationsHistory.add(operation);
    }
}
