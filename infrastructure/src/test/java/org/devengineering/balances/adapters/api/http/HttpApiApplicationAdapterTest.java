package org.devengineering.balances.adapters.api.http;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpApiApplicationAdapterTest {

    @Test
    void testHelloEndpoint() {
        Javalin app = Javalin.create().get("/", ctx -> ctx.result("Hello, Javalin!"));
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            Assertions.assertEquals(200, response.code());
            Assertions.assertEquals("Hello, Javalin!", response.body().string());
        });
    }
}