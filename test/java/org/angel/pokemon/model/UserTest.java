package org.angel.pokemon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testConstructorUser() {
        User user = new User(1, "admin", "1234");

        assertNotNull(user);
        assertEquals(1, user.getUser_id());
        assertEquals("admin", user.getUsername());
        assertEquals("1234", user.getPassword());
    }

    @Test
    void testSettersUser() {
        User user = new User();

        user.setUser_id(1);
        user.setUsername("admin");
        user.setPassword("1234");

        assertNotNull(user);
        assertEquals(1, user.getUser_id());
        assertEquals("admin", user.getUsername());
        assertEquals("1234", user.getPassword());

    }


}