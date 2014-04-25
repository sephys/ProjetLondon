/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package controleur;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import vue.JPAccueil;

/**
 *  Ce listener permet, à l'appui du bouton "Regles" dans la fenêtre d'accueil
 *  d'afficher le fichier PDF des règles.
 * @author Anh-Djuy
 */
public class AfficheRegles implements ActionListener{
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // fichier règles
        URL uri = JPAccueil.class.getResource("../fichier/regle.pdf");
        try {
            Desktop.getDesktop().open(new File(uri.getPath()));
        } catch (IOException ex) {
            Logger.getLogger(JPAccueil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
