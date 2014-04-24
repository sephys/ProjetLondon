/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controleur;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import vue.*;

/**
 *
 * @author Joke
 */
public class MenuDroiteControl {
    JPnomGaucheImageDroiteControl control;
    
    public MenuDroiteControl()
    {
        super();
        control= new JPnomGaucheImageDroiteControl();
    }
    
    public void disableAll() {
        Main.getJeu().getMenudroite().getJouer().setEnabled(false);
        Main.getJeu().getMenudroite().getRestaurer().setEnabled(false);
        Main.getJeu().getMenudroite().getInvestir().setEnabled(false);
        Main.getJeu().getMenudroite().getPiocher3().setEnabled(false);
        Main.getJeu().getMenudroite().getPiocher().setEnabled(false);
        Main.getJeu().getMenudroite().getFinTour().setEnabled(false);
    }

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
    
    // methode qui permet de changer de joueur
    public void actualiserMain() {

        //passer chantier.carte2 à false
        for (int i = 0; i < Main.getJeu().getJpChantier().getChantiers().length; i++) {
            JPPileChantier chantier = Main.getJeu().getJpChantier().getChantiers()[i];
            chantier.setCarte2(false);
        }

        // sauvegarde de la main dans le tableau
        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()] = (JPMain) Main.getJeu().getSouth();

        // sauvegarde zone de construction
        Main.getJeu().getTabJPChantiers()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()] = Main.getJeu().getJpChantier();

        // on enleve la main
        Main.getJeu().getCentral().remove(Main.getJeu().getSouth());
        // on passe au joueur suivant
        Main.getJeu().setListeJoueur(Main.getJeu().getListeJoueur().getSuivant());
        // change le label nomJoeuur
        //labelJoueur.setText(London.getListeJoueur().getJoueur().getNom());
        control.actualiseJoueur();
        // on remplace le panel par celui du nouveau joueur
        Main.getJeu().setSouth(Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()]);
        // on ajoute le panel
        Main.getJeu().getCentral().add(Main.getJeu().getSouth(), BorderLayout.SOUTH);

        // on enleve la zone
        Main.getJeu().getPanelOnglet().remove(Main.getJeu().getPanelOnglet().getComponent(2));
        // on remplace la zone
        Main.getJeu().setJpChantier(Main.getJeu().getTabJPChantiers()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()]);
        // on ajoute la zone
        Main.getJeu().getPanelOnglet().addTab("Chantiers", Main.getJeu().getJpChantier());

        // actualiser la fenêtre
        Main.getJeu().getFrame().repaint();
        Main.getJeu().getCentral().revalidate();

        // le jouuer peut piocher une carte
        Main.getJeu().getListeJoueur().getJoueur().setPioche(1);

        // on réinitialise les valeurs
        Main.getJeu().getListeJoueur().getJoueur().setFinitTour(false);
        JBCarte.setClicDroitJouer(false);
        JBCarte.setActiverCarte(false);

        //London.getListeJoueur().getJoueur().setFinTourPiocheCarte(false);
        // on informe le joueur
        JOptionPane.showMessageDialog(null, "C'est au tour de " + Main.getJeu().getListeJoueur().getJoueur().getNom() + " de jouer");
        //London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");

        // on peut pas se defausser a la base
        //setDefausseCarte(false);
        // change onglet
        Main.getJeu().getPanelOnglet().setSelectedIndex(1);

        // on peut pas d&d à la base
        DragDropControl.setDragEnable(false);

    }
    
}
