package vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controlleur.FournisseurController;
import models.Fournisseur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFournisseurForm extends JFrame {
    private JTextField cinField, fNameField, lNameField, telField, emailField, adrField;
    private JButton addButton;

    public AddFournisseurForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10)); // Espacement horizontal et vertical
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
        

       

        cinField = new JTextField(10);
        fNameField = new JTextField(10);
        lNameField = new JTextField(10);
        telField = new JTextField(10);
        emailField = new JTextField(10);
        adrField = new JTextField(10);

        addButton = new JButton("Ajouter");
        addButton.setBackground(Color.BLUE); // Couleur du bouton
        addButton.setForeground(Color.WHITE);
        
     // Champs de texte avec bordures colorées
        cinField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        fNameField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        lNameField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        telField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        emailField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        adrField.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        

     
        
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

        panel.add(new JLabel()); // Espacement
        panel.add(addButton);
        
        

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cin = cinField.getText();
                String fName = fNameField.getText();
                String lName = lNameField.getText();
                String tel = telField.getText();
                String email = emailField.getText();
                String adr = adrField.getText();

                // Créez un objet Fournisseur avec les données du formulaire
                Fournisseur fournisseur = new Fournisseur();
                fournisseur.setCin(Integer.parseInt(cin));
                fournisseur.setfName(fName);
                fournisseur.setlName(lName);
                fournisseur.setTel(Integer.parseInt(tel));
                fournisseur.setEmail(email);
                fournisseur.setAdr(adr);

                // Enregistrez le fournisseur en utilisant le contrôleur
                FournisseurController fournisseurController = new FournisseurController();
                fournisseurController.ajouterFournisseur(fournisseur);

                dispose();
            }
        });


        setContentPane(new JPanel(new BorderLayout()));
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(Box.createVerticalStrut(20), BorderLayout.SOUTH);
        setTitle("Ajouter un Fournisseur");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddFournisseurForm());
    }
}
