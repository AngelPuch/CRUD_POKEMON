package org.angel.pokemon.connection;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void testDatabaseConnection() throws SQLException {

        Connection conn = DatabaseConnection.getInstance().getConnection();
        assertNotNull(conn);
        assertFalse(conn.isClosed());

    }
}