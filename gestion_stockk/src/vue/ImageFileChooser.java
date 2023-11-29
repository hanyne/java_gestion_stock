package vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageFileChooser {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton chooseImageButton = new JButton("Choisir une image");

        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home")); // Ouvre le répertoire de l'utilisateur
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    String imagePath = selectedFile.getAbsolutePath();
                    System.out.println("Chemin de l'image : " + imagePath);

                    // Ici, vous pouvez utiliser imagePath comme chemin de l'image sélectionnée
                    // Par exemple, vous pouvez l'utiliser pour créer un Produit avec l'image correspondante
                    // Par exemple : Produit produit = new Produit(..., imagePath, ...);

                    // Mettez à jour l'interface utilisateur si nécessaire
                }
            }
        });

        frame.add(chooseImageButton);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
