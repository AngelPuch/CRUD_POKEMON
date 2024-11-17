package org.angel.pokemon.gui;

import org.angel.pokemon.events.AddPokemonFormController;
import org.angel.pokemon.events.UpdatePokemonFormController;
import org.angel.pokemon.model.Pokemon;

import javax.swing.*;
import java.awt.*;

public class UpdatePokemonForm extends JFrame {
    private JTextField pokemonNameField;
    private JTextField pokemonHeightField;
    private JTextField pokemonWeightField;
    private JTextField pokemonBaseExperienceField;
    private JTextField pokemonTypeField;
    private Pokemon pokemon;
    private UpdatePokemonFormController controller;

    public UpdatePokemonForm(Pokemon pokemon){
        this.pokemon = pokemon;
        setTitle("Add Pokemon Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponent();
        this.controller = new UpdatePokemonFormController(this, pokemonNameField, pokemonHeightField,
                pokemonWeightField, pokemonBaseExperienceField, pokemonTypeField, pokemon);
        setVisible(true);

    }

    private void initComponent(){
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        pokemonNameField = new JTextField(pokemon.getName());
        pokemonHeightField = new JTextField(String.valueOf(pokemon.getHeight()));
        pokemonWeightField = new JTextField(String.valueOf(pokemon.getWeight()));
        pokemonBaseExperienceField = new JTextField(String.valueOf(pokemon.getBaseExperience()));
        pokemonTypeField = new JTextField(String.valueOf(pokemon.getType().getId()));

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
        JButton updateButton = new JButton("Actualizar");
        JButton cancelButton = new JButton("Cancelar");

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        updateButton.addActionListener(e -> controller.updatePokemon());
        cancelButton.addActionListener(e -> controller.cancel());

    }

}
