/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import model.Constructible;
import model.Joueur;
import model.NonConstructible;
import model.PouvoirBeta;
import vue.JBCarte;
import vue.JPZoom;
import vue.London;

import vue.Main;

/**
 *
 * @author Anh-Djuy
 */
public class JBCarteControl implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {

            Joueur courrant = Main.getJeu().getListeJoueur().getJoueur();

            switch (((JBCarte) e.getComponent()).getPosition()) {
                case "main": // on met la carte de la main sur l'étalage
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("defausse")&&((JBCarte) e.getComponent()).isDefausse())
                    // cas ou defausse car trop de cartes dans la main

                    if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() != 0 && Main.getJeu().getListeJoueur().getJoueur().getLastCarte() == null) {

                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());
                        carte.setPosition("etalage");
                        // ajout de la carte dans l'etalage

                        Main.getJeu().getEtalage().addCarte(carte.getCarte());
                        // on rafrachit l'etalage
                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                        // on enleve la carte de la main du joueur ( graphiquement )
                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());

                        //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                        // suppression de la carte de la main du joueur
                        Main.getJeu().getListeJoueur().getJoueur().getMain().remove(carte.getCarte());

                        //on enlève la défausse
                        Main.getJeu().getListeJoueur().getJoueur().defausseMoins(carte.getCarte());

                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        //London.getListeJoueur().getJoueur().payeConstruction(carte.carte);
                    } // cas defausse à cause d'une construction
                    else if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() != 0) {

                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());

                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        // si carte ne respecte pas les contraintes de defausse
                        if (Main.getJeu().getListeJoueur().getJoueur().payeConstruction(carte.getCarte()) == false) {
                            if (Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("School") >= 1) {
                                int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                        "[Pouvoir school] Dépensez 1$ pour défausser une carte de couleur différente",
                                        "Pouvoir school",
                                        JOptionPane.YES_NO_OPTION);
                                if (rep == JOptionPane.YES_OPTION) {
                                    if (Main.getJeu().getListeJoueur().getJoueur().getArgent() >= 1) {
                                        PouvoirBeta.pouvoirSchool(Main.getJeu().getListeJoueur().getJoueur(), carte.getCarte());
                                        /*La carte va dans l'étalage*/
                                        carte.setPosition("etalage");
                                        // ajout de la carte dans l'etalage
                                        Main.getJeu().getEtalage().addCarte(carte.getCarte());
                                        // on rafrachit l'etalage
                                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                                        // on enleve la carte de la main du joueur ( graphiquement )
                                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());

                                        //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                                        // refresh
                                        Main.getJeu().getSouth().repaint();
                                        Main.getJeu().getSouth().revalidate();
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent");
                                    }

                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                            }
                        } else {
                            /*La carte va dans l'étalage*/
                            carte.setPosition("etalage");
                            // ajout de la carte dans l'etalage
                            Main.getJeu().getEtalage().addCarte(carte.getCarte());
                            // on rafrachit l'etalage
                            Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                            // on enleve la carte de la main du joueur ( graphiquement )
                            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());

                            //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                            // refresh
                            Main.getJeu().getSouth().repaint();
                            Main.getJeu().getSouth().revalidate();

                        }
                    } else {
                        System.out.println("JBCARTE defausse = 0");
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;

                case "etalage": // on met la carte de l'etalage dans la main OU chantier
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    JBCarte carte = ((JBCarte) e.getComponent());

                    if (Main.getJeu().getListeJoueur().getJoueur().getPioche() != 0) {
                        // ajout de la carte dans la main du joueur graphiquement
                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(carte.getCarte());
                        // ajout de la carte das la main du joueur
                        Main.getJeu().getListeJoueur().getJoueur().getMain().add(carte.getCarte());
                        // suppresssion de la carte dans l'etalage
                        Main.getJeu().getEtalage().piocherCarte(carte.getCarte());
                        // on rafraichit l'étalage
                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                        Main.getJeu().getListeJoueur().getJoueur().piocheMoins();

                        ((JBCarte) e.getComponent()).setPosition("main");

                        /*Pouvoir Coffee Shop : ajout de la carte de l'étalage sur un chantier*/
                    } else if (Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("Coffee") >= 1) {

                        if (carte.getCarte().getClass() == Constructible.class) {
                            /*Ajout visuel de la carte*/
                            int i = 0;
                            boolean pose = false; /*Boolean qui devient vrai si la carte de l'étalage est posée*/

                            while (i < Main.getJeu().getListeJoueur().getJoueur().getListeChantier().size() && !pose) {
                                if (Main.getJeu().getListeJoueur().getJoueur().getListeChantier().get(i).peekFirst().getNom().equals("Coffee House")) {
                                    Main.getJeu().getJpChantier().getChantiers()[i].removeAll();
                                    Main.getJeu().getJpChantier().getChantiers()[i].ajoutCarte(carte.getCarte());
                                }
                                i++;
                            }

                            /*Ajout dans le modèle de la carte*/
                            Main.getJeu().getListeJoueur().getJoueur().getMain().add(carte.getCarte());

                            // suppresssion de la carte dans l'etalage
                            Main.getJeu().getEtalage().piocherCarte(carte.getCarte());

                            /*Rafraichissement de l'étalage*/
                            Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());

                            /*Changer la position de la carte*/
                            carte.setPosition("construction");

                            // change onglet sur le chantier
                            Main.getJeu().getPanelOnglet().setSelectedIndex(2);

                            Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Cette carte n'est pas constructible");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas prendre de cette carte");
                    }
                    break;

                case "fenetre": // on met la carte de la fenetre dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if (JBCarte.getDoubleClick()) {

                        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                "Êtes-vous sûr de vouloir choisir \"" + ((JBCarte) e.getComponent()).getCarte().getNom() + "\" ?",
                                "Piocher",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {

                            Main.getJeu().getMenudroite().getF().dispose();
                            Main.getJeu().getMenudroite().getF().remetCartes(((JBCarte) e.getComponent()));
                            // ajout de la carte dans la main du joueur graphiquement
                            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).getCarte());
                            // ajout de la carte das la main du joueur
                            Main.getJeu().getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).getCarte());
                            Main.getJeu().getListeJoueur().getJoueur().piocheMoins();

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;

                case "construction": // on active une carte qui est dans la zone de construction
                    if (JBCarte.isActiverCarte()) {

                        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                "Êtes-vous sûr de vouloir activer cette carte ?",
                                "Activer la carte",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {

                            Main.getJeu().getListeJoueur().getJoueur().activerCarte(((Constructible) ((JBCarte) e.getComponent()).getCarte()));

                            // on check si la carte doit être retourné
                            if (((Constructible) ((JBCarte) e.getComponent()).getCarte()).isARetourne()) {
                                ((JBCarte) e.getComponent()).changerImage("../img/cartes/Background.png");

                                Main.getJeu().getInfos().maj_infos();
                                Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true);

                            }

                        }
                        break;

                    }
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            if (JBCarte.isClicDroitJouer()) {
                if (((JBCarte) e.getComponent()).getCarte().getClass() == NonConstructible.class) {
                    if (!"Paupers".equals(((JBCarte) e.getComponent()).getCarte().getNom())) {

                        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                "Êtes-vous sûr de vouloir jouer cette carte ?",
                                "Jouer carte",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
                            System.out.println(((JBCarte) e.getComponent()).getCarte().getNom());

                            Main.getJeu().getListeJoueur().getJoueur().jouerCarte2(((JBCarte) e.getComponent()).getCarte(), 0);
                            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());

                            /*Wren*/
                            if (Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("Wren") == 1) {
                                Main.getJeu().getMenudroite().getLabelInfo().setText("<html>[Pouvoir Wren] Posez deux cartes sur votre<br/>chantier sans devoir défausser des cartes</html>");
                            }

                            /*Hugueunot*/
                            if (Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("Huguenots") == 1) {
                                PouvoirBeta.pouvoirHuguenots(Main.getJeu().getListeJoueur().getJoueur());

                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Paupers ne peux pas être jouée");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cette carte est constructible'");
                }
            } else {

                if (Main.getJeu().getListeJoueur().getJoueur().getPioche() == 0) {

                    JOptionPane.showMessageDialog(null, "Vous devez choisir l'action 'Jouer des cartes'");
                } else {
                    JOptionPane.showMessageDialog(null, "Vous devez piocher");
                }
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JPZoom.setImg(((JBCarte) e.getComponent()).getCarte().getPath());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPZoom.setImg("../img/cartes/Background.png");

    }

}
