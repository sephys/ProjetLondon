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
import model.Joueur;
import model.Zone;

/**
 *
 * @author Joke
 * Boutton disponible sur le plateau pour chaque zone.
 */
public class JBZone extends JButton implements ActionListener{
    
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
        this.addActionListener(this);
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
    public void actionPerformed(ActionEvent e) {
        if(MenuDroite.invest){
            int rep = JOptionPane.showConfirmDialog(London.acc,             // Affichage de la fenêtre de confirmation de l'action d'investir
                    "Voulez-vous investir dans " + this.zone.getNom()+ " ?",
                    "Investir",
                    JOptionPane.YES_NO_OPTION);
            if (rep == JOptionPane.YES_OPTION){                             // Cas ou le joueur accepte d'investir
                Joueur courrant = London.getListeJoueur().getJoueur();
                if(zone.getPrix() > courrant.getArgent()){
                    // Dans le cas ou son argent est insuffisant, il lui est proposé un emprunt (fenêtre de confirmation)
                    JOptionPane.showMessageDialog(London.acc, "Vous n'avez pas assez d'argent ! Empruntez ou choisissez une autre zone.");
                }else{
                    // S'il dispose d'assez d'argent, la zone est directement investie
                    this.zone.investir(courrant);
                    this.setBackground(London.getListeJoueur().getJoueur().getColor());
                    JOptionPane.showMessageDialog(London.acc, "Investissement réussi.");    // Une fenêtre affiche que l'investissement s'est bien déroulé
                    // Les actions d'investissement de la zone sont ajoutée au joueur courrant (nombre de cartes à piocher, coût décrémenté à son argent, points de victoire ajoutés)
                    courrant.addArgent(-this.zone.getPrix());
                    courrant.setPioche(this.zone.getNbCartes());
                    courrant.addPointVictoire(this.zone.getPointsVictoire());
                   // London.getListeJoueur().getJoueur().setFinTourPiocheCarte(true);
                    London.getMenudroite().getPiocher().setEnabled(true);
                    London.getMenudroite().getFinTour().setEnabled(true);
                    // L'action de piocher des cartes lui est affichée dans le JLabel dans le MenuDroit
                    London.getMenudroite().getLabelInfo().setText("Vous devez piocher "+ this.zone.getNbCartes() + " cartes");
                    
                    London.getPlateau().desactiveZones();   // Désactivation de tous les boutons de zone pour le prochain joueur
                }
            }else{
                // S'il refuse l'action d'investir, il peut donc rechoisir l'action à effectuer
                JOptionPane.showMessageDialog(London.acc, "Investissement annulé.");    // Une fenêtre affichant l'échec de l'action Investir est affichée
            }
        }
        London.infos.maj_infos();   // Actualisation du panneau de gauche
    }
    
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
