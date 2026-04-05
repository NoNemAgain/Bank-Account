package com.duong.bank.domain.service.deposit.strategy;

import com.duong.bank.domain.model.AccountType;
import org.springframework.stereotype.Component;

@Component
public class CheckingDepositStrategy extends AbstractDepositStrategy implements DepositStrategy {

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
