package org.angel.pokemon.events;

import org.angel.pokemon.dao.PokemonDAO;
import org.angel.pokemon.dao.implementation.PokemonDAOImp;
import org.angel.pokemon.gui.AddPokemonForm;
import org.angel.pokemon.gui.PokemonManager;
import org.angel.pokemon.gui.UpdatePokemonForm;
import org.angel.pokemon.model.Pokemon;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

public class PokemonManagerController {
    private PokemonManager pokemonManager;
    private DefaultListModel<Pokemon> pokemonListModel;
    private JList<Pokemon> pokemonList;
    private JTextField searchField;
    private List<Pokemon> pokemons;
    private PokemonDAO pokemonDAO;

    public PokemonManagerController(PokemonManager pokemonManager, DefaultListModel<Pokemon> pokemonListModel,
                                    JList<Pokemon> pokemonList, JTextField searchField, List<Pokemon> pokemons) {
        this.pokemonManager = pokemonManager;
        this.pokemonListModel = pokemonListModel;
        this.pokemonList = pokemonList;
        this.searchField = searchField;
        this.pokemons = pokemons;
        this.pokemonDAO = new PokemonDAOImp();
    }


    public void loadPokemons() throws SQLException {
        pokemons = pokemonDAO.readAll();
        updatePokemonList();
    }

    public void updatePokemonList() {
        pokemonListModel.clear();
        for (Pokemon pokemon : pokemons) {
            pokemonListModel.addElement(pokemon);
        }
    }
    public void filterPokemonList() {
        String searchText = searchField.getText().toLowerCase();
        pokemonListModel.clear();
        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().toLowerCase().contains(searchText)) {
                pokemonListModel.addElement(pokemon);
            }
        }
    }

    public void addPokemon(){
        AddPokemonForm addForm = new AddPokemonForm();
        addForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    loadPokemons();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void updatePokemon(){
        Pokemon selectedPokemon = pokemonList.getSelectedValue();
        if (selectedPokemon != null) {
            UpdatePokemonForm updateForm = new UpdatePokemonForm(selectedPokemon);
            updateForm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        loadPokemons();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        } else {
            JOptionPane.showMessageDialog(pokemonManager, "Seleccione un Pokemon para actualizar.");
        }
    }

    public void deletePokemon() throws SQLException {
        Pokemon selectedPokemon = pokemonList.getSelectedValue();
        if (selectedPokemon != null) {
            pokemonDAO.delete(selectedPokemon.getId());
            loadPokemons();
            JOptionPane.showMessageDialog(pokemonManager, "Pokemon eliminado correctamente.");
        } else {
            JOptionPane.showMessageDialog(pokemonManager, "Seleccione un Pokemon para eliminar.");
        }
    }
}
