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
        this.zone=zone;
        this.setText(zone.getNom());
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
}
