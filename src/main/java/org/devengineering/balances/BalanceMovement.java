package org.devengineering.balances;

public record BalanceMovement(
        String accountId,
        double amount
) {
}
