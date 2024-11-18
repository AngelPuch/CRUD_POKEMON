package org.angel.pokemon.dao.implementation;

import org.angel.pokemon.dao.PokemonDAO;
import org.angel.pokemon.model.Pokemon;
import org.angel.pokemon.model.PokemonType;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonDAOImpTest {
    @Test
    void testCreatePokemon() throws SQLException {
        PokemonDAO pokemonDAO = new PokemonDAOImp();
        Pokemon pokemon = new Pokemon(32, "Charmander", 0.6F, 8.5F, 62, new PokemonType(1, "Fire"));

        pokemonDAO.create(pokemon);
        Pokemon retrievedPokemon = pokemonDAO.read(32);
        assertNotNull(retrievedPokemon);
        assertEquals("Charmander", retrievedPokemon.getName());
        assertEquals(0.6F, retrievedPokemon.getHeight());
        assertEquals(8.5F, retrievedPokemon.getWeight());
        assertEquals(62, retrievedPokemon.getBaseExperience());
        assertEquals("Fire", retrievedPokemon.getType().getTypeName());

    }

    @Test
    void testReadPokemon() throws SQLException {
        PokemonDAO pokemonDAO = new PokemonDAOImp();
        Pokemon pokemon = pokemonDAO.read(1);

        assertNotNull(pokemon);
        assertEquals(1, pokemon.getId());
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(0.7F, pokemon.getHeight());
        assertEquals(6.9F, pokemon.getWeight());
        assertEquals(64, pokemon.getBaseExperience());
        assertEquals("Grass", pokemon.getType().getTypeName());
    }

    @Test
    void testUpdatePokemon() throws SQLException {
        PokemonDAO pokemonDAO = new PokemonDAOImp();
        Pokemon pokemon = pokemonDAO.read(1);
        pokemon.setName("Charizard");
        pokemonDAO.update(pokemon);

        Pokemon updatedPokemon = pokemonDAO.read(1);
        assertEquals("Charizard", updatedPokemon.getName());
    }

    @Test
    void testDeletePokemon() throws SQLException {
        PokemonDAO pokemonDAO = new PokemonDAOImp();
        pokemonDAO.delete(24);

        Pokemon deletedPokemon = pokemonDAO.read(24);
        assertNull(deletedPokemon);
    }

    @Test
    void testReadAllPokemon() throws SQLException {
        PokemonDAO pokemonDAO = new PokemonDAOImp();
        List<Pokemon> pokemonList = pokemonDAO.readAll();

        assertNotNull(pokemonList);
        assertEquals("Charizard", pokemonList.get(0).getName());
        assertEquals("Squirtle", pokemonList.get(1).getName());
        assertEquals("Pikachu", pokemonList.get(2).getName());
        assertEquals("Charmander", pokemonList.get(3).getName());

    }
}