package controleur;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import model.Carte;
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

        Joueur courrant = Main.getJeu().getListeJoueur().getJoueur();
        JBCarte carteCourante = ((JBCarte) e.getComponent());
        if (e.getClickCount() == 2) {

            switch (((JBCarte) e.getComponent()).getPosition()) {
                case "main": // on met la carte de la main sur l'étalage
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("defausse")&&((JBCarte) e.getComponent()).isDefausse())
                    // cas ou defausse car trop de cartes dans la main
                    if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() != 0 && Main.getJeu().getListeJoueur().getJoueur().getLastCarte() == null) {
                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        carteCourante.setPosition("etalage");
                        // ajout de la carte dans l'etalage
                        Main.getJeu().getEtalage().addCarte(carteCourante.getCarte());
                        // on rafrachit l'etalage
                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                        // on enleve la carte de la main du joueur ( graphiquement )
                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(carteCourante.getCarte());

                        //System.out.println("avant :"+London.getListeJoueur().getJoueur().getMain().size());
                        // suppression de la carte de la main du joueur
                        Main.getJeu().getListeJoueur().getJoueur().getMain().remove(carteCourante.getCarte());

                        //on enlève la défausse
                        Main.getJeu().getListeJoueur().getJoueur().defausseMoins(carteCourante.getCarte());
                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        //London.getListeJoueur().getJoueur().payeConstruction(carte.carte);
                    } // cas defausse à cause d'une construction
                    else if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() != 0) {
                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        // si carte ne respecte pas les contraintes de defausse
                        if (Main.getJeu().getListeJoueur().getJoueur().payeConstruction(carteCourante.getCarte()) == false) {
                            if (Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("School") >= 1) {
                                int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                        "[Pouvoir school] Dépensez 1$ pour défausser une carte de couleur différente",
                                        "Pouvoir school",
                                        JOptionPane.YES_NO_OPTION);
                                if (rep == JOptionPane.YES_OPTION) {
                                    if (Main.getJeu().getListeJoueur().getJoueur().getArgent() >= 1) {
                                        PouvoirBeta.pouvoirSchool(Main.getJeu().getListeJoueur().getJoueur(), carteCourante.getCarte());
                                        /*La carte va dans l'étalage*/
                                        carteCourante.setPosition("etalage");
                                        // ajout de la carte dans l'etalage
                                        Main.getJeu().getEtalage().addCarte(carteCourante.getCarte());
                                        // on rafrachit l'etalage
                                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                                        // on enleve la carte de la main du joueur ( graphiquement )
                                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(carteCourante.getCarte());

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
                            carteCourante.setPosition("etalage");
                            // ajout de la carte dans l'etalage
                            Main.getJeu().getEtalage().addCarte(carteCourante.getCarte());
                            // on rafrachit l'etalage
                            Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                            // on enleve la carte de la main du joueur ( graphiquement )
                            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(carteCourante.getCarte());

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

                    if (Main.getJeu().getListeJoueur().getJoueur().getPioche() != 0) {
                        // ajout de la carte dans la main du joueur graphiquement
                        Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(carteCourante.getCarte());
                        // ajout de la carte das la main du joueur
                        Main.getJeu().getListeJoueur().getJoueur().getMain().add(carteCourante.getCarte());
                        // suppresssion de la carte dans l'etalage
                        Main.getJeu().getEtalage().piocherCarte(carteCourante.getCarte());
                        // on rafraichit l'étalage
                        Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());
                        Main.getJeu().getListeJoueur().getJoueur().piocheMoins();

                        ((JBCarte) e.getComponent()).setPosition("main");

                        /*Pouvoir Coffee Shop : ajout de la carte de l'étalage sur un chantier*/
                    } else if (Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("Coffee") >= 1) {

                        if (carteCourante.getCarte().getClass() == Constructible.class) {

                            /*Ajout visuel de la carte*/

                            /*Récupère l'index du chantier sur lequel se trouve la carte*/
                            int indexChantier = Main.getJeu().getListeJoueur().getJoueur().indexCarte("Coffee House");
                            Main.getJeu().getJpChantier().getChantiers()[indexChantier].removeAll();
                            Main.getJeu().getJpChantier().getChantiers()[indexChantier].ajoutCarte(carteCourante.getCarte());

                            /*Ajout dans le modèle de la carte*/
                            Main.getJeu().getListeJoueur().getJoueur().getMain().add(carteCourante.getCarte());

                            // suppresssion de la carte dans l'etalage
                            Main.getJeu().getEtalage().piocherCarte(carteCourante.getCarte());

                            /*Rafraichissement de l'étalage*/
                            Main.getJeu().getJpEtalage().actualiser(Main.getJeu().getEtalage().getLigne1(), Main.getJeu().getEtalage().getLigne2());

                            /*Changer la position de la carte*/
                            carteCourante.setPosition("construction");

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
                            Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(carteCourante.getCarte());
                            // ajout de la carte das la main du joueur
                            Main.getJeu().getListeJoueur().getJoueur().getMain().add(carteCourante.getCarte());
                            Main.getJeu().getListeJoueur().getJoueur().piocheMoins();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;

                case "construction": // on active une carte qui est dans la zone de construction
                    if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() == 0) {

                        if (JBCarte.isActiverCarte() && !carteCourante.getCarte().getNom().equals("Hospital")) {

                            int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                    "Êtes-vous sûr de vouloir activer cette carte ?",
                                    "Activer la carte",
                                    JOptionPane.YES_NO_OPTION);
                            if (rep == JOptionPane.YES_OPTION) {
                                Main.getJeu().getListeJoueur().getJoueur().activerCarte(((Constructible) carteCourante.getCarte()));

                                // on check si la carte doit être retourné et est activable
                                if (((Constructible) carteCourante.getCarte()).isARetourne() && ((Constructible) carteCourante.getCarte()).isActivable()) {

                                    /*Récupère l'index du chantier contenant une carte hopital sinon -1*/
                                    int indexChantier = Main.getJeu().getListeJoueur().getJoueur().indexCarte("Hospital");
                                    if (indexChantier != -1) {
                                        /*Récupère la carte sur la JPPileChantier*/
                                        JBCarte carteHospital = (JBCarte) Main.getJeu().getJpChantier().getChantiers()[indexChantier].getComponent(0);

                                        /*Vérifie que la carte hopital */
                                        if (((Constructible) carteHospital.getCarte()).isActivable()) {

                                            int rep2 = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                                    "Voulez vous retourner la carte Hospital à la place de celle-ci ?",
                                                    "Pouvoir Hospital",
                                                    JOptionPane.YES_NO_OPTION);
                                            if (rep2 == JOptionPane.YES_OPTION) {
                                                /*Retourne hospital*/
                                                ((Constructible) carteHospital.getCarte()).setActivable(false);

                                                carteHospital.changerImage("../img/cartes/Background.png");

                                                Main.getJeu().getInfos().maj_infos();
                                                Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true);

                                            } else {
                                                /*Retourne la carte*/
                                                ((Constructible) carteCourante.getCarte()).setActivable(false);
                                                ((JBCarte) e.getComponent()).changerImage("../img/cartes/Background.png");
                                                Main.getJeu().getInfos().maj_infos();
                                                Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true);

                                            }
                                        } else {
                                            /*Retourne la carte*/
                                            ((Constructible) carteCourante.getCarte()).setActivable(false);
                                            ((JBCarte) e.getComponent()).changerImage("../img/cartes/Background.png");
                                            Main.getJeu().getInfos().maj_infos();
                                            Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true);
                                        }
                                    } else {
                                        /*Retourne la carte*/
                                        ((Constructible) carteCourante.getCarte()).setActivable(false);
                                        ((JBCarte) e.getComponent()).changerImage("../img/cartes/Background.png");
                                        Main.getJeu().getInfos().maj_infos();
                                        Main.getJeu().getListeJoueur().getJoueur().setFinitTour(true);

                                    }
                                }

                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Vous devez vous défausser");
                        }
                        break;

                    }
            }
        }

        if (e.getButton()
                == MouseEvent.BUTTON3) {
            if (JBCarte.isClicDroitJouer()) {
                if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() == 0) {
                    System.out.println("Deffause JBCarte : " + Main.getJeu().getListeJoueur().getJoueur().getDefausse());
                    if (carteCourante.getCarte().getClass() == NonConstructible.class) {
                        if (!"Paupers".equals(((JBCarte) e.getComponent()).getCarte().getNom())) {

                            int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
                                    "Êtes-vous sûr de vouloir jouer cette carte ?",
                                    "Jouer carte",
                                    JOptionPane.YES_NO_OPTION);
                            if (rep == JOptionPane.YES_OPTION) {
                                System.out.println(carteCourante.getCarte().getNom());

                                Main.getJeu().getListeJoueur().getJoueur().jouerCarte2(carteCourante.getCarte(), 0);
                                Main.getJeu().getTabJPMain()[Main.getJeu().getListeJoueur().getJoueur().getPlaceJoueur()].removeCarte(carteCourante.getCarte());

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
                    JOptionPane.showMessageDialog(null, "Vous devez d'abord défausser une carte");
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
    public void mouseReleased(MouseEvent e
    ) {
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {
        JPZoom.setImg(((JBCarte) e.getComponent()).getCarte().getPath());
    }

    @Override
    public void mouseExited(MouseEvent e
    ) {
        JPZoom.setImg("../img/cartes/Background.png");

    }

    @Override
    public void mousePressed(MouseEvent e
    ) {
    }
}
