package org.devengineering.balances.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.devengineering.balances.BalanceMovedEvent;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountsControllerTest {

    @Test
    void depositIsSuccessful() {
        Javalin app = Javalin.create();
        var eventId = UUID.randomUUID();
        var balanceMovedEvent = new BalanceMovedEvent(eventId, "a", "100");
        ObjectMapper objectMapper = new ObjectMapper();

        JavalinTest.test(app, (server, client) -> {
            new AccountsController(app);
            var response = client.post("/api/v1/accounts/deposit", balanceMovedEvent);
            assertEquals(201, response.code());
            JSONAssert.assertEquals(objectMapper.writeValueAsString(balanceMovedEvent),
            response.body().string(), true);
        });
    }

}
