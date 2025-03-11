package org.devengineering.balances.adapters.api.http.accounts;

import io.javalin.http.Context;
import org.devengineering.balances.application.accounts.ports.AccountsService;
import org.devengineering.balances.entities.BalanceMovedEvent;

import java.io.IOException;

public class AccountsController {

  private final AccountsService accountsService;

  public AccountsController(AccountsService accountsService) {
    this.accountsService = accountsService;
  }

  public void deposit(Context ctx) {
    var balanceMovement = ctx.bodyAsClass(BalanceMovedEvent.class);

    try {
      accountsService.deposit(balanceMovement.accountId(), balanceMovement);
    } catch (IOException e) {
      ctx.status(500);
      return;
    }

    ctx.json(balanceMovement);
    ctx.status(201);
  }
}
