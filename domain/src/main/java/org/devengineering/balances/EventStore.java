package org.devengineering.balances;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EventStore {

    protected Path filePath;

    public EventStore(String filePath) {
        this.filePath = Path.of(filePath);
    }

    public void append(String accountId, BalanceMovedEvent movement) throws IOException {
        final Path accountPath = Path.of(this.filePath.toString(), accountId);
        if (!Files.exists(accountPath)) {
            Files.createDirectories(accountPath);
        }

        Files.write(Path.of(accountPath.toString(), movement.getEventId().toString()), movement.toString().getBytes());
    }
}
