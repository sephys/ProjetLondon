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
        London.getMenudroite().getJouer().setEnabled(false);
        London.getMenudroite().getRestaurer().setEnabled(false);
        London.getMenudroite().getInvestir().setEnabled(false);
        London.getMenudroite().getPiocher3().setEnabled(false);
        London.getMenudroite().getPiocher().setEnabled(false);
        London.getMenudroite().getFinTour().setEnabled(false);
    }

    public void enableAll() {
         London.getMenudroite().getJouer().setEnabled(true);
        London.getMenudroite().getRestaurer().setEnabled(true);
        London.getMenudroite().getInvestir().setEnabled(true);
        London.getMenudroite().getPiocher3().setEnabled(true);
        London.getMenudroite().getPiocher().setEnabled(true);
        London.getMenudroite().getFinTour().setEnabled(true);
    }
    
    public void setFinTour() {
        disableAll();
        London.getMenudroite().getFinTour().setEnabled(true);
        London.getMenudroite().getLabelInfo().setText("Vous avez finit votre tour");
    }
    
    // methode qui permet de changer de joueur
    public void actualiserMain() {

        //passer chantier.carte2 à false
        for (int i = 0; i < London.getJpChantier().getChantiers().length; i++) {
            JPPileChantier chantier = London.getJpChantier().getChantiers()[i];
            chantier.setCarte2(false);
        }

        // sauvegarde de la main dans le tableau
        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()] = (JPMain) London.getSouth();

        // sauvegarde zone de construction
        London.getTabJPChantiers()[London.getListeJoueur().getJoueur().getPlaceJoueur()] = London.getJpChantier();

        // on enleve la main
        London.getCentral().remove(London.getSouth());
        // on passe au joueur suivant
        London.setListeJoueur(London.getListeJoueur().getSuivant());
        // change le label nomJoeuur
        //labelJoueur.setText(London.getListeJoueur().getJoueur().getNom());
        control.actualiseJoueur();
        // on remplace le panel par celui du nouveau joueur
        London.setSouth(London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()]);
        // on ajoute le panel
        London.getCentral().add(London.getSouth(), BorderLayout.SOUTH);

        // on enleve la zone
        London.getPanelOnglet().remove(London.getPanelOnglet().getComponent(2));
        // on remplace la zone
        London.setJpChantier(London.getTabJPChantiers()[London.getListeJoueur().getJoueur().getPlaceJoueur()]);
        // on ajoute la zone
        London.getPanelOnglet().addTab("Chantiers", London.getJpChantier());

        // actualiser la fenêtre
        London.getFrame().repaint();
        London.getCentral().revalidate();

        // le jouuer peut piocher une carte
        London.getListeJoueur().getJoueur().setPioche(1);

        // on réinitialise les valeurs
        London.getListeJoueur().getJoueur().setFinitTour(false);
        JBCarte.setClicDroitJouer(false);
        JBCarte.setActiverCarte(false);

        //London.getListeJoueur().getJoueur().setFinTourPiocheCarte(false);
        // on informe le joueur
        JOptionPane.showMessageDialog(null, "C'est au tour de " + London.getListeJoueur().getJoueur().getNom() + " de jouer");
        //London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");

        // on peut pas se defausser a la base
        //setDefausseCarte(false);
        // change onglet
        London.getPanelOnglet().setSelectedIndex(1);

        // on peut pas d&d à la base
        DragDrop.setDragEnable(false);

    }
    
}
