package vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controlleur.ProduitController;
import models.Fournisseur;
import models.Produit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class ProduitInterface extends JFrame {
    private JTable table;
    private JButton ajoutButton, supprimeButton, modifButton;
    private ProduitController produitController;

    public ProduitInterface() {
    	produitController = new ProduitController(); // Instanciation du contrôleur

        // Initialisation de la fenêtre
        setTitle("Interface Produit");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Obtention des données des produits depuis la base de données
        List<Produit> produits = produitController.getAllProduits();
        String[] columnNames = {"ID", "Image", "Nom", "Description", "Prix", "Quantité", "Status", "Fournisseur"};
        Object[][] data = new Object[produits.size()][8];

        // Remplir les données pour le tableau
        for (int i = 0; i < produits.size(); i++) {
            Produit produit = produits.get(i);
            data[i][0] = produit.getProduitId();
            data[i][1] = produit.getImageP();
            data[i][2] = produit.getNameP();
            data[i][3] = produit.getDescription();
            data[i][4] = produit.getPrice();
            data[i][5] = produit.getQte();
            data[i][6] = produit.isStatus();
            data[i][7] = produit.getFournisseurName();
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
        
        
        class ImageRenderer extends DefaultTableCellRenderer {
            private JLabel label;

            public ImageRenderer() {
                label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value != null) {
                    ImageIcon icon = new ImageIcon((String) value);
                    Image image = icon.getImage().getScaledInstance(50, -1, Image.SCALE_SMOOTH); // Redimensionner l'image
                    label.setIcon(new ImageIcon(image));
                    label.setText(null);
                } else {
                    label.setIcon(null);
                    label.setText("No Image");
                }
                return label;
            }
        }

        
        TableColumn imageColumn = table.getColumnModel().getColumn(1);
        imageColumn.setCellRenderer(new ImageRenderer());
        // Ajout du tableau à un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        table.setIntercellSpacing(new Dimension(10, 10));

        // Création des boutons
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
        
        // Ajout des boutons à un JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(ajoutButton);
        buttonPanel.add(supprimeButton);
        buttonPanel.add(modifButton);
        
      
        ajoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Créer et afficher l'interface AddProduitForm
                AddProduitForm addProduitForm = new AddProduitForm();
                addProduitForm.setVisible(true);
              //refresh the table
                addProduitForm.addWindowListener(new WindowAdapter() {
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
                    int produitId = (int) table.getValueAt(selectedRow, 0);
                    Produit produit = produitController.getProduitById(produitId);
                    ModifierProduitForm modifierProduitForm = new ModifierProduitForm(produit);
                    modifierProduitForm.setVisible(true);
                    // refresh the table
                    modifierProduitForm.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            actualiserTable();
                        }
                    });
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un produit à modifier.");
                }
            }
        });
        supprimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int produitId = (int) table.getValueAt(selectedRow, 0);
                    Produit produit = produitController.getProduitById(produitId);
                    int confirmation = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce produit ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmation == JOptionPane.YES_OPTION) {
                        produitController.supprimerProduit(produitId);
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "Le produit a été supprimé avec succès.");
                        produitController.supprimerProduit(produitId);
                        actualiserTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner un produit à supprimer.");
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
        setSize(900, 400);
        setLocationRelativeTo(null); // Pour afficher la fenêtre au centre de l'écran
        setVisible(true);
    }
    private void actualiserTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing data in the table

        List<Produit> produits = produitController.getAllProduits();
        for (Produit produit : produits) {
            Object[] row = {
                produit.getProduitId(),
                produit.getImageP(),
                produit.getNameP(),
                produit.getDescription(),
                produit.getPrice(),
                produit.getQte(),
                produit.isStatus(),
                produit.getFournisseurName() // Adding supplier's name to the row data
            };
            model.addRow(row); // Add new data to the table
        }
    }






    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProduitInterface::new);
    }
}