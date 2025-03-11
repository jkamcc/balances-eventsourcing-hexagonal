package org.devengineering.balances.application;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.devengineering.balances.BalanceMovedEvent;

public class AccountsController {

    public AccountsController(final Javalin app) {
        app.post("/api/v1/accounts/deposit", this::deposit);
    }

    public void deposit(Context ctx) {
        var balanceMovement= ctx.bodyAsClass(BalanceMovedEvent.class);
        ctx.json(balanceMovement);
        ctx.status(201);
    }

}
