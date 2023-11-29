package vue;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MenuInterface extends JFrame {
    private JMenuBar menuBar;

    public MenuInterface() {
        menuBar = new JMenuBar();

        // Redimensionner l'icône pour le menu 'Fournisseur'
        ImageIcon icon = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//homme.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        JMenu fournisseurMenu = new JMenu("Fournisseur");
        fournisseurMenu.setIcon(icon);

        // Redimensionner l'icône pour le menu 'Facture'
        ImageIcon iconf = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//facturer.png");
        Image imgf = iconf.getImage();
        Image newImgf = imgf.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconf = new ImageIcon(newImgf);
        JMenu factureMenu = new JMenu("Facture");
        factureMenu.setIcon(iconf);

        // Redimensionner l'icône pour le menu 'Produit'
        ImageIcon iconp = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//boite-de-livraison.png");
        Image imgp = iconp.getImage();
        Image newImgp = imgp.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconp = new ImageIcon(newImgp);
        JMenu produitMenu = new JMenu("Produit");
        produitMenu.setIcon(iconp);

        // Redimensionner l'icône pour le menu 'Profil'
        ImageIcon iconpr = new ImageIcon("C://Users//eki//Downloads//Icon//Icon//pere.png");
        Image imgpr = iconpr.getImage();
        Image newImgpr = imgpr.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        iconpr = new ImageIcon(newImgpr);
        JMenu profilMenu = new JMenu("Profil");
        profilMenu.setIcon(iconpr);

        menuBar.add(fournisseurMenu);
        menuBar.add(factureMenu);
        menuBar.add(produitMenu);
        menuBar.add(profilMenu);
        
        fournisseurMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                FournisseurInterface fournisseurInterface = new FournisseurInterface();
                fournisseurInterface.setVisible(true);
            }
        });
        produitMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                ProduitInterface produitInterface = new ProduitInterface();
                produitInterface.setVisible(true);
            }
        });

        
        setJMenuBar(menuBar);
        setTitle("Menu");
        setSize(600, 500); // Redimensionner la barre de menu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuInterface::new);
    }
}
