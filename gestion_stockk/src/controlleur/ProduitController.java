package controlleur;

import models.Produit;
import configuration.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProduitController {
    private Connection connection;

    public ProduitController() {
        this.connection = MySQLConnection.getConnection(); // Récupération de la connexion à la base de données
    }

    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();

        try {
            String query = "SELECT p.*, f.fName, f.lName " +
                           "FROM Produit p " +
                           "INNER JOIN Fournisseur f ON p.fournisseurId = f.fournisseurId";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int produitId = resultSet.getInt("produitId");
                String imageP = resultSet.getString("ImageP");
                String nameP = resultSet.getString("nameP");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                int qte = resultSet.getInt("Qte");
                boolean status = resultSet.getBoolean("Status");
                int fournisseurId = resultSet.getInt("fournisseurId");
                String fournisseurName = resultSet.getString("fName") + " " + resultSet.getString("lName");

                Produit produit = new Produit(produitId, imageP, nameP, description, price, qte, status, fournisseurId);
                produit.setFournisseurName(fournisseurName); // Ajouter le nom du fournisseur
                produits.add(produit);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produits;
    }



    public Produit getProduitById(int produitId) {
        Produit produit = null;

        try {
            String query = "SELECT * FROM Produit WHERE produitId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, produitId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String imageP = resultSet.getString("ImageP");
                String nameP = resultSet.getString("nameP");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                int qte = resultSet.getInt("Qte");
                boolean status = resultSet.getBoolean("Status");
                int fournisseurId = resultSet.getInt("fournisseurId");

                produit = new Produit(produitId, imageP, nameP, description, price, qte, status, fournisseurId);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produit;
    }

    public void ajouterProduit(Produit produit) {
        try {
            String query = "INSERT INTO Produit (produitId, ImageP, nameP, Description, Price, Qte, Status, fournisseurId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, produit.getProduitId());
            statement.setString(2, produit.getImageP());
            statement.setString(3, produit.getNameP());
            statement.setString(4, produit.getDescription());
            statement.setDouble(5, produit.getPrice());
            statement.setInt(6, produit.getQte());
            statement.setBoolean(7, produit.isStatus());
            statement.setInt(8, produit.getFournisseurId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
            	JOptionPane.showMessageDialog(null, "Le produit a été ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(null, "Échec de l'ajout d'un produit.", "Échec", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout d'un produit : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void modifierProduit(Produit produit) {
        try {
            String query = "UPDATE Produit SET ImageP=?, nameP=?, Description=?, Price=?, Qte=?, Status=?, fournisseurId=? WHERE produitId=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, produit.getImageP());
            statement.setString(2, produit.getNameP());
            statement.setString(3, produit.getDescription());
            statement.setDouble(4, produit.getPrice());
            statement.setInt(5, produit.getQte());
            statement.setBoolean(6, produit.isStatus());
            statement.setInt(7, produit.getFournisseurId());
            statement.setInt(8, produit.getProduitId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
            	JOptionPane.showMessageDialog(null, "Le produit a été modifié avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
             	JOptionPane.showMessageDialog(null, "Échec de la mise à jour des détails du produit.", "Échec", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erreur lors de modification d'un produit : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void supprimerProduit(int produitId) {
        try {
            String query = "DELETE FROM Produit WHERE produitId=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, produitId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Le produit a été supprimé avec succès !");
            } else {
                System.out.println("Échec de la suppression du produit.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
