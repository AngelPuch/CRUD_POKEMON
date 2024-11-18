package org.angel.pokemon.events;

import org.angel.pokemon.dao.PokemonDAO;
import org.angel.pokemon.dao.PokemonTypeDAO;
import org.angel.pokemon.dao.implementation.PokemonDAOImp;
import org.angel.pokemon.dao.implementation.PokemonTypeDAOImp;
import org.angel.pokemon.model.Pokemon;
import org.angel.pokemon.model.PokemonType;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class UpdatePokemonFormController {
    private JFrame updatePokemonForm;
    private JTextField pokemonNameField;
    private JTextField pokemonHeightField;
    private JTextField pokemonWeightField;
    private JTextField pokemonBaseExperienceField;
    private JComboBox<PokemonType> comboType;
    private Pokemon pokemon;
    private PokemonDAO pokemonDAO;

    public UpdatePokemonFormController(JFrame updatePokemonForm, JTextField pokemonNameField,
                                       JTextField pokemonHeightField, JTextField pokemonWeightField,
                                       JTextField pokemonBaseExperienceField, JComboBox<PokemonType> comboType,
                                       Pokemon pokemon) {
        this.updatePokemonForm = updatePokemonForm;
        this.pokemonNameField = pokemonNameField;
        this.pokemonHeightField = pokemonHeightField;
        this.pokemonWeightField = pokemonWeightField;
        this.pokemonBaseExperienceField = pokemonBaseExperienceField;
        this.comboType = comboType;
        this.pokemon = pokemon;
        this.pokemonDAO = new PokemonDAOImp();
    }

    public void updatePokemon() throws SQLException{
        pokemon = convertoToPokemon();
        pokemonDAO.update(pokemon);
    }

    public void fillComboBox() {
        PokemonTypeDAO pokemonTypeDAO = new PokemonTypeDAOImp();
        List<PokemonType> pokemonTypeList = null;
        try {
            pokemonTypeList = pokemonTypeDAO.readAll();
            for (PokemonType type: pokemonTypeList){
                comboType.addItem(type);
            }
            comboType.setSelectedItem(pokemon.getType());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(updatePokemonForm, "Error al consultar los tipos de pokemon");
        }

    }

    private Pokemon convertoToPokemon(){
        Pokemon convertedPokemon = new Pokemon();
        PokemonType type = (PokemonType)comboType.getSelectedItem();
        convertedPokemon.setId(pokemon.getId());
        convertedPokemon.setName(pokemonNameField.getText());
        convertedPokemon.setHeight(Float.parseFloat(pokemonHeightField.getText()));
        convertedPokemon.setWeight(Float.parseFloat(pokemonWeightField.getText()));
        convertedPokemon.setBaseExperience(Integer.parseInt(pokemonBaseExperienceField.getText()));
        convertedPokemon.setType(type);

        return convertedPokemon;
    }


}
