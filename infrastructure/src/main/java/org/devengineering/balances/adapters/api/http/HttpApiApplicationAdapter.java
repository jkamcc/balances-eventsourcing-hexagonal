package org.devengineering.balances.adapters.api.http;

import io.javalin.Javalin;
import org.devengineering.balances.adapters.api.http.accounts.AccountsModule;
import org.devengineering.balances.adapters.files.FilesEventStore;
import org.devengineering.balances.application.accounts.ports.AccountsService;

public class HttpApiApplicationAdapter {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7001);
        app.get("/", ctx -> ctx.result("Hello, Javalin!"));

        new AccountsModule().registerRoutes(
                app,
                new AccountsService(
                        new FilesEventStore("/tmp/event-store/api")));
    }
}
