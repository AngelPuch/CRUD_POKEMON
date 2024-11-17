package org.angel.pokemon.dao.implementation;

import org.angel.pokemon.connection.DatabaseConnection;
import org.angel.pokemon.dao.PokemonTypeDAO;
import org.angel.pokemon.model.PokemonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonTypeDAOImp implements PokemonTypeDAO {
    private final String TABLE_NAME = "pokemontype";

    public PokemonTypeDAOImp() {
    }

    @Override
    public void create(PokemonType pokemonType) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (null, ?)";
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pokemonType.getTypeName());
        ps.executeUpdate();
    }

    @Override
    public PokemonType read(Integer id) throws SQLException {
        PokemonType pokemonType = null;

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            pokemonType = new PokemonType(rs.getInt(1), rs.getString(2));
        }
        return pokemonType;
    }

    @Override
    public void update(PokemonType pokemonType) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET name = ? WHERE id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pokemonType.getTypeName());
        ps.setInt(2, pokemonType.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<PokemonType> readAll() throws SQLException {
        List<PokemonType> pokemonTypeList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            pokemonTypeList.add( new PokemonType(rs.getInt(1), rs.getString(2)) );
        }
        return pokemonTypeList;
    }
}
