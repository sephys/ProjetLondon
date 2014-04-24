/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;

import controleur.JBZoneControl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Joueur;
import model.Zone;

/**
 *
 * @author Joke
 * Boutton disponible sur le plateau pour chaque zone.
 */
public class JBZone extends JButton {
    
    private Zone zone; // Zone contenue dans le bouton.
    
    /**
     * Le constructeur de JBZone prend en paramètre la zone associée.
     * @param zone
     */
    public JBZone(Zone zone){
        this.setMargin(new Insets(0, 0, 0, 0));
        Font f = this.getFont();
        f = new Font(Font.DIALOG, Font.BOLD, 9);
        this.setFont(f);
        this.zone=zone;
        this.setText(split(zone.getNom()));
        this.addActionListener(new JBZoneControl());
    }
    
    public Zone getZone() {
        return this.zone;
    }
    
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
    /**
     * actionPerformed gère le comportement de l'appui sur le bouton.
     * 2 principaux cas sont gérés :
     *  Le joueur voulant investir sur la zone possède l'argent nécessaire et investit donc la zone.
     *  Le joueur voulant investir sur la zone ne possède pas l'argent nécessaire, il lui est alors
     *  proposé de faire un emprunt.
     *
     * A chaque opération, une fenêtre de confirmation apparaît pour que le joueur ne puisse pas se
     * tromper.
     * @param e
     */
    
    /**
     * Cette méthode permet au nom de la zone d'être centré dans son bouton, en utilisant du HTML.
     * Il a été défini que la taille maximum approximative de la chaîne de caractère possible
     * que l'on pouvait mettre sur une ligne sur un Bouton de la taille que l'on a définie
     * était de 14. La méthode place en plus les sauts de ligne où il le faut afin que l'espace
     * dans le bouton soit le mieux utilisé.
     * @param nomZone
     * @return
     */
    private String split(String nomZone) {
        // taille max de 14
        StringBuilder retour = new StringBuilder("<html><center>");
        String[] tab = nomZone.split(" ");
        StringBuilder aAjouter = new StringBuilder("");
        for(int i = 0; i < tab.length; i++){
            if(aAjouter.length() <= 14){
                aAjouter.append(" ");
                aAjouter.append(tab[i]);
            }else{
                aAjouter.append("<br>");
                retour.append(aAjouter);
                aAjouter = new StringBuilder(tab[i]);
            }
        }
        retour.append(aAjouter);
        retour.append("</center></html>");
        return new String(retour);
    }
}
