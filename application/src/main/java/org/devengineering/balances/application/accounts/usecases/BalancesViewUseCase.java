package org.devengineering.balances.application.accounts.usecases;

import org.devengineering.balances.entities.BalanceMovedEvent;

import java.util.List;

public interface BalancesViewUseCase {

  List<BalanceMovedEvent> getBalancesByAccountId(String accountId);
}
