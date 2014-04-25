/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Joueur;
import vue.JBZone;
import vue.Main;

/**
 *  Ce Listener permet de gérer tous les boutons des zones du jeu.
 *  Si le joueur ne possède pas assez d'argent, il ne peut investir et
 *  doit faire un emprunt.
 * @author Joke
 */
public class JBZoneControl implements ActionListener {
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JBZone bouton= (JBZone) e.getSource();
        
        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),             // Affichage de la fenêtre de confirmation de l'action d'investir
                "Voulez-vous investir dans " + bouton.getZone().getNom()+ " ?",
                "Investir",
                JOptionPane.YES_NO_OPTION);
        if (rep == JOptionPane.YES_OPTION){                             // Cas ou le joueur accepte d'investir
            Joueur courrant = Main.getJeu().getListeJoueur().getJoueur();
            if(bouton.getZone().getPrix() > courrant.getArgent()){
                // Dans le cas ou son argent est insuffisant, il lui est proposé un emprunt (fenêtre de confirmation)
                JOptionPane.showMessageDialog(Main.getJeu().getFrame(), "Vous n'avez pas assez d'argent ! Empruntez ou choisissez une autre zone.");
            }else{
                // S'il dispose d'assez d'argent, la zone est directement investie
                bouton.getZone().investir(courrant);
                bouton.setBackground(Main.getJeu().getListeJoueur().getJoueur().getColor());
                JOptionPane.showMessageDialog(Main.getJeu().getFrame(), "Investissement réussi.");    // Une fenêtre affiche que l'investissement s'est bien déroulé
                // Les actions d'investissement de la zone sont ajoutée au joueur courrant (nombre de cartes à piocher, coût décrémenté à son argent, points de victoire ajoutés)
                courrant.addArgent(-bouton.getZone().getPrix());
                courrant.setPioche(bouton.getZone().getNbCartes());
                courrant.addPointVictoire(bouton.getZone().getPointsVictoire());
                // London.getListeJoueur().getJoueur().setFinTourPiocheCarte(true);
                Main.getJeu().getMenudroite().getPiocher().setEnabled(true);
                Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true);
                
                // L'action de piocher des cartes lui est affichée dans le JLabel dans le MenuDroit
                Main.getJeu().getMenudroite().getLabelInfo().setText("Vous devez piocher "+ bouton.getZone().getNbCartes() + " cartes");
                
                Main.getJeu().getPlateau().desactiveZones();   // Désactivation de tous les boutons de zone pour le prochain joueur
            }
        }else{
            // S'il refuse l'action d'investir, il peut donc rechoisir l'action à effectuer
            JOptionPane.showMessageDialog(Main.getJeu().getFrame(), "Investissement annulé.");    // Une fenêtre affichant l'échec de l'action Investir est affichée
        }
        
        Main.getJeu().getInfos().maj_infos();   // Actualisation du panneau de gauche
    }
    
    
}
