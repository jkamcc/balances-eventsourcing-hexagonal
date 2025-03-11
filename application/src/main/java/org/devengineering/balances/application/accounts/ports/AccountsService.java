package org.devengineering.balances.application.accounts.ports;

import org.devengineering.balances.application.accounts.usecases.BalancesDepositUseCase;
import org.devengineering.balances.entities.BalanceMovedEvent;
import org.devengineering.balances.repositories.EventStore;

import java.io.IOException;

public class AccountsService implements BalancesDepositUseCase {

  private final EventStore eventStore;

  public AccountsService(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  @Override
  public void deposit(String accountId, BalanceMovedEvent balanceMovedEvent) throws IOException {
    eventStore.append(accountId, balanceMovedEvent);
  }
}
