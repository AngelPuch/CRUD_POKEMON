package org.angel.pokemon.events;

import org.angel.pokemon.dao.PokemonDAO;
import org.angel.pokemon.dao.PokemonTypeDAO;
import org.angel.pokemon.dao.implementation.PokemonDAOImp;
import org.angel.pokemon.dao.implementation.PokemonTypeDAOImp;
import org.angel.pokemon.model.Pokemon;
import org.angel.pokemon.model.PokemonType;

import javax.swing.*;
import java.sql.SQLException;

public class UpdatePokemonFormController {
    private JFrame updatePokemonForm;
    private JTextField pokemonNameField;
    private JTextField pokemonHeightField;
    private JTextField pokemonWeightField;
    private JTextField pokemonBaseExperienceField;
    private JTextField pokemonTypeField;
    private Pokemon pokemon;
    private PokemonDAO pokemonDAO;

    public UpdatePokemonFormController(JFrame updatePokemonForm, JTextField pokemonNameField,
                                       JTextField pokemonHeightField, JTextField pokemonWeightField,
                                       JTextField pokemonBaseExperienceField, JTextField pokemonTypeField, Pokemon pokemon) {
        this.updatePokemonForm = updatePokemonForm;
        this.pokemonNameField = pokemonNameField;
        this.pokemonHeightField = pokemonHeightField;
        this.pokemonWeightField = pokemonWeightField;
        this.pokemonBaseExperienceField = pokemonBaseExperienceField;
        this.pokemonTypeField = pokemonTypeField;
        this.pokemon = pokemon;
        this.pokemonDAO = new PokemonDAOImp();
    }

    public void updatePokemon(){
        try {
            pokemon = convertoToPokemon();
            pokemonDAO.update(pokemon);
            JOptionPane.showMessageDialog(updatePokemonForm, "El registro fue actualizado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(updatePokemonForm, "Error al actualizar el registro");
        }
        updatePokemonForm.dispose();
    }

    public void cancel(){
        updatePokemonForm.dispose();
    }

    private Pokemon convertoToPokemon(){
        Pokemon convertedPokemon = new Pokemon();
        convertedPokemon.setId(pokemon.getId());
        convertedPokemon.setName(pokemonNameField.getText());
        convertedPokemon.setHeight(Float.parseFloat(pokemonHeightField.getText()));
        convertedPokemon.setWeight(Float.parseFloat(pokemonWeightField.getText()));
        convertedPokemon.setBaseExperience(Integer.parseInt(pokemonBaseExperienceField.getText()));
        convertedPokemon.setType(searchPokemonType());

        return convertedPokemon;
    }

    private PokemonType searchPokemonType(){
        PokemonTypeDAO pokemonTypeDAO = new PokemonTypeDAOImp();
        PokemonType pokemonType = null;
        try {
            pokemonType = pokemonTypeDAO.read(Integer.valueOf(pokemonTypeField.getText()));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(updatePokemonForm, "El tipo de pokemon no existe");
        }
        return pokemonType;
    }
}
