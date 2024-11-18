package org.angel.pokemon.model;
import java.io.Serializable;
import java.util.Objects;

public class PokemonType implements Serializable {
    private int id;
    private String typeName;

    public PokemonType() {
    }


    public PokemonType(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() { return id; }
    public String getTypeName() { return typeName; }


    public void setId(int id) {
        this.id = id;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PokemonType that = (PokemonType) object;
        return Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }
}
