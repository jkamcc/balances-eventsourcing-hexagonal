package org.devengineering.balances;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

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
        var eventStore = new EventStore(filePath);
        var accountId = "a";
        var eventId = UUID.fromString("353c9ad7-a9f6-45f8-b605-04be4a5862ed");
        var balanceMovement = new BalanceMovedEvent(eventId, accountId, String.valueOf(10));
        eventStore.append(accountId, balanceMovement);

        assertTrue(Files.exists(Path.of(eventStore.filePath.toString(), accountId)));
        var eventFilePath = Path.of(eventStore.filePath.toString(), accountId, eventId.toString());
        assertTrue(Files.exists(eventFilePath));

        byte[] jsonData = Files.readAllBytes(eventFilePath);

        String actualJson = new String(jsonData);
        String expectedJson =
               """
               {"eventId":"353c9ad7-a9f6-45f8-b605-04be4a5862ed","accountId":"a","amount":"10"}
               """;
        JSONAssert.assertEquals(expectedJson, actualJson, true);
    }

}
