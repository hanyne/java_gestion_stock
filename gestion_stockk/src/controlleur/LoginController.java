package controlleur;

import models.User;
import vue.LoginView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    private LoginView vue;
    private Connection connection; // Connexion à la base de données

    public LoginController(LoginView view, Connection conn) {
        this.vue = view;
        this.connection = conn;
        this.vue.setController(this);
    }

    public void validateLogin(String pseudo, String password) {
        try {
            String query = "SELECT * FROM User WHERE pseudo = ? AND UserPassword = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pseudo);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Connexion réussie !");
                vue.onLoginSuccess();
            } else {
                System.out.println("Identifiants incorrects !");
                vue.onLoginFailure();
            }
            

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
