package org.angel.pokemon.model;

import java.io.Serializable;

public class User implements Serializable {
    private int user_id;
    private String username;
    private String password;

    public User() {
    }

    public User(int user_id, String username, String password) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }

    public int getUser_id() { return user_id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
