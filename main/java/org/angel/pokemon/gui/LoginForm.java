package org.angel.pokemon.gui;

import org.angel.pokemon.events.LoginFormListener;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;
    private JButton logginButton;

    public LoginForm(){
        setTitle("Login form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponent();
    }

    private void initComponent(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(Color.BLACK);

        logginButton = new JButton("Iniciar Sesion");
        logginButton.addActionListener(new LoginFormListener(this, usernameField, passwordField, messageLabel));

        panel.add(new JLabel("User: ", SwingConstants.CENTER));
        panel.add(usernameField);
        panel.add(new JLabel("Password: ", SwingConstants.CENTER));
        panel.add(passwordField);

        add(panel, BorderLayout.CENTER);
        add(logginButton, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.NORTH);

    }
}
