package org.devengineering.balances;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EventStore {

    protected Path filePath;

    protected ObjectMapper objectMapper;

    public EventStore(String filePath) {
        this.filePath = Path.of(filePath);
        this.objectMapper = new ObjectMapper();
    }

    public void append(String accountId, BalanceMovedEvent movement) throws IOException {
        final Path accountPath = Path.of(this.filePath.toString(), accountId);
        if (!Files.exists(accountPath)) {
            Files.createDirectories(accountPath);
        }

        Files.write(
                Path.of(accountPath.toString(), movement.getEventId().toString()),
                objectMapper.writeValueAsBytes(movement));
    }
}
