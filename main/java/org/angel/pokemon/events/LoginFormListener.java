package org.angel.pokemon.events;

import org.angel.pokemon.dao.UserDAO;
import org.angel.pokemon.dao.implementation.UserDAOImp;
import org.angel.pokemon.gui.PokemonManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFormListener implements ActionListener {
    private JFrame loginForm;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    public LoginFormListener(JFrame loginForm, JTextField usernameField, JPasswordField passwordField, JLabel messageLabel) {
        this.loginForm = loginForm;
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.messageLabel = messageLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String (passwordField.getPassword());
        if (authenticate(username, password)) {
            messageLabel.setText("Inicio de sesión exitoso");
            JOptionPane.showMessageDialog(loginForm, "Bienvenido " + username);
            loginForm.dispose();
            new PokemonManager();
        } else {
            messageLabel.setText("Usuario o contraseña incorrectos");
        }

    }

    private boolean authenticate(String username, String password) {
        try {
            UserDAO user = new UserDAOImp();
            if (user.findUserByUsernameAndPassword(username, password) != null){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
