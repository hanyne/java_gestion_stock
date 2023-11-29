package vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlleur.LoginController;

public class LoginView extends JFrame {
    private JTextField pseudoField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private LoginController controller;
  public void setController(LoginController controller) {
        this.controller = controller;
    }
  
  public void onLoginSuccess() {
      // Code à exécuter après une connexion réussie, par exemple afficher la fenêtre de menu
      MenuInterface menu = new MenuInterface();
      menu.setVisible(true);
      this.dispose(); // Ferme la fenêtre de login
  }
  public void onLoginFailure() {
	  System.out.println("Connexion échoué!");
  }

    public LoginView() {
        initialize();
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pseudo = pseudoField.getText();
                String password = new String(passwordField.getPassword());

                // Appeler la méthode du contrôleur pour valider les informations de connexion
                if (controller != null) {
                    controller.validateLogin(pseudo, password);
                } else {
                    System.out.println("Erreur: Contrôleur non initialisé");
                }
            }
        });
    }

    private void initialize() {
        pseudoField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Pseudo:"));
        panel.add(pseudoField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);

        // Ajoutez l'ActionListener après avoir initialisé loginButton
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pseudo = pseudoField.getText();
                String password = new String(passwordField.getPassword());

                // Appeler la méthode du contrôleur pour valider les informations de connexion
                if (controller != null) {
                    controller.validateLogin(pseudo, password);
                } else {
                    System.out.println("Erreur: Contrôleur non initialisé");
                }
            }
        });

        this.add(panel);
    }


    public void display() {
        // Afficher la fenêtre
        this.setTitle("Login");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
