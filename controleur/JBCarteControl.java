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

/**
 *
 * @author Anh-Djuy
 */
public class JBCarteControl implements MouseListener{
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2) {
            
            Joueur courrant = London.getListeJoueur().getJoueur();
            switch (((JBCarte) e.getComponent()).getPosition()) {
                case "main": // on met la carte de la main sur le l'étalage
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("defausse")&&((JBCarte) e.getComponent()).isDefausse())
                    // cas ou defausse car trop de cartes dans la main
                    if (London.getListeJoueur().getJoueur().getDefausse() != 0 && London.getListeJoueur().getJoueur().getLastCarte() == null) {
                        
                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());
                        carte.setPosition("etalage");
                        // ajout de la carte dans l'etalage
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
                        
                        //System.out.println("apres :"+London.getListeJoueur().getJoueur().getMain().size());
                        //London.getListeJoueur().getJoueur().defausseMoins();
                        //London.getListeJoueur().getJoueur().payeConstruction(carte.carte);
                    } // cas defausse à cause d'une construction
                    else if (London.getListeJoueur().getJoueur().getDefausse() != 0) {
                        /**/
                        //London.getMenudroite().getFinTour().setEnabled(true);
                        JBCarte carte = ((JBCarte) e.getComponent());
                        carte.setPosition("etalage");
                        
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
                            
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;
                    
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
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas prendre de cette carte");
                    }
                    break;
                    
                case "fenetre": // on met la carte de la fenetre dans la main
                    //if(doubleClick&&courrant.getPiocheDefausse().equals("pioche"))
                    if (JBCarte.getDoubleClick()) {
                        
                        int rep = JOptionPane.showConfirmDialog(London.acc,
                                "Êtes-vous sûr de vouloir choisir \"" + ((JBCarte) e.getComponent()).getCarte().getNom() + "\" ?",
                                "Piocher",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
                            London.getMenudroite().getF().dispose();
                            London.getMenudroite().getF().remetCartes(((JBCarte) e.getComponent()));
                            // ajout de la carte dans la main du joueur graphiquement
                            London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(((JBCarte) e.getComponent()).getCarte());
                            // ajout de la carte das la main du joueur
                            London.getListeJoueur().getJoueur().getMain().add(((JBCarte) e.getComponent()).getCarte());
                            London.getListeJoueur().getJoueur().piocheMoins();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous défausser de cette carte");
                    }
                    break;
                    
                case "construction": // on active une carte qui est dans la zone de construction
                    if (JBCarte.isActiverCarte()) {
                        int rep = JOptionPane.showConfirmDialog(London.acc,
                                "Êtes-vous sûr de vouloir activer cette carte ?",
                                "Activer la carte",
                                JOptionPane.YES_NO_OPTION);
                        if (rep == JOptionPane.YES_OPTION) {
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
