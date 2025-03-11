package org.devengineering.balances.application.accounts.usecases;

import org.devengineering.balances.entities.BalanceMovedEvent;

import java.io.IOException;

public interface BalancesDepositUseCase {

    void deposit(String accountId, BalanceMovedEvent balanceMovedEvent) throws IOException;
}
