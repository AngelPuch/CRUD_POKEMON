package org.angel.pokemon.events;

import org.angel.pokemon.dao.PokemonDAO;
import org.angel.pokemon.dao.PokemonTypeDAO;
import org.angel.pokemon.dao.implementation.PokemonDAOImp;
import org.angel.pokemon.dao.implementation.PokemonTypeDAOImp;
import org.angel.pokemon.model.Pokemon;
import org.angel.pokemon.model.PokemonType;

import javax.swing.*;
import java.sql.SQLException;

public class AddPokemonFormController {
    private JFrame addPokemonForm;
    private JTextField pokemonNameField;
    private JTextField pokemonHeightField;
    private JTextField pokemonWeightField;
    private JTextField pokemonBaseExperienceField;
    private JTextField pokemonTypeField;
    private PokemonDAO pokemonDAO;

    public AddPokemonFormController(JFrame addPokemonForm, JTextField pokemonNameField, JTextField pokemonHeightField,
                                    JTextField pokemonWeightField, JTextField pokemonBaseExperienceField,
                                    JTextField pokemonTypeField) {
        this.addPokemonForm = addPokemonForm;
        this.pokemonNameField = pokemonNameField;
        this.pokemonHeightField = pokemonHeightField;
        this.pokemonWeightField = pokemonWeightField;
        this.pokemonBaseExperienceField = pokemonBaseExperienceField;
        this.pokemonTypeField = pokemonTypeField;
        this.pokemonDAO = new PokemonDAOImp();
    }

    public void addPokemon(){
        Pokemon pokemon = null;
        try {
            pokemon = convertoToPokemon();
            pokemonDAO.create(pokemon);
            JOptionPane.showMessageDialog(addPokemonForm, "El registro fue añadido exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(addPokemonForm, "Error al añadir el registro");
        }
        addPokemonForm.dispose();
    }

    public void cancel(){
        addPokemonForm.dispose();
    }

    private Pokemon convertoToPokemon(){
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonNameField.getText());
        pokemon.setHeight(Float.parseFloat(pokemonHeightField.getText()));
        pokemon.setWeight(Float.parseFloat(pokemonWeightField.getText()));
        pokemon.setBaseExperience(Integer.parseInt(pokemonBaseExperienceField.getText()));
        pokemon.setType(searchPokemonType());

        return pokemon;
    }

    private PokemonType searchPokemonType(){
        PokemonTypeDAO pokemonTypeDAO = new PokemonTypeDAOImp();
        PokemonType pokemonType = null;
        try {
            pokemonType = pokemonTypeDAO.read(Integer.valueOf(pokemonTypeField.getText()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(addPokemonForm, "El tipo de pokemon no existe");
        }
        return pokemonType;
    }
}
