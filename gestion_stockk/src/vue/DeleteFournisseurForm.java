package vue;

import controlleur.FournisseurController;
import models.Fournisseur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class DeleteFournisseurForm extends JFrame {
    private JComboBox<String> fournisseurList;
    private JButton deleteButton;

    private FournisseurController fournisseurController;

    public DeleteFournisseurForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        fournisseurController = new FournisseurController();

        JPanel panel = new JPanel(new GridLayout(2, 2));

        // Récupérer la liste des fournisseurs
        List<Fournisseur> fournisseurs = fournisseurController.getAllFournisseurs();

        // Créer une liste déroulante de noms de fournisseurs
        fournisseurList = new JComboBox<>();
        for (Fournisseur fournisseur : fournisseurs) {
            fournisseurList.addItem(fournisseur.getfName() + " " + fournisseur.getlName());
        }

        deleteButton = new JButton("Supprimer");

        panel.add(new JLabel("Sélectionner un fournisseur :"));
        panel.add(fournisseurList);
        panel.add(new JLabel()); // Espacement
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedFournisseurIndex = fournisseurList.getSelectedIndex();
                Fournisseur selectedFournisseur = fournisseurs.get(selectedFournisseurIndex);

                try {
                    boolean hasAssociatedProduit = fournisseurController.hasAssociatedProduit(selectedFournisseur.getFournisseurId());

                    if (hasAssociatedProduit) {
                        JOptionPane.showMessageDialog(null, "On ne peut pas supprimer un fournisseur qui est attaché à un produit.");
                    } else {
                        int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce fournisseur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirmation == JOptionPane.YES_OPTION) {
                            fournisseurController.supprimerFournisseur(selectedFournisseur.getFournisseurId());
                            fournisseurList.removeItemAt(selectedFournisseurIndex);
                            fournisseurList.revalidate();
                            fournisseurList.repaint();
                            JOptionPane.showMessageDialog(null, "Fournisseur supprimé avec succès !");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace(); 
                }
            }
        });


        setContentPane(panel);
        setTitle("Supprimer un Fournisseur");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DeleteFournisseurForm::new);
    }
}
