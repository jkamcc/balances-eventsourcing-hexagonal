package org.devengineering.balances.application.accounts;

import io.javalin.http.Context;
import org.devengineering.balances.BalanceMovedEvent;

public class AccountsController {

    public void deposit(Context ctx) {
        var balanceMovement= ctx.bodyAsClass(BalanceMovedEvent.class);
        ctx.json(balanceMovement);
        ctx.status(201);
    }

}
