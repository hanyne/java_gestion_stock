package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlleur.FournisseurController;
import models.Fournisseur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

public class FournisseurInterface extends JFrame {
    private JTable table;
    private JButton ajoutButton, supprimeButton, modifButton;
    private FournisseurController fournisseurController;

    public FournisseurInterface() {
        fournisseurController = new FournisseurController(); // Instanciation du contrôleur

        setTitle("Interface Fournisseur");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Obtention des données des fournisseurs depuis la base de données
        List<Fournisseur> fournisseurs = fournisseurController.getAllFournisseurs();
        String[] columnNames = {"ID", "CIN", "Prénom", "Nom", "Téléphone", "Email", "Adresse"};
        Object[][] data = new Object[fournisseurs.size()][7];

        // Remplir les données pour le tableau
        for (int i = 0; i < fournisseurs.size(); i++) {
            Fournisseur fournisseur = fournisseurs.get(i);
            data[i][0] = fournisseur.getFournisseurId();
            data[i][1] = fournisseur.getCin();
            data[i][2] = fournisseur.getfName();
            data[i][3] = fournisseur.getlName();
            data[i][4] = fournisseur.getTel();
            data[i][5] = fournisseur.getEmail();
            data[i][6] = fournisseur.getAdr();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Désactiver l'édition des cellules pour la table
            }
        };
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.BLACK);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        table.setIntercellSpacing(new Dimension(10, 10));

        ajoutButton = new JButton("Ajouter");
        supprimeButton = new JButton("Supprimer");
        modifButton = new JButton("Modifier");

        ajoutButton.setFont(new Font("Arial", Font.PLAIN, 14));
        supprimeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        modifButton.setFont(new Font("Arial", Font.PLAIN, 14));
        ajoutButton.setBackground(Color.BLUE);
        ajoutButton.setForeground(Color.WHITE);
        supprimeButton.setBackground(Color.RED);
        supprimeButton.setForeground(Color.WHITE);
        modifButton.setBackground(Color.GREEN);
        modifButton.setForeground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajoutButton);
        buttonPanel.add(supprimeButton);
        buttonPanel.add(modifButton);
        
        ajoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Créer et afficher l'interface AddFournisseurForm
                AddFournisseurForm addFournisseurForm = new AddFournisseurForm();
                addFournisseurForm.setVisible(true);
                //refresh the table
                addFournisseurForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        actualiserTable();
                    }
                });
            }
            
            
        }); 
        
        modifButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int fournisseurId = (int) table.getValueAt(selectedRow, 0);
                    Fournisseur fournisseur = fournisseurController.getFournisseurById(fournisseurId);
                    ModifierFournisseurForm modifierFournisseurForm = new ModifierFournisseurForm(fournisseur);
                    modifierFournisseurForm.setVisible(true);
                    // refresh the table
                    modifierFournisseurForm.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            actualiserTable();
                        }
                    });
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fournisseur à modifier.");
                }
            }
        });
        supprimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int fournisseurId = (int) table.getValueAt(selectedRow, 0);
                    Fournisseur fournisseur = fournisseurController.getFournisseurById(fournisseurId);
                    int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce fournisseur ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        try {
                            boolean hasAssociatedProduit = fournisseurController.hasAssociatedProduit(fournisseurId);

                            if (hasAssociatedProduit) {
                                JOptionPane.showMessageDialog(null, "On ne peut pas supprimer un fournisseur qui est attaché à un produit.");
                            } else {
                                fournisseurController.supprimerFournisseur(fournisseurId);
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                model.removeRow(selectedRow);
                                actualiserTable();
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace(); // or handle the exception as per your application's needs
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fournisseur à supprimer.");
                }
            }
        });


        // Ajout d'un écouteur de clic sur une ligne de la table pour activer le bouton Modifier
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                modifButton.setEnabled(true); // Activer le bouton Modifier au clic sur une ligne
            }
        });


        // Ajout des composants à la fenêtre
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Réglage de la taille et de la visibilité de la fenêtre
        setSize(600, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void actualiserTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Effacer les données existantes de la table

        List<Fournisseur> fournisseurs = fournisseurController.getAllFournisseurs();
        for (Fournisseur fournisseur : fournisseurs) {
            Object[] row = {
                fournisseur.getFournisseurId(),
                fournisseur.getCin(),
                fournisseur.getfName(),
                fournisseur.getlName(),
                fournisseur.getTel(),
                fournisseur.getEmail(),
                fournisseur.getAdr()
            };
            model.addRow(row); // Ajouter les nouvelles données à la table
        }
    }

    // Méthodes pour gérer les actions des boutons et autres fonctionnalités...

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FournisseurInterface::new);
    }
}
