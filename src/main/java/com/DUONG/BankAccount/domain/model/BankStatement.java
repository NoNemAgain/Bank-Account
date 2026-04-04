package com.DUONG.BankAccount.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankStatement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private AccountType accountType;

    private BigDecimal balance;

    private LocalDateTime date;

    @OneToMany(mappedBy = "bankStatement")
    @JsonManagedReference
    private List<Operation> operations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    @JsonBackReference
    private BankAccount bankAccount;

}
