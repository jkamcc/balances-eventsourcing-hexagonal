package org.devengineering.balances.adapters.api.http;

import io.javalin.Javalin;
import org.devengineering.balances.adapters.api.http.accounts.AccountsModule;

public class HttpApiApplicationAdapter {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);
        app.get("/", ctx -> ctx.result("Hello, Javalin!"));

        new AccountsModule().registerRoutes(app);

    }
}
