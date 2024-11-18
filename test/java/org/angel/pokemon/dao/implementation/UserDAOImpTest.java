package org.angel.pokemon.dao.implementation;

import org.angel.pokemon.dao.UserDAO;
import org.angel.pokemon.model.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOImpTest {
    @Test
    void testCreateUser() throws SQLException {
        UserDAO userDAO = new UserDAOImp();
        User user = new User(2, "normal", "12345");

        userDAO.create(user);
        User retrievedUser = userDAO.read(3);

        assertNotNull(retrievedUser);
        assertEquals("normal", retrievedUser.getUsername());
        assertEquals("12345", retrievedUser.getPassword());
    }

    @Test
    void testReadUser() throws SQLException {
        UserDAO userDAO = new UserDAOImp();
        User user = userDAO.read(1);

        assertNotNull(user);
        assertEquals("admin", user.getUsername());
        assertEquals("1234", user.getPassword());
    }

    @Test
    void testUpdateUser() throws SQLException {
        UserDAO userDAO = new UserDAOImp();
        User user = userDAO.read(2);

        user.setUsername("normalUpdate");
        userDAO.update(user);

        User updatedUser = userDAO.read(2);
        assertEquals("normalUpdate", updatedUser.getUsername());
    }

    @Test
    void testDeleteUser() throws SQLException {
        UserDAO userDAO = new UserDAOImp();
        userDAO.delete(2);

        User deletedUser = userDAO.read(2);
        assertNull(deletedUser);

    }

}