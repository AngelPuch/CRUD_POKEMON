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

public class AddPokemonFormController {
    private JFrame addPokemonForm;
    private JTextField pokemonNameField;
    private JTextField pokemonHeightField;
    private JTextField pokemonWeightField;
    private JTextField pokemonBaseExperienceField;
    private JComboBox<PokemonType> comboType;
    private PokemonDAO pokemonDAO;

    public AddPokemonFormController(JFrame addPokemonForm, JTextField pokemonNameField, JTextField pokemonHeightField, JTextField pokemonWeightField, JTextField pokemonBaseExperienceField, JComboBox<PokemonType> comboType) {
        this.addPokemonForm = addPokemonForm;
        this.pokemonNameField = pokemonNameField;
        this.pokemonHeightField = pokemonHeightField;
        this.pokemonWeightField = pokemonWeightField;
        this.pokemonBaseExperienceField = pokemonBaseExperienceField;
        this.comboType = comboType;
        this.pokemonDAO = new PokemonDAOImp();
    }

    public void addPokemon() throws SQLException{
        Pokemon pokemon = null;
        pokemon = convertoToPokemon();
        pokemonDAO.create(pokemon);
    }

    public void fillComboBox() {
        PokemonTypeDAO pokemonTypeDAO = new PokemonTypeDAOImp();
        List<PokemonType> pokemonTypeList;
        try {
            pokemonTypeList = pokemonTypeDAO.readAll();
            for (PokemonType type: pokemonTypeList){
                comboType.addItem(type);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(addPokemonForm, "Error al consultar los tipos de pokemon");
        }

    }
    
    private Pokemon convertoToPokemon(){
        Pokemon pokemon = new Pokemon();
        PokemonType type = (PokemonType)comboType.getSelectedItem();
        pokemon.setName(pokemonNameField.getText());
        pokemon.setHeight(Float.parseFloat(pokemonHeightField.getText()));
        pokemon.setWeight(Float.parseFloat(pokemonWeightField.getText()));
        pokemon.setBaseExperience(Integer.parseInt(pokemonBaseExperienceField.getText()));
        pokemon.setType(type);

        return pokemon;
    }
}
