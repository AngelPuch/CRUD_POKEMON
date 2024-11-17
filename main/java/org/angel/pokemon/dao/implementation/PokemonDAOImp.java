package org.angel.pokemon.dao.implementation;

import org.angel.pokemon.connection.DatabaseConnection;
import org.angel.pokemon.dao.PokemonDAO;
import org.angel.pokemon.model.Pokemon;
import org.angel.pokemon.model.PokemonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOImp implements PokemonDAO {
    private final String TABLE_NAME = "pokemon";

    public PokemonDAOImp() {
    }

    @Override
    public void create(Pokemon pokemon) throws SQLException{
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (null, ?, ?, ?, ?, ?)";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        setPokemonValues(pokemon, ps);
        ps.executeUpdate();
    }

    @Override
    public Pokemon read(Integer id) throws SQLException {
        Pokemon pokemon = null;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            pokemon = convertToPokemon(rs);
        }
        return pokemon;
    }

    @Override
    public void update(Pokemon pokemon) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET name = ?, height = ?, weight = ?, " +
                "base_experience = ?, type_id = ? WHERE id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        setPokemonValues(pokemon, ps);
        ps.setInt(6, pokemon.getId());
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
    public List<Pokemon> readAll() throws SQLException {
        List<Pokemon> pokemonList = new ArrayList<>();
        String query = "SELECT pokemon.id, name, height ,weight, base_experience, type_id, type_name " +
                "FROM pokemon INNER JOIN pokemontype " + "WHERE type_id = pokemontype.id " + "ORDER BY pokemon.id";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            pokemonList.add(convertToPokemon(rs));
        }
        return pokemonList;
    }


    private void setPokemonValues(Pokemon pokemon, PreparedStatement ps) throws SQLException{
        ps.setString(1, pokemon.getName());
        ps.setFloat(2, pokemon.getHeight());
        ps.setFloat(3, pokemon.getWeight());
        ps.setInt(4, pokemon.getBaseExperience());
        ps.setInt(5, pokemon.getType().getId());
    }

    private Pokemon convertToPokemon(ResultSet rs) throws SQLException {
        Pokemon pokemon = new Pokemon(
                rs.getInt(1),
                rs.getString(2),
                rs.getFloat(3),
                rs.getFloat(4),
                rs.getInt(5),
                new PokemonType(rs.getInt(6), rs.getString(7))
        );
        return pokemon;
    }
}
