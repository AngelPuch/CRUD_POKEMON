package org.angel.pokemon.gui;

import org.angel.pokemon.events.PokemonManagerController;
import org.angel.pokemon.model.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PokemonManager extends JFrame {
    private DefaultListModel<Pokemon> pokemonListModel;
    private JList<Pokemon> pokemonList;
    private JTextField searchField;
    private List<Pokemon> pokemons;
    private PokemonManagerController controller;

    public PokemonManager(){
        setTitle("Gestión de Pokemon");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pokemons = new ArrayList<>();
        pokemonListModel = new DefaultListModel<>();
        pokemonList = new JList<>(pokemonListModel);
        searchField = new JTextField();

        controller = new PokemonManagerController(this, pokemonListModel, pokemonList, searchField, pokemons);
        initComponents();
        setVisible(true);

    }

    private void initComponents(){
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel searchLabel = new JLabel("Buscar Pokemon:");
        topPanel.add(searchLabel, BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Eliminar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(pokemonList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        searchField.addActionListener(e -> controller.filterPokemonList());

        addButton.addActionListener(e -> controller.addPokemon());
        updateButton.addActionListener(e -> controller.updatePokemon());

        deleteButton.addActionListener(e -> {
            try {
                controller.deletePokemon();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el registro");
            }
        });

        try {
            controller.loadPokemons();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los registros");
        }
    }
}
