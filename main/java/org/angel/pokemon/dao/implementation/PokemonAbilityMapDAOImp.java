package org.angel.pokemon.dao.implementation;

import org.angel.pokemon.connection.DatabaseConnection;
import org.angel.pokemon.dao.PokemonAbilityMapDAO;
import org.angel.pokemon.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonAbilityMapDAOImp implements PokemonAbilityMapDAO {
    private final String TABLE_NAME = "pokemonabilitymap";

    public PokemonAbilityMapDAOImp() {
    }

    @Override
    public void create(PokemonAbilityMap pokemonAbilityMap) throws SQLException {
        String query = "INSERT INTO " + TABLE_NAME + " VALUES (?,?)";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, pokemonAbilityMap.getPokemon().getId());
        ps.setInt(1, pokemonAbilityMap.getAbility().getId());
        ps.executeUpdate();
    }

    @Override
    public PokemonAbilityMap read(Integer id) throws SQLException {
        PokemonAbilityMap pokemonAbilityMap = null;
        String sql = "SELECT p.*, pa.*, pt.*, pam.* " +
                "FROM pokemonabilitymap pam " +
                "JOIN pokemon p ON pam.pokemon_id = p.id " +
                "JOIN pokemonability pa ON pam.ability_id = pa.id " +
                "LEFT JOIN pokemontype pt ON p.type_id = pt.id " +
                "WHERE p.id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            pokemonAbilityMap = convertToPokemonAbilityMap(rs);
        }
        return pokemonAbilityMap;
    }

    @Override
    public void update(PokemonAbilityMap pokemonAbilityMap) throws SQLException {
        String query = "UPDATE " + TABLE_NAME + " SET ability_id = ? WHERE pokemon_id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, pokemonAbilityMap.getAbility().getId());
        ps.setInt(2, pokemonAbilityMap.getPokemon().getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM " + TABLE_NAME + " WHERE pokemon_id = ?";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();

    }

    @Override
    public List<PokemonAbilityMap> readAll() throws SQLException {
        List<PokemonAbilityMap> pokemonAbilityMapList = new ArrayList<>();
        String sql = "SELECT p.*, pa.*, pt.*, pam.* " +
                "FROM pokemonabilitymap pam " +
                "JOIN pokemon p ON pam.pokemon_id = p.id " +
                "JOIN pokemonability pa ON pam.ability_id = pa.id " +
                "LEFT JOIN pokemontype pt ON p.type_id = pt.id ";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            pokemonAbilityMapList.add(convertToPokemonAbilityMap(rs));
        }

        return pokemonAbilityMapList;
    }


    private PokemonAbilityMap convertToPokemonAbilityMap(ResultSet rs) throws SQLException {
        PokemonAbilityMap pokemonAbilityMap = new PokemonAbilityMap();
        Pokemon pokemon = new Pokemon();
        pokemon.setId(rs.getInt("id"));
        pokemon.setName(rs.getString("name"));
        pokemon.setHeight(rs.getFloat("height"));
        pokemon.setWeight(rs.getFloat("weight"));
        pokemon.setBaseExperience(rs.getInt("base_experience"));

        PokemonType pokemonType = new PokemonType();
        pokemonType.setId(rs.getInt("type_id"));
        pokemonType.setTypeName(rs.getString("type_name"));
        pokemon.setType(pokemonType);

        PokemonAbility ability = new PokemonAbility();
        ability.setId(rs.getInt("ability_id"));
        ability.setAbilityName(rs.getString("ability_name"));

        pokemonAbilityMap = new PokemonAbilityMap();
        pokemonAbilityMap.setPokemon(pokemon);
        pokemonAbilityMap.setAbility(ability);
        return pokemonAbilityMap;
    }

}
