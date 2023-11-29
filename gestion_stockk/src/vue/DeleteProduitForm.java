package vue;

import controlleur.ProduitController;
import models.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteProduitForm extends JFrame {
    private JComboBox<String> produitList;
    private JButton deleteButton;

    private ProduitController produitController;

    public DeleteProduitForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        produitController = new ProduitController();

        JPanel panel = new JPanel(new GridLayout(2, 2));

        // Récupérer la liste des produits
        List<Produit> produits = produitController.getAllProduits();

        // Créer une liste déroulante de noms de produits
        produitList = new JComboBox<>();
        for (Produit produit : produits) {
            produitList.addItem(produit.getNameP());
        }

        deleteButton = new JButton("Supprimer");

        panel.add(new JLabel("Sélectionner un produit :"));
        panel.add(produitList);
        panel.add(new JLabel()); // Espacement
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer l'index du produit sélectionné
                int selectedProduitIndex = produitList.getSelectedIndex();
                Produit selectedProduit = produits.get(selectedProduitIndex);

                // Supprimer le produit en utilisant son ID
                produitController.supprimerProduit(selectedProduit.getProduitId());

                // Mettre à jour la liste déroulante après suppression
                produitList.removeItemAt(selectedProduitIndex);
                produitList.revalidate();
                produitList.repaint();
            }
        });

        setContentPane(panel);
        setTitle("Supprimer un Produit");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteProduitForm::new);
    }
}
