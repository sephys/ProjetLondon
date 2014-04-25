/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import model.Joueur;
import vue.*;

/**
 *  Cette classe permet de gérer tous les boutons du menu de droite ainsi
 *  que de changer de tour.
 * @author Joke
 */
public class MenuDroiteControl {
    
    // Panel haut du menu droit.
    JPnomGaucheImageDroiteControl control;
    
    public MenuDroiteControl(){
        super();
        control= new JPnomGaucheImageDroiteControl();
    }
    
    /**
     *  Méthode de désactivation de tous les boutons du menu droit.
     */
    public void disableAll() {
        Main.getJeu().getMenudroite().getJouer().setEnabled(false);
        Main.getJeu().getMenudroite().getRestaurer().setEnabled(false);
        Main.getJeu().getMenudroite().getInvestir().setEnabled(false);
        Main.getJeu().getMenudroite().getPiocher3().setEnabled(false);
        Main.getJeu().getMenudroite().getPiocher().setEnabled(false);
        Main.getJeu().getMenudroite().getFinTour().setEnabled(false);
    }

    /**
     * Méthode d'activation de tous les boutons du menu droit.
     */
    public void enableAll() {
        Main.getJeu().getMenudroite().getJouer().setEnabled(true);
        Main.getJeu().getMenudroite().getRestaurer().setEnabled(true);
        Main.getJeu().getMenudroite().getInvestir().setEnabled(true);
        Main.getJeu().getMenudroite().getPiocher3().setEnabled(true);
        Main.getJeu().getMenudroite().getPiocher().setEnabled(true);
        Main.getJeu().getMenudroite().getFinTour().setEnabled(true);
    }
    
    public void setFinTour() {
        disableAll();
        Main.getJeu().getMenudroite().getFinTour().setEnabled(true);
        Main.getJeu().getMenudroite().getLabelInfo().setText("Vous avez finit votre tour");
    }
    
    /**
     *  Cette méthode permet de changer de joueur avec tous les traitements
     *  que cela implique.
     */
    public void actualiserMain() {

        //  Passage de chantier.carte2 à false
        for (int i = 0; i < Main.getJeu().getJpChantier().getChantiers().length; i++) {
            JPPileChantier chantier = Main.getJeu().getJpChantier().getChantiers()[i];
            chantier.setCarte2(false);
        }

        // Sauvegarde de la main dans le tableau
        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()] = (JPMain) Main.getJeu().getSouth();

        // Sauvegarde de la zone de construction
        Main.getJeu().getTabJPChantiers()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()] = Main.getJeu().getJpChantier();

        // On enlève la main
        Main.getJeu().getCentral().remove(Main.getJeu().getSouth());
        // On passe au joueur suivant
        Main.getJeu().setListeJoueur(Main.getJeu().getListeJoueur().getSuivant());
        Main.getJeu().getListeJoueur().getJoueur().setPioche(1);
        Joueur nouveauJoueur = Main.getJeu().getListeJoueur().getJoueur();
        // On vérifie s'il a le pouvoir University of London 
        if(nouveauJoueur.getPouvoir().get("University") == 1){
            Main.getJeu().getMenudroite().getRegarder3Cartes().setVisible(true);
        }else{
            Main.getJeu().getMenudroite().getRegarder3Cartes().setVisible(false);            
        }
        // Changement de l'image ainsi que du pseudo du joueur dans la partie haute
        control.actualiseJoueur();
        // On prend met la main du joueur suivant
        Main.getJeu().setSouth(Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()]);
        // On ajoute le panel
        Main.getJeu().getCentral().add(Main.getJeu().getSouth(), BorderLayout.SOUTH);

        // On enleve les chantiers du précédent joueur
        Main.getJeu().getPanelOnglet().remove(Main.getJeu().getPanelOnglet().getComponent(2));
        // On les remplace par les chantiers du nouveau joueur
        Main.getJeu().setJpChantier(Main.getJeu().getTabJPChantiers()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()]);
        // On ajoute la zone
        Main.getJeu().getPanelOnglet().addTab("Chantiers", Main.getJeu().getJpChantier());

        // Actualisation de la fenêtre
        Main.getJeu().getFrame().repaint();
        Main.getJeu().getCentral().revalidate();

        // On permet au joueur de piocher une carte
        Main.getJeu().getListeJoueur().getJoueur().setPioche(1);

        // On réinitialise les valeurs
        Main.getJeu().getListeJoueur().getJoueur().setFinitTour(false);
        JBCarte.setClicDroitJouer(false);
        JBCarte.setActiverCarte(false);

        // On informe le joueur de son tour
        JOptionPane.showMessageDialog(null, "C'est au tour de " + Main.getJeu().getListeJoueur().getJoueur().getNom() + " de jouer");
        
        // Changement d'onglet pour la carte du plateau
        Main.getJeu().getPanelOnglet().setSelectedIndex(1);

        // Désactivation du Drag & Drop
        DragDropControl.setDragEnable(false);
        if(Main.getJeu().getListeJoueur().getFinTour()==0){
            this.disableAll();
            Main.getJeu().getMenudroite().getLabelInfo().setText("La partie est finie");
            Main.getJeu().getListeJoueur().finJeu();
        }
    }
}
