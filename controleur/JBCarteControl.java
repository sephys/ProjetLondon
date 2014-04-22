/*
<<<<<<< HEAD
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

=======
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
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

<<<<<<< HEAD
=======
import vue.Main;

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
/**
 *
 * @author Anh-Djuy
 */
<<<<<<< HEAD
public class JBCarteControl implements MouseListener{
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2) {
            
            Joueur courrant = London.getListeJoueur().getJoueur();
=======
public class JBCarteControl implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) {

            Joueur courrant = Main.getJeu().getListeJoueur().getJoueur();

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
            switch (((JBCarte) e.getComponent()).getPosition()) {
                case "main": // on met la carte de la main sur le l'étalage
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("defausse")&&((JBCarte) e.getComponent()).isDefausse())
                    // cas ou defausse car trop de cartes dans la main
<<<<<<< HEAD
                    if (London.getListeJoueur().getJoueur().getDefausse() != 0 && London.getListeJoueur().getJoueur().getLastCarte() == null) {
                        
=======

                    if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() != 0 && Main.getJeu().getListeJoueur().getJoueur().getLastCarte() == null) {

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());
                        carte.setPosition("etalage");
                        // ajout de la carte dans l'etalage
<<<<<<< HEAD
                        London.getEtalage().addCarte(carte.getCarte());
                        // on rafrachit l'etalage
                        London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
                        // on enleve la carte de la main du joueur ( graphiquement )
                        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());
                        //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                        
                        // suppression de la carte de la main du joueur
                        
                        London.getListeJoueur().getJoueur().getMain().remove(carte.getCarte());
                        
                        
                        //on enlève la défausse
                        London.getListeJoueur().getJoueur().defausseMoins();
                        
=======

                        Main.getJeu().getEtalage().addCarte(carte.getCarte());
                        // on rafrachit l'etalage
                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                        // on enleve la carte de la main du joueur ( graphiquement )
                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());

                        //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                        // suppression de la carte de la main du joueur
                        Main.getJeu().getListeJoueur().getJoueur().getMain().remove(carte.getCarte());

                        //on enlève la défausse
                        Main.getJeu().getListeJoueur().getJoueur().defausseMoins();

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        //London.getListeJoueur().getJoueur().payeConstruction(carte.carte);
                    } // cas defausse à cause d'une construction
<<<<<<< HEAD
                    else if (London.getListeJoueur().getJoueur().getDefausse() != 0) {
=======
                    else if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() != 0) {

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());
                        carte.setPosition("etalage");
<<<<<<< HEAD
                        
                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        // si carte ne respecte pas les contraintes de defausse
                        if (London.getListeJoueur().getJoueur().payeConstruction(carte.getCarte()) == false) {
                            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                        } else {
                            // ajout de la carte dans l'etalage
                            London.getEtalage().addCarte(carte.getCarte());
                            // on rafrachit l'etalage
                            London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
                            // on enleve la carte de la main du joueur ( graphiquement )
                            London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());
                            //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                            
                            // suppression de la carte de la main du joueur
                            
                            London.getListeJoueur().getJoueur().getMain().remove(carte.getCarte());
                            
                            
                            // refresh
                            London.getSouth().repaint();
                            London.getSouth().revalidate();
                            
=======

                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        // si carte ne respecte pas les contraintes de defausse
                        if (Main.getJeu().getListeJoueur().getJoueur().payeConstruction(carte.getCarte()) == false) {
                            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                        } else {
                            // ajout de la carte dans l'etalage
                            Main.getJeu().getEtalage().addCarte(carte.getCarte());
                            // on rafrachit l'etalage
                            Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                            // on enleve la carte de la main du joueur ( graphiquement )
                            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());

                            //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                            // suppression de la carte de la main du joueur
                            Main.getJeu().getListeJoueur().getJoueur().getMain().remove(carte.getCarte());

                            // refresh
                            Main.getJeu().getSouth().repaint();
                            Main.getJeu().getSouth().revalidate();

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;
<<<<<<< HEAD
                    
                case "etalage": // on met la carte de l'etalage dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if (London.getListeJoueur().getJoueur().getPioche() != 0) {
                        // ajout de la carte dans la main du joueur graphiquement
                        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).getCarte());
                        // ajout de la carte das la main du joueur
                        London.getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).getCarte());
                        // suppresssion de la carte dans l'etalage
                        London.getEtalage().piocherCarte(((JBCarte) e.getComponent()).getCarte());
                        // on rafraichit l'étalage
                        London.getJpEtalage().actualiser(London.getEtalage().getLigne1(), London.getEtalage().getLigne2());
                        London.getListeJoueur().getJoueur().piocheMoins();
                        
                        ((JBCarte) e.getComponent()).setPosition("main");
                        
