package vue;

import controlleur.FournisseurController;
import controlleur.ProduitController;
import models.Fournisseur;
import models.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class AddProduitForm extends JFrame {
    private JTextField nameField, descriptionField, priceField, qteField;
    private JButton chooseImageButton, addButton;
    private ImageIcon selectedImage;

    private JCheckBox statusCheckBox;
    private JComboBox<String> fournisseurComboBox;
    

    public AddProduitForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FournisseurController fournisseurController = new FournisseurController();
        List<Fournisseur> fournisseurs = fournisseurController.getAllFournisseurs();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        chooseImageButton = new JButton("Choisir une image");
        nameField = new JTextField(20);
        descriptionField = new JTextField(20);
        priceField = new JTextField(20);
        qteField = new JTextField(20);
        statusCheckBox = new JCheckBox();
        addButton = new JButton("Ajouter");
        fournisseurComboBox = new JComboBox<>();
        fournisseurComboBox = new JComboBox<>();
        for (Fournisseur fournisseur : fournisseurs) {
            fournisseurComboBox.addItem(fournisseur.getfName() + " " + fournisseur.getlName());
        }

       
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Nom:"), constraints);

        constraints.gridy = 1;
        panel.add(new JLabel("Description:"), constraints);

        constraints.gridy = 2;
        panel.add(new JLabel("Prix:"), constraints);

        constraints.gridy = 3;
        panel.add(new JLabel("Quantité:"), constraints);

        constraints.gridy = 4;
        panel.add(new JLabel("Fournisseur:"), constraints);
        constraints.gridy = 5;
        panel.add(new JLabel("Status:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(nameField, constraints);

        constraints.gridy = 1;
        panel.add(descriptionField, constraints);

        constraints.gridy = 2;
        panel.add(priceField, constraints);

        constraints.gridy = 3;
        panel.add(qteField, constraints);

        constraints.gridy = 4;
        constraints.gridwidth = 2;
        fournisseurComboBox.setPreferredSize(new Dimension(200, 25));
        panel.add(fournisseurComboBox, constraints);

        constraints.gridy = 5;
        panel.add(statusCheckBox, constraints);

        constraints.gridy = 6;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        panel.add(addButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridheight = 7;
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        panel.add(imageLabel, constraints);

        constraints.gridx = 2;
        constraints.gridy = 7;
        constraints.gridheight = 1;
        panel.add(chooseImageButton, constraints);
        
        
        

        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();
                    System.out.println("Chemin de l'image : " + imagePath);

                    ImageIcon imageIcon = resizeImage(imagePath, 150, 150);
                    if (imageIcon != null) {
                    	selectedImage = new ImageIcon(imagePath);
                        imageLabel.setIcon(selectedImage);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Erreur lors du redimensionnement de l'image.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedImage == null) {
                    JOptionPane.showMessageDialog(null, "Veuillez choisir une image.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String name = nameField.getText();
                String description = descriptionField.getText();
                double price = Double.parseDouble(priceField.getText());
                int qte = Integer.parseInt(qteField.getText());
                boolean status = statusCheckBox.isSelected();

                String selectedSupplierName = (String) fournisseurComboBox.getSelectedItem();
                int fournisseurId = findSupplierId(selectedSupplierName, fournisseurs);

                String imagePath = selectedImage.getDescription();

                Produit produit = new Produit(0, imagePath, name, description, price, qte, status, fournisseurId);

                ProduitController produitController = new ProduitController();
                produitController.ajouterProduit(produit);

                dispose();
            }
        });

        setContentPane(panel);
        setTitle("Ajouter un Produit");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
    private ImageIcon resizeImage(String imagePath, int width, int height) {
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }
    private int findSupplierId(String selectedSupplierName, List<Fournisseur> fournisseurs) {
        for (Fournisseur fournisseur : fournisseurs) {
            if ((fournisseur.getfName() + " " + fournisseur.getlName()).equals(selectedSupplierName)) {
                return fournisseur.getFournisseurId();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddProduitForm::new);
    }
}

