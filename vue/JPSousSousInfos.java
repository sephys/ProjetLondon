package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *  Ce conteneur contient les éléments graphiques permettant d'afficher les jetons
 *  (points de victoire, de pauvreté, ...) et en dessous le nombre associé à ces
 *  jetons.
 * @author Anh-Djuy
 */
public class JPSousSousInfos extends JPanel{
    
    JLabel haut;
    JLabel bas;
    int nombre;
    
    public JPSousSousInfos(String imagePath, int nombre){
        
        this.nombre = nombre;
        this.setLayout(new BorderLayout());
        this.setOpaque(false); // transparence
        URL uri = JBCarte.class.getResource(imagePath);
        bas = new JLabel();
        bas.setText(""+nombre);
        bas.setFont(bas.getFont ().deriveFont (14.0f));
        try {
            Image image = ImageIO.read(uri);
            haut = new JLabel(new ImageIcon(image));
            haut.setHorizontalAlignment(JLabel.CENTER);
            bas.setHorizontalAlignment(JLabel.CENTER);
            this.add(haut, BorderLayout.NORTH);
            this.add(bas, BorderLayout.CENTER);            
        } catch (IOException ex) {
            Logger.getLogger(JBCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        haut.setPreferredSize(new Dimension(60,50));
        this.setBackground(Color.LIGHT_GRAY);
    }
    
    
    
    public void ajoutNombre(int nombre){
        this.nombre += nombre;
        bas.setText(String.valueOf(nombre));
    }
    
    public void setNombre(int nombre){
        this.nombre = nombre;
        bas.setText(String.valueOf(nombre));
    }
}
