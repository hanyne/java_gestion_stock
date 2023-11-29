package controlleur;


import models.Fournisseur;
import configuration.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FournisseurController {
    private Connection connection;

    public FournisseurController() {
        this.connection = MySQLConnection.getConnection(); // Récupération de la connexion à la base de données
    }
    public List<Fournisseur> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();

        try {
            String query = "SELECT * FROM Fournisseur";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fournisseurId = resultSet.getInt("fournisseurId");
                int cin = resultSet.getInt("Cin");
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                int tel = resultSet.getInt("Tel");
                String email = resultSet.getString("Email");
                String adr = resultSet.getString("Adr");

                Fournisseur fournisseur = new Fournisseur(fournisseurId, cin, fName, lName, tel, email, adr);
                fournisseurs.add(fournisseur);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fournisseurs;
    }
  
            

 // Méthode pour récupérer un fournisseur par son ID
    public Fournisseur getFournisseurById(int fournisseurId) {
        Fournisseur fournisseur = null;

        try {
            String query = "SELECT * FROM Fournisseur WHERE fournisseurId = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, fournisseurId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int cin = resultSet.getInt("Cin");
                String fName = resultSet.getString("fName");
                String lName = resultSet.getString("lName");
                int tel = resultSet.getInt("Tel");
                String email = resultSet.getString("Email");
                String adr = resultSet.getString("Adr");

                fournisseur = new Fournisseur(fournisseurId, cin, fName, lName, tel, email, adr);
            }

            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fournisseur;
    }

    public void ajouterFournisseur(Fournisseur fournisseur) {
        try {
            String query = "INSERT INTO Fournisseur (fournisseurId, Cin, fName, lName, Tel, Email, Adr) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, fournisseur.getFournisseurId());
            statement.setInt(2, fournisseur.getCin());
            statement.setString(3, fournisseur.getfName());
            statement.setString(4, fournisseur.getlName());
            statement.setInt(5, fournisseur.getTel());
            statement.setString(6, fournisseur.getEmail());
            statement.setString(7, fournisseur.getAdr());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
            	JOptionPane.showMessageDialog(null, "Le fournisseur a été ajouté avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
            	JOptionPane.showMessageDialog(null, "Échec de l'ajout du fournisseur.", "Échec", JOptionPane.ERROR_MESSAGE);
            }
            
            statement.close();
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du fournisseur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

// Méthode pour mettre à jour les détails d'un fournisseur
public void modifierFournisseur(Fournisseur fournisseur) {
    try {
        String query = "UPDATE Fournisseur SET Cin=?, fName=?, lName=?, Tel=?, Email=?, Adr=? WHERE fournisseurId=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, fournisseur.getCin());
        statement.setString(2, fournisseur.getfName());
        statement.setString(3, fournisseur.getlName());
        statement.setInt(4, fournisseur.getTel());
        statement.setString(5, fournisseur.getEmail());
        statement.setString(6, fournisseur.getAdr());
        statement.setInt(7, fournisseur.getFournisseurId());

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
        	JOptionPane.showMessageDialog(null, "Le fournisseur a été modifié avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(null, "Échec de la mise à jour des détails du fournisseur.", "Échec", JOptionPane.ERROR_MESSAGE);
        }

        statement.close();
    } catch (SQLException e) {
    	JOptionPane.showMessageDialog(null, "Erreur lors de modification du fournisseur : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }


// Méthode pour supprimer un fournisseur par son ID
public void supprimerFournisseur(int fournisseurId) {
    try {
        // Check if the fournisseur has associated produits
        boolean hasAssociatedProduit = hasAssociatedProduit(fournisseurId);

        if (hasAssociatedProduit) {
            // If associated produits exist, display a message that deletion is not allowed
            JOptionPane.showMessageDialog(null, "On ne peut pas supprimer un fournisseur qui est attaché à un produit.", "Erreur de suppression", JOptionPane.ERROR_MESSAGE);
        } else {
            // If no associated produits, proceed with deletion
            String deleteFournisseurQuery = "DELETE FROM Fournisseur WHERE fournisseurId=?";
            PreparedStatement deleteFournisseurStatement = connection.prepareStatement(deleteFournisseurQuery);
            deleteFournisseurStatement.setInt(1, fournisseurId);

            int rowsDeleted = deleteFournisseurStatement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Le fournisseur a été supprimé avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Échec de la suppression du fournisseur.", "Échec", JOptionPane.ERROR_MESSAGE);
            }

            deleteFournisseurStatement.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
        public boolean hasAssociatedProduit(int fournisseurId) throws SQLException {
            String countProduitsQuery = "SELECT COUNT(*) FROM Produit WHERE fournisseurId=?";
            PreparedStatement countProduitsStatement = connection.prepareStatement(countProduitsQuery);
            countProduitsStatement.setInt(1, fournisseurId);
            ResultSet resultSet = countProduitsStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);

            countProduitsStatement.close();
            resultSet.close();

            return count > 0;
        }
 
}



