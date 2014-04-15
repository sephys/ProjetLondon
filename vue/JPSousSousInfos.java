/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author Anh-Djuy
 */
public class JPSousSousInfos extends JPanel{
    
    JLabel haut;
    JLabel bas;
    int nombre;
    
    public JPSousSousInfos(String imagePath, int nombre){
       // this.setPreferredSize(new Dimension(50,50));
        
        this.nombre = nombre;
        this.setLayout(new BorderLayout());
        this.setOpaque(false); // transparance
        URL uri = JBCarte.class.getResource(imagePath);
        bas = new JLabel();
        bas.setText(""+nombre);
        bas.setFont(bas.getFont ().deriveFont (14.0f));
        try {
            Image image = ImageIO.read(uri);
            haut = new JLabel(new ImageIcon(image));
            haut.setHorizontalAlignment(JLabel.CENTER);
            bas.setHorizontalAlignment(JLabel.CENTER);
            //  this.centrerTexte();
            this.add(haut, BorderLayout.NORTH);
            this.add(bas, BorderLayout.CENTER);            
        } catch (IOException ex) {
            Logger.getLogger(JBCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        haut.setPreferredSize(new Dimension(60,50));
        this.setBackground(Color.LIGHT_GRAY);
    }
    
    /**public void centrerTexte(){
        StyledDocument doc = bas.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }*/
    
    public void ajoutNombre(int nombre){
        this.nombre += nombre;
        bas.setText(String.valueOf(nombre));
        //bas.setText(new String(new StringBuilder(nombre)));
    }
    
    public void setNombre(int nombre){
        this.nombre = nombre;
        bas.setText(String.valueOf(nombre));
        //bas.setText(new String(new StringBuilder(nombre)));
    }
}
