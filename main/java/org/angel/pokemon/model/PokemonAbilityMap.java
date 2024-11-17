package org.angel.pokemon.model;
import java.io.Serializable;

public class PokemonAbilityMap implements Serializable {
    private Pokemon pokemon;
    private PokemonAbility ability;

    public PokemonAbilityMap() {
    }

    public PokemonAbilityMap(Pokemon pokemon, PokemonAbility ability) {
        this.pokemon = pokemon;
        this.ability = ability;
    }

    public Pokemon getPokemon() { return pokemon; }
    public PokemonAbility getAbility() { return ability; }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setAbility(PokemonAbility ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return pokemon.toString() +
                ability.toString();
    }
}
