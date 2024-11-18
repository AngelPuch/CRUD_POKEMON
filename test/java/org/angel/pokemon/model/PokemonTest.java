package org.angel.pokemon.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {

    @Test
    void testConstructorPokemon() {
        PokemonType type = new PokemonType(1, "Electric");
        Pokemon pokemon = new Pokemon(1, "Bulbasaur", 0.7F, 6.9F, 64, type);

        assertEquals(1, pokemon.getId());
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(0.7F, pokemon.getHeight());
        assertEquals(6.9F, pokemon.getWeight());
        assertEquals(64, pokemon.getBaseExperience());
        assertEquals(type, pokemon.getType());
    }

    @Test
    void testSettersPokemon(){
        Pokemon pokemon = new Pokemon();

        pokemon.setId(2);
        pokemon.setName("Pikachu");
        pokemon.setHeight(0.4F);
        pokemon.setWeight(6);
        pokemon.setBaseExperience(112);
        pokemon.setType(new PokemonType(1, "Fire"));

        assertEquals(2, pokemon.getId());
        assertEquals("Pikachu", pokemon.getName());
        assertEquals(0.4F, pokemon.getHeight());
        assertEquals(6F, pokemon.getWeight());
        assertEquals(112, pokemon.getBaseExperience());
        assertEquals(1, pokemon.getType().getId());
        assertEquals("Fire", pokemon.getType().getTypeName());
    }


}