package com.DUONG.BankAccount.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String type;

    private LocalDateTime date;

    private BigDecimal montant;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    @JsonBackReference
    private BankAccount bankAccount;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "bank_statement_id")
    private BankStatement bankStatement;
}
