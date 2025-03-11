package org.devengineering.balances.adapters.api.http.accounts;

import io.javalin.http.Context;
import org.devengineering.balances.entities.BalanceMovedEvent;

public class AccountsController {

    public void deposit(Context ctx) {
        var balanceMovement= ctx.bodyAsClass(BalanceMovedEvent.class);
        ctx.json(balanceMovement);
        ctx.status(201);
    }

}
