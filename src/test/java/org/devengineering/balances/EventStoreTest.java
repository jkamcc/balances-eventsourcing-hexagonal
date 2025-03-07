package org.devengineering.balances;

import org.junit.jupiter.api.Test;

class EventStoreTest {

    @Test
    void checkAddElementExists() {
        var eventStore = new EventStore();
        var accountId = "a";
        var balanceMovement = new BalanceMovement(accountId, 10);
        eventStore.append(accountId, balanceMovement);
    }

}
