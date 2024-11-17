package org.angel.pokemon.model;
import java.io.Serializable;

public class PokemonAbility implements Serializable {
    private int id;
    private String abilityName;

    public PokemonAbility() {
    }

    public PokemonAbility(int id, String abilityName) {
        this.id = id;
        this.abilityName = abilityName;
    }

    public int getId() { return id; }
    public String getAbilityName() { return abilityName; }

    public void setId(int id) {
        this.id = id;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    @Override
    public String toString() {
        return ", Ability id = " + id +
                ", abilityName = " + abilityName;
    }
}
