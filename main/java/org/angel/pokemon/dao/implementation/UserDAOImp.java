package org.angel.pokemon.dao.implementation;

import org.angel.pokemon.connection.DatabaseConnection;
import org.angel.pokemon.dao.UserDAO;
import org.angel.pokemon.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {
    private final String TABLE_NAME = "user";

    public UserDAOImp() {
    }

    @Override
    public void create(User user) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (null, ?, ?)";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.executeUpdate();
    }

    @Override
    public User read(Integer id) throws SQLException {
        User user = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE user_id = ?";
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
        }
        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "UPDATE " + TABLE_NAME +
                " SET username = ?, password = ? WHERE user_id = ?";
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setInt(3, user.getUser_id());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE user_id = ?";
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<User> readAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            userList.add( new User(rs.getInt(1), rs.getString(2), rs.getString(3)) );
        }
        return userList;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        User user = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE username = ? AND password = ?";

        Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
        }

        return user;
    }
}
