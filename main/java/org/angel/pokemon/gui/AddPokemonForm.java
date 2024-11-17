package org.angel.pokemon.gui;

import org.angel.pokemon.events.AddPokemonFormController;

import javax.swing.*;
import java.awt.*;

public class AddPokemonForm extends JFrame{
    private JTextField pokemonNameField;
    private JTextField pokemonHeightField;
    private JTextField pokemonWeightField;
    private JTextField pokemonBaseExperienceField;
    private JTextField pokemonTypeField;
    private AddPokemonFormController controller;

    public AddPokemonForm(){
        setTitle("Add Pokemon Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponent();
        this.controller = new AddPokemonFormController(this, pokemonNameField, pokemonHeightField,
                pokemonWeightField, pokemonBaseExperienceField, pokemonTypeField);
        setVisible(true);

    }

    private void initComponent(){
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        pokemonNameField = new JTextField();
        pokemonHeightField = new JTextField();
        pokemonWeightField = new JTextField();
        pokemonBaseExperienceField = new JTextField();
        pokemonTypeField = new JTextField();

        formPanel.add(new JLabel("Nombre: "));
        formPanel.add(pokemonNameField);

        formPanel.add(new JLabel("Altura: "));
        formPanel.add(pokemonHeightField);

        formPanel.add(new JLabel("Peso: "));
        formPanel.add(pokemonWeightField);

        formPanel.add(new JLabel("Experiencia Base: "));
        formPanel.add(pokemonBaseExperienceField);

        formPanel.add(new JLabel("Tipo: "));
        formPanel.add(pokemonTypeField);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Añadir");
        JButton cancelButton = new JButton("Cancelar");

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> controller.addPokemon());
        cancelButton.addActionListener(e -> controller.cancel());

    }
}
