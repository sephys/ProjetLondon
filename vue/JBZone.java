/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Joke
 * Boutton disponible sur le plateau pour chaque zones.
 */
public class JBZone extends JButton implements ActionListener{
    
    private String nom; // nom du bouton et de la zone
    
    public JBZone(String nom){
        this.nom=nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame jfzone=new JFrame();
        jfzone.setTitle(getNom());
        
    }
    
}
