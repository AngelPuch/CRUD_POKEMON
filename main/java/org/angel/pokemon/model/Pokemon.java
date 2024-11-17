package org.angel.pokemon.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id;
    private String name;
    private float height;
    private float weight;
    private int baseExperience;
    private PokemonType type;

    public Pokemon() {
    }

    public Pokemon(int id, String name, float height, float weight, int baseExperience, PokemonType type) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.baseExperience = baseExperience;
        this.type = type;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public float getHeight() { return height; }
    public float getWeight() { return weight; }
    public int getBaseExperience() { return baseExperience; }
    public PokemonType getType() { return type; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) {
        this.name = name;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }
    public void setType(PokemonType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return name;
    }
    /*@Override
    public String toString() {
        return "Pokemon id = " + id +
                ", name = " + name +
                ", height = " + height +
                ", weight = " + weight +
                ", baseExperience = " + baseExperience +
                type.toString();
    }*/
}
