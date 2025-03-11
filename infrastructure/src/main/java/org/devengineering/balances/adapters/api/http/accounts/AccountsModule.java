package org.devengineering.balances.adapters.api.http.accounts;

import io.javalin.Javalin;

public final class AccountsModule {

    public void registerRoutes(Javalin app) {
        var controller = new AccountsController();
        app.post("/api/v1/accounts/deposit", controller::deposit);
    }
}
