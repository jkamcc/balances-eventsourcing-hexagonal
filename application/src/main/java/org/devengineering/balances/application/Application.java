package org.devengineering.balances.application;

import io.javalin.Javalin;
import org.devengineering.balances.application.accounts.AccountsModule;

public class Application {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);
        app.get("/", ctx -> ctx.result("Hello, Javalin!"));

        new AccountsModule().registerRoutes(app);

    }
}
