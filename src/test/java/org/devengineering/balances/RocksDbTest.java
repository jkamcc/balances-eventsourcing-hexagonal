package org.devengineering.balances;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import static org.junit.jupiter.api.Assertions.*;

public class RocksDbTest {

    private static final String DB_PATH = "test_rocksdb_data";
    private RocksDB db;

    @BeforeEach
    public void setUp() {
        // Create options to open the database with the option to create it if it doesn't exist
        Options options = new Options().setCreateIfMissing(true);
        try {
            db = RocksDB.open(options, DB_PATH);
        } catch (RocksDBException e) {
            fail("Failed to open RocksDB: " + e.getMessage());
        }
    }

    @Test
    public void testPutAndGet() {
        String key = "name";
        String value = "Alice";

        try {
            // Put data into the database
            db.put(key.getBytes(), value.getBytes());

            // Get data from the database
            byte[] retrievedValue = db.get(key.getBytes());

            // Assert that the value retrieved matches the one we stored
            assertNotNull(retrievedValue);
            assertEquals(value, new String(retrievedValue));

        } catch (RocksDBException e) {
            fail("Failed to perform put/get operation: " + e.getMessage());
        }
    }
}
