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
import model.Zone;

/**
 *
 * @author Joke
 * Boutton disponible sur le plateau pour chaque zone.
 */
public class JBZone extends JButton implements ActionListener{
    
    private Zone zone; // nom du bouton et de la zone
    
    public JBZone(Zone zone){
        this.setMargin(new Insets(0, 0, 0, 0)); //A revoir, pour diminuer la taille des marges
        Font f = this.getFont();
        f = new Font(Font.DIALOG, Font.BOLD, 9);
        this.setFont(f);
        this.zone=zone;
        String nomZone = zone.getNom();
        this.setText(split(nomZone));
    }
    
    public Zone getZone() {
        return this.zone;
    }
    
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("T'as tapé sur " + this.zone.getNom());
    }
    
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
