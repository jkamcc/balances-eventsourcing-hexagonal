package org.devengineering.balances.adapters.api.http.accounts;

import io.javalin.Javalin;
import org.devengineering.balances.application.accounts.ports.AccountsService;

public final class AccountsModule {

  public void registerRoutes(Javalin app, AccountsService accountsService) {
    var controller = new AccountsController(accountsService);
    app.post("/api/v1/accounts/deposit", controller::deposit);
  }
}
