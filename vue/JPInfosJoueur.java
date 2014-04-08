/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author FT
 */
public class JPInfosJoueur extends JPanel{
    private JLabel nom;
    
    public JPInfosJoueur(String nomJoueur){
        //this.setBackground(Color.red);
        this.nom = new JLabel(nomJoueur);
        this.add(nom);
    }
}
