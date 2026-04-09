package com.duong.bank.adapter.in.dto.response;

import com.duong.bank.domain.model.AccountType;
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
public class BankStatementResponse {

    private UUID id;

    private AccountType accountType;

    private BigDecimal balance;

    private LocalDateTime date;

    private List<OperationResponse> operations = new ArrayList<>();

    private UUID bankAccountId;
}
