package org.angel.pokemon.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T, K>{
    void create (T t) throws SQLException;
    T read (K id) throws SQLException;
    void update (T t) throws SQLException;
    void delete (K id) throws SQLException;
    List<T> readAll () throws SQLException;
}
