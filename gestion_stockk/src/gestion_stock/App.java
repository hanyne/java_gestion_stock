package gestion_stock;

import controlleur.LoginController;
import vue.LoginView;
import configuration.MySQLConnection;
import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        // Récupérer la connexion à la base de données en utilisant la méthode getConnection()
        Connection connection = MySQLConnection.getConnection();

        // Créer une instance de LoginView
        LoginView loginView = new LoginView();
        // Création du contrôleur et liaison avec la vue
        LoginController loginController = new LoginController(loginView, connection);
        loginView.setController(loginController);
        // Affichage de l'interface de login
        loginView.display();
    }
}


