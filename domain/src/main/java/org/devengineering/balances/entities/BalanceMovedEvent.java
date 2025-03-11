package org.devengineering.balances.entities;

import java.util.UUID;

public record BalanceMovedEvent(
        UUID eventId,
        String accountId,
        String amount
) implements Event{

    @Override
    public UUID getEventId() {
        return this.eventId;
    }
}
