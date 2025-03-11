package org.devengineering.balances.repositories;

import org.devengineering.balances.entities.BalanceMovedEvent;

import java.io.IOException;

public interface EventStore {

    void append(String accountId, BalanceMovedEvent movement) throws IOException;

}
