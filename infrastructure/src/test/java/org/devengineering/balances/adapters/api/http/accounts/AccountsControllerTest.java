package org.devengineering.balances.adapters.api.http.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.devengineering.balances.application.accounts.ports.AccountsService;
import org.devengineering.balances.entities.BalanceMovedEvent;
import org.devengineering.balances.repositories.EventStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.Mockito.mock;

class AccountsControllerTest {

  private Javalin app;
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    app = Javalin.create();
    objectMapper = new ObjectMapper();
  }

  @Test
  void depositIsSuccessful() {
    var eventId = UUID.randomUUID();
    var balanceMovedEvent = new BalanceMovedEvent(eventId, "a", "100");

    JavalinTest.test(
        app,
        (server, client) -> {
          new AccountsModule().registerRoutes(app, new AccountsService(mock(EventStore.class)));
          var response = client.post("/api/v1/accounts/deposit", balanceMovedEvent);
          Assertions.assertEquals(201, response.code());
          JSONAssert.assertEquals(
              objectMapper.writeValueAsString(balanceMovedEvent), response.body().string(), true);
        });
  }

  @Test
  void depositFailsConnectionError() throws Exception {
    var eventId = UUID.randomUUID();
    var balanceMovedEvent = new BalanceMovedEvent(eventId, "a", "100");
    var accountsService = mock(AccountsService.class);
    Mockito.doThrow(new IOException("Connection error"))
        .when(accountsService)
        .deposit(balanceMovedEvent.accountId(), balanceMovedEvent);

    JavalinTest.test(
        app,
        (server, client) -> {
          new AccountsModule().registerRoutes(app, accountsService);
          var response = client.post("/api/v1/accounts/deposit", balanceMovedEvent);
          Assertions.assertEquals(500, response.code());
        });
  }
}
