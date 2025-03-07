package org.devengineering.balances;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EventStoreTest {

    final String filePath = "/tmp/event-store/";

    @BeforeEach
    void removeDirectories() throws Exception {
        DirectoryDeleter.deleteDirectoryRecursively(Path.of(filePath));
    }

    @Test
    void checkAddElementExists() throws Exception {
        final String filePath = "/tmp/event-store/";
        var eventStore = new EventStore(filePath);
        var accountId = "a";
        var balanceMovement = new BalanceMovedEvent(UUID.randomUUID(), accountId, 10);
        eventStore.append(accountId, balanceMovement);

        assertTrue(Files.exists(Path.of(eventStore.filePath + "/" + accountId)));
    }

}
