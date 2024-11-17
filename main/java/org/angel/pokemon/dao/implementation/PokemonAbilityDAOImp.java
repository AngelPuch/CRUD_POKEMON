package org.angel.pokemon.dao.implementation;

import org.angel.pokemon.connection.DatabaseConnection;
import org.angel.pokemon.dao.PokemonAbilityDAO;
import org.angel.pokemon.model.PokemonAbility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonAbilityDAOImp implements PokemonAbilityDAO {
    private final String TABLE_NAME = "pokemonability";


    public PokemonAbilityDAOImp() {
    }

    @Override
    public void create(PokemonAbility pokemonAbility) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (null, ?)";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pokemonAbility.getAbilityName());
        ps.executeUpdate();
    }

    @Override
    public PokemonAbility read(Integer id) throws SQLException {
        PokemonAbility pokemonAbility = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            pokemonAbility = new PokemonAbility(rs.getInt(1), rs.getString(2));
        }
        return pokemonAbility;
    }

    @Override
    public void update(PokemonAbility pokemonAbility) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET ability_name = ? WHERE id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, pokemonAbility.getAbilityName());
        ps.setInt(2, pokemonAbility.getId());
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
    public List<PokemonAbility> readAll() throws SQLException {
        List<PokemonAbility> pokemonAbilityList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            pokemonAbilityList.add(
                    new PokemonAbility(rs.getInt(1), rs.getString(2))
            );
        }
        return pokemonAbilityList;
    }
}
