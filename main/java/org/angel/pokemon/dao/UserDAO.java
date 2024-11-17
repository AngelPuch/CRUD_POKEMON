package org.angel.pokemon.dao;

import org.angel.pokemon.model.User;

public interface UserDAO extends DAO<User, Integer>{
    User findUserByUsernameAndPassword(String username, String password) throws Exception;
}