=======

                case "etalage": // on met la carte de l'etalage dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))

                    if (Main.getJeu().getListeJoueur().getJoueur().getPioche() != 0) {
                        // ajout de la carte dans la main du joueur graphiquement
                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).getCarte());
                        // ajout de la carte das la main du joueur
                        Main.getJeu().getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).getCarte());
                        // suppresssion de la carte dans l'etalage
                        Main.getJeu().getEtalage().piocherCarte(((JBCarte) e.getComponent()).getCarte());
                        // on rafraichit l'étalage
                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                        Main.getJeu().getListeJoueur().getJoueur().piocheMoins();

                        ((JBCarte) e.getComponent()).setPosition("main");

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas prendre de cette carte");
                    }
                    break;
<<<<<<< HEAD
                    
                case "fenetre": // on met la carte de la fenetre dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if (JBCarte.getDoubleClick()) {
                        
                        int rep = JOptionPane.showConfirmDialog(London.acc,
=======

                case "fenetre": // on met la carte de la fenetre dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if (JBCarte.getDoubleClick()) {

                        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                                "Êtes-vous sûr de vouloir choisir \"" + ((JBCarte) e.getComponent()).getCarte().getNom() + "\" ?",
                                "Piocher",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
<<<<<<< HEAD
                            London.getMenudroite().getF().dispose();
                            London.getMenudroite().getF().remetCartes(((JBCarte) e.getComponent()));
                            // ajout de la carte dans la main du joueur graphiquement
                            London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).getCarte());
                            // ajout de la carte das la main du joueur
                            London.getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).getCarte());
                            London.getListeJoueur().getJoueur().piocheMoins();
=======

                            Main.getJeu().getMenudroite().getF().dispose();
                            Main.getJeu().getMenudroite().getF().remetCartes(((JBCarte) e.getComponent()));
                            // ajout de la carte dans la main du joueur graphiquement
                            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).getCarte());
                            // ajout de la carte das la main du joueur
                            Main.getJeu().getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).getCarte());
                            Main.getJeu().getListeJoueur().getJoueur().piocheMoins();

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;
<<<<<<< HEAD
                    
                case "construction": // on active une carte qui est dans la zone de construction
                    if (JBCarte.isActiverCarte()) {
                        int rep = JOptionPane.showConfirmDialog(London.acc,
=======

                case "construction": // on active une carte qui est dans la zone de construction
                    if (JBCarte.isActiverCarte()) {

                        int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                                "Êtes-vous sûr de vouloir activer cette carte ?",
                                "Activer la carte",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
<<<<<<< HEAD
                            London.getListeJoueur().getJoueur().activerCarte(((Constructible) ((JBCarte) e.getComponent()).getCarte()));
                            
                            // on check si la carte doit être retourné
                            if (((Constructible) ((JBCarte) e.getComponent()).getCarte()).isARetourne()) {
                                ((JBCarte) e.getComponent()).changerImage("../img/cartes/Background.png");
                            }
                            London.getInfos().maj_infos();
                            London.getListeJoueur().getJoueur().setFinitTour(true);
                            
                        }
                        
                    }
                    break;
                    
            }
        }
        
        if (e.getButton() == MouseEvent.BUTTON3) {
            if (JBCarte.isClicDroitJouer()) {
                if (((JBCarte) e.getComponent()).getCarte().getClass() == NonConstructible.class) {
                    if (!"Paupers".equals(((JBCarte) e.getComponent()).getCarte().getNom())) {
                        int rep = JOptionPane.showConfirmDialog(London.acc,
                                "Êtes-vous sûr de vouloir jouer cette carte ?",
                                "Jouer carte",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
                            System.out.println(((JBCarte) e.getComponent()).getCarte().getNom());
                            London.getListeJoueur().getJoueur().jouerCarte2(((JBCarte) e.getComponent()).getCarte(),0);
                            London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(((JBCarte) e.getComponent()).getCarte());
                            
                            /*Wren*/
                            if (London.getListeJoueur().getJoueur().getPouvoir().get("Wren") == 1) {
                                London.getMenudroite().getLabelInfo().setText("<html>[Pouvoir Wren] Posez deux cartes sur votre<br/>chantier sans devoir défausser des cartes</html>");
                            }
                            
                            /*Hugueunot*/
                            if (London.getListeJoueur().getJoueur().getPouvoir().get("Huguenots") == 1) {
                                PouvoirBeta.pouvoirHuguenots(London.getListeJoueur().getJoueur());
                                
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Paupers ne peux pas être jouée");
                        
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cette carte est constructible'");
                }
            } else {
                if(London.getListeJoueur().getJoueur().getPioche()==0){
                    JOptionPane.showMessageDialog(null, "Vous devez choisir l'action 'Jouer des cartes'");
                }else{
                    JOptionPane.showMessageDialog(null, "Vous devez piocher");
=======

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
>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
<<<<<<< HEAD
        
=======

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
    }

    @Override
    public void mouseReleased(MouseEvent e) {
<<<<<<< HEAD
        
=======

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JPZoom.setImg(((JBCarte) e.getComponent()).getCarte().getPath());
    }

<<<<<<< HEAD
    

=======
>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
    @Override
    public void mouseExited(MouseEvent e) {
        JPZoom.setImg("../img/cartes/Background.png");

    }
<<<<<<< HEAD
    
=======

>>>>>>> e2717739f8164ac8c00945a5218efb7d6879c598
}
