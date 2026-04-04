package com.DUONG.BankAccount.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public abstract class BankAccount {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    private BigDecimal balance;

    @OneToMany(mappedBy = "bankAccount")
    @JsonManagedReference
    private List<Operation> operationsHistory = new ArrayList<>();

    @OneToMany(mappedBy = "bankAccount")
    @JsonManagedReference
    private List<BankStatement> bankStatements = new ArrayList<>();


    public abstract AccountType getTypeBank();

    public void addOperation(Operation operation) {
        operationsHistory.add(operation);
    }
}
