package vue;

import controlleur.FournisseurController;
import controlleur.ProduitController;
import models.Fournisseur;
import models.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierProduitForm extends JFrame {
    // Existing fields
    private JTextField imageField, nameField, descriptionField, priceField, qteField;
    private JCheckBox statusCheckBox;
    private JButton modifierButton;
    private ProduitController produitController;
    private Produit produit;
    
    // Additional field for supplier name
    private JLabel fournisseurLabel;

    public ModifierProduitForm(Produit produit) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.produit = produit;
        produitController = new ProduitController();

        JPanel panel = new JPanel(new GridLayout(9, 2));

        // Existing text fields and checkboxes
        imageField = new JTextField(produit.getImageP(), 10);
        nameField = new JTextField(produit.getNameP(), 10);
        descriptionField = new JTextField(produit.getDescription(), 10);
        priceField = new JTextField(String.valueOf(produit.getPrice()), 10);
        qteField = new JTextField(String.valueOf(produit.getQte()), 10);
        statusCheckBox = new JCheckBox(String.valueOf(produit.isStatus()));
        modifierButton = new JButton("Modifier");

        // Display label for supplier name
        fournisseurLabel = new JLabel();
        panel.add(new JLabel("Fournisseur:"));
        panel.add(fournisseurLabel);

        panel.add(new JLabel("Image:"));
        panel.add(imageField);
        panel.add(new JLabel("Nom:"));
        panel.add(nameField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Prix:"));
        panel.add(priceField);
        panel.add(new JLabel("Quantité:"));
        panel.add(qteField);
        panel.add(new JLabel("Status:"));
        panel.add(statusCheckBox);

        panel.add(new JLabel()); // Spacing
        panel.add(modifierButton);

        // Populate supplier name label
        FournisseurController fournisseurController = new FournisseurController();
        Fournisseur fournisseur = fournisseurController.getFournisseurById(produit.getFournisseurId());
        if (fournisseur != null) {
            fournisseurLabel.setText(fournisseur.getfName() + " " + fournisseur.getlName());
        }

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update product data with form inputs
                produit.setImageP(imageField.getText());
                produit.setNameP(nameField.getText());
                produit.setDescription(descriptionField.getText());
                produit.setPrice(Double.parseDouble(priceField.getText()));
                produit.setQte(Integer.parseInt(qteField.getText()));
                produit.setStatus(statusCheckBox.isSelected());

                // Update the product using the controller
                produitController.modifierProduit(produit);

                dispose();
            }
        });

        setContentPane(panel);
        setTitle("Modifier un Produit");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
