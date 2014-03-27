/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Anh-Djuy
 * Bouton représentant une carte
 */
public class JBCarte extends JButton implements ActionListener{

    
    private Image image;

    public JBCarte(Image image) {
        this.image = image;
        this.setIcon(new ImageIcon(image));
    }
    
        
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
