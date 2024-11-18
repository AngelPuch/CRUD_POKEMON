package org.angel.pokemon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTypeTest {
    @Test
    void testConstructorPokemonType() {
        PokemonType pokemonType = new PokemonType(1, "Fire");

        assertNotNull(pokemonType);
        assertEquals(1, pokemonType.getId());
        assertEquals("Fire", pokemonType.getTypeName());
    }

    @Test
    void testSettersPokemonType() {
        PokemonType pokemonType = new PokemonType();
        pokemonType.setId(2);
        pokemonType.setTypeName("Water");

        assertEquals(2, pokemonType.getId());
        assertEquals("Water", pokemonType.getTypeName());
    }

    @Test
    void testToString() {
        PokemonType pokemonType = new PokemonType(1, "Electric");

        assertEquals("Electric", pokemonType.toString());
    }

    @Test
    void testEquals() {
        PokemonType pokemonType1 = new PokemonType(1, "Grass");
        PokemonType pokemonType2 = new PokemonType(2, "Grass");

        assertEquals(pokemonType1, pokemonType2);
    }

}