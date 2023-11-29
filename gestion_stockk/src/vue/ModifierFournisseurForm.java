package vue;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import controlleur.FournisseurController;
import models.Fournisseur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModifierFournisseurForm extends JFrame {
    private JTextField cinField, fNameField, lNameField, telField, emailField, adrField;
    private JButton modifierButton;
    private FournisseurController fournisseurController;
    private Fournisseur fournisseur;

    public ModifierFournisseurForm(Fournisseur fournisseur) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.fournisseur = fournisseur;

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);
        
        ImageIcon iconCIN = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//logg.png"); // Remplacez par le chemin de votre image CIN
        ImageIcon iconPrenom = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//nom.png"); 
        ImageIcon iconNom = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//nom.png"); 
        ImageIcon iconTele = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//tel.png"); // Remplacez par le chemin de votre image CIN
        ImageIcon iconMail = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//mail.png"); 
        ImageIcon iconAdr = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//add.png"); // Remplacez par le chemin de votre image CIN
         
        // Redimensionner les images aux tailles spécifiées
        Image imageCIN = iconCIN.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imagePrenom = iconPrenom.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imageNom = iconNom.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imageTele = iconTele.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imageMail = iconMail.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        Image imageAdr = iconAdr.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        
        iconCIN = new ImageIcon(imageCIN);
        iconPrenom = new ImageIcon(imagePrenom);
        iconNom = new ImageIcon(imageNom);
        iconTele = new ImageIcon(imageTele);
        iconMail = new ImageIcon(imageMail);
        iconAdr = new ImageIcon(imageAdr);
        
        JLabel labelCIN = new JLabel("CIN:", iconCIN, JLabel.LEFT);
        JLabel labelPrenom = new JLabel("Prénom:", iconPrenom, JLabel.LEFT);
        JLabel labelNom = new JLabel("Nom:", iconNom, JLabel.LEFT);
        JLabel labelTele = new JLabel("Adresse:", iconTele, JLabel.LEFT);
        JLabel labelMail = new JLabel("Telephone:", iconMail, JLabel.LEFT);
        JLabel labelAdr = new JLabel("Adresse:", iconAdr, JLabel.LEFT);
        

        cinField = new JTextField(String.valueOf(fournisseur.getCin()), 10);
        fNameField = new JTextField(fournisseur.getfName(), 10);
        lNameField = new JTextField(fournisseur.getlName(), 10);
        telField = new JTextField(String.valueOf(fournisseur.getTel()), 10);
        emailField = new JTextField(fournisseur.getEmail(), 10);
        adrField = new JTextField(fournisseur.getAdr(), 10);
        modifierButton = new JButton("Modifier");
        modifierButton.setBackground(Color.BLUE); // Couleur du bouton
        modifierButton.setForeground(Color.WHITE);



        //add image 
        panel.add(labelCIN);
        panel.add(cinField);
        panel.add(labelPrenom);
        panel.add(fNameField);
        panel.add(labelNom);
        panel.add(lNameField);
        panel.add(labelTele);
        panel.add(telField);
        panel.add(labelMail);
        panel.add(emailField);
        panel.add(labelAdr);
        panel.add(adrField);
        panel.add(new JLabel());
        panel.add(modifierButton);
        
       
        
        
     // Champs de texte avec bordures colorées
        cinField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        fNameField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        lNameField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        telField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        emailField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        adrField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        

        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mettre à jour les données du fournisseur avec celles du formulaire
                fournisseur.setCin(Integer.parseInt(cinField.getText()));
                fournisseur.setfName(fNameField.getText());
                fournisseur.setlName(lNameField.getText());
                fournisseur.setTel(Integer.parseInt(telField.getText()));
                fournisseur.setEmail(emailField.getText());
                fournisseur.setAdr(adrField.getText());

                // Mettre à jour le fournisseur en utilisant le contrôleur
                fournisseurController = new FournisseurController();
                fournisseurController.modifierFournisseur(fournisseur);

                dispose();
            }
        });

        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
        setTitle("Modifier un Fournisseur");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
