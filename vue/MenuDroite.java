/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;
import static vue.London.dndListener;

/**
 *
 * @author Joke
 */
public class MenuDroite extends JPanel {
    
    //private JLabel labelJoueur; // indique quel joueur joue
    private JLabel labelInfo; // indique ce que doit faire l'utilisateur
    private JPanel menuBouton; // panel contenant tous les boutons
    private JButton jouer, restaurer, investir, emprunter, piocher3, piocher, regarder3Cartes, finTour; // les différents boutons du menu
    public static boolean invest; // a virer !
    private JPnomGaucheImageDroite jpsij;

    private Image img;


    private Frame3Cartes f;
    

    public MenuDroite() {
        super(new BorderLayout());
        menuBouton = new JPanel();
        menuBouton.setLayout(new BoxLayout(menuBouton, BoxLayout.Y_AXIS));
        
        // position le menuBouton
        Dimension d = new Dimension(310, 260);
        
        jpsij = new JPnomGaucheImageDroite();
        this.add(jpsij);
        menuBouton.setOpaque(false);

           try {
            URL uri = JPEtalage.class.getResource("../img/rgbg3.png");            
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        

        // label du joueur qui joue
        //labelJoueur = new JLabel(London.getListeJoueur().getJoueur().getNom());
        
        // intancie tous les boutons
        jouer = new JButton("    Jouer des cartes    ");
        jouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        restaurer = new JButton("   Restaurer la ville   ");
        restaurer.setAlignmentX(Component.CENTER_ALIGNMENT);
        investir = new JButton("          Investir           ");
        investir.setAlignmentX(Component.CENTER_ALIGNMENT);
        emprunter = new JButton("    Emprunter (10£)   ");
        emprunter.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.invest = false;
        piocher3 = new JButton(" Prendre trois cartes ");
        piocher3.setAlignmentX(Component.CENTER_ALIGNMENT);
        regarder3Cartes = new JButton("Regarder les 3 premières cartes");
        regarder3Cartes.setAlignmentX(Component.CENTER_ALIGNMENT);
        finTour = new JButton("        Fin du Tour       ");
        finTour.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelInfo = new JLabel("");
        labelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.piocher = new JButton("          Piocher           ");
        piocher.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        jouer.setPreferredSize(new Dimension(149, 26));
        restaurer.setPreferredSize(new Dimension(149, 26));
        investir.setPreferredSize(new Dimension(149, 26));
        emprunter.setPreferredSize(new Dimension(149, 26));
        piocher.setPreferredSize(new Dimension(149, 26));
        piocher3.setPreferredSize(new Dimension(149, 26));
        finTour.setPreferredSize(new Dimension(149, 26));
        // on les ajoute au menu
        menuBouton.add(jpsij);
        menuBouton.add(Box.createRigidArea(new Dimension(0,6)));
        menuBouton.add(jouer);
        menuBouton.add(Box.createRigidArea(new Dimension(0,6)));
        menuBouton.add(restaurer);
        menuBouton.add(Box.createRigidArea(new Dimension(0,6)));
        menuBouton.add(investir);
        menuBouton.add(Box.createRigidArea(new Dimension(0,6)));
        menuBouton.add(emprunter);
        menuBouton.add(Box.createRigidArea(new Dimension(0,6)));
        menuBouton.add(piocher3);
        menuBouton.add(Box.createRigidArea(new Dimension(0,4)));
        menuBouton.add(regarder3Cartes);
        menuBouton.add(Box.createRigidArea(new Dimension(0,6)));
        menuBouton.add(piocher);
        menuBouton.add(Box.createRigidArea(new Dimension(0,6)));
        menuBouton.add(finTour);
        menuBouton.add(Box.createRigidArea(new Dimension(0,30)));
        menuBouton.add(labelInfo);
        menuBouton.add(Box.createRigidArea(new Dimension(0,30)));
        

        // on doit piocher au début
        disableAll();
        piocher.setEnabled(true);
        
        
      

        
        // ACTION BOUTON JOUERCARTES
        jouer.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) { 
                int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir jouer des cartes ?",
                        "Jouer des cartes",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                 London.dndListener.setDragEnable(true);
                 JBCarte.setClicDroitJouer(true);
                 labelInfo.setText("Vous pouvez jouer des cartes");
                 
                 disableAll();
                 finTour.setEnabled(true);
                 // change onglet
                 London.getPanelOnglet().setSelectedIndex(2);
                }
            }

        });
        
       // ACTION BOUTON RESTAURER LA VILLE
       restaurer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir restaurer la ville ?",
                        "Restaurer la ville",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    disableAll();
                    finTour.setEnabled(true);
                    labelInfo.setText("Vous pouvez activer des cartes");
                    JBCarte.setActiverCarte(true);
                    // change onglet
                    London.getPanelOnglet().setSelectedIndex(2);
                    
                }
                
               
            }
        });
        
        

        
        // ACTION BOUTON EMPRUNTER
        emprunter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir emprunter 10£ à la banque ?",
                        "Emprunter",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    London.getListeJoueur().getJoueur().setNbPret(London.getListeJoueur().getJoueur().getNbPret() + 1);
                    London.getListeJoueur().getJoueur().setArgent(London.getListeJoueur().getJoueur().getArgent() + 10);
                    London.getInfos().maj_infos();
                }
            }
        });
        
        // ACTION BOUTON PIOCHER
        piocher.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (London.getListeJoueur().getJoueur().getPioche() != 0) {
                    London.getListeJoueur().getJoueur().piocheCarte(London.getDeck().peekFirst());
                    London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(London.getDeck().poll());
                    London.getListeJoueur().getJoueur().piocheMoins();
                    London.getMenudroite().repaint();
                    London.getMenudroite().revalidate();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas le droit de piocher une carte");
                }
                
            }
            
        });
        
        // ACTION BOUTON PIOCHER 3
        piocher3.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir choisir l'action 'Piocher 3 cartes' ?",
                        "Emprunter",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    
                    London.getListeJoueur().getJoueur().setFinitTour(true); // le joueur a finit son tour apres avoir piocher 3 cartes
                    London.getListeJoueur().getJoueur().setPioche(3);
                    //London.getListeJoueur().getJoueur().setFinTourPiocheCarte(true);
                    
                }
            }
        });
                
        
        // POUVOIR DE "UNIVERSITY OF LONDON"
        regarder3Cartes.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JBCarte c1 = new JBCarte(London.getDeck().poll());
                JBCarte c2 = new JBCarte(London.getDeck().poll());
                JBCarte c3 = new JBCarte(London.getDeck().poll());
                //London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");
                f = new Frame3Cartes(c1, c2, c3);
            }
            
        });
        
        // ACTION BOUTON FIN DE TOUR
        finTour.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (London.getListeJoueur().getJoueur().isFinitTour()) {
                    // check trop grand nombre de cartes dans la main
                    if (London.getListeJoueur().getJoueur().getMain().size() > 9) {
                        //System.out.println(London.getListeJoueur().getJoueur().getMain().size());
                        //JBCarte.setDoubleClick(true);
                        //London.getListeJoueur().getJoueur().setPiocheDefausse("defausse");
                        //setDefausseCarte(true); // peut se defausser de n'importe quelle carte
                        
                        JOptionPane.showMessageDialog(null, "Vous avez trop de cartes en main. Vous devez vous en défausser avant de finir votre tour");
                       // if(London.getListeJoueur().getJoueur().getDefausse()==0)
                        //{
                            London.getListeJoueur().getJoueur().setDefausse(London.getListeJoueur().getJoueur().getMain().size() - 9);
                        //}
                        
                        disableAll();
                        labelInfo.setText("Vous avez trop de cartes en main");
                       // finTour.setEnabled(true);
                        
                       // change onglet
                        London.getPanelOnglet().setSelectedIndex(1);
                        
                    } else { // ici le joueur finit son tour
                        actualiserMain();
                        
                        invest = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas finit votre tour");
                }
                
            }
            
        });
        
        // ACTION BOUTON INVESTIR
        investir.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir investir ?",
                        "Investir",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION) {
                    disableAll();
                    labelInfo.setText("Vous devez choisir une zone à investir");
                    London.getPlateau().activerZonesInvestissables();
                    
                    London.getPlateau().desactiveZonesInvesties();
                    invest = true;
                    emprunter.setEnabled(true);
                    // change onglet
                    London.getPanelOnglet().setSelectedIndex(0);
                }
            }
        });
        
        // ajout du menuBOUTON
        this.add(menuBouton, BorderLayout.CENTER);
        
        // Panel conteant le zoom de la carte
        JPZoom zoom = new JPZoom();
        zoom.setPreferredSize(new Dimension(300, 400));
        // zoom.setBackground(Color.red);
        this.add(zoom, BorderLayout.SOUTH);
        regarder3Cartes.setVisible(false);
    }
    
    // methode qui permet de changer de joueur
    public void actualiserMain() {
        
        
          //passer chantier.carte2 à false
        for(int i=0;i<London.getJpChantier().getChantiers().length;i++){
            JPPileChantier chantier = London.getJpChantier().getChantiers()[i];
            chantier.setCarte2(false);
        }
        
        
        // sauvegarde de la main dans le tableau
        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()] = (JPMain) London.getSouth();
        
        // sauvegarde zone de construction
        London.getTabJPChantiers()[London.getListeJoueur().getJoueur().getPlaceJoueur()]=London.getJpChantier();
        
        // on enleve la main
        London.central.remove(London.getSouth());
        // on passe au joueur suivant
        London.setListeJoueur(London.getListeJoueur().getSuivant());
        // change le label nomJoeuur
        //labelJoueur.setText(London.getListeJoueur().getJoueur().getNom());
        jpsij.actualiseJoueur();
        // on remplace le panel par celui du nouveau joueur
        London.setSouth(London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()]);
        // on ajoute le panel
        London.central.add(London.getSouth(), BorderLayout.SOUTH);
        
      
        
        // on enleve la zone
        London.getPanelOnglet().remove(London.getPanelOnglet().getComponent(2));
        // on remplace la zone
        London.setJpChantier(London.getTabJPChantiers()[London.getListeJoueur().getJoueur().getPlaceJoueur()]);
        // on ajoute la zone
        London.getPanelOnglet().addTab("Chantiers",London.getJpChantier());

                
        // actualiser la fenêtre
        London.frame.repaint();
        London.central.revalidate();
        
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
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 310, 810, this);
    }


    
    

    public void disableAll() {
        jouer.setEnabled(false);
        restaurer.setEnabled(false);
        investir.setEnabled(false);
        piocher3.setEnabled(false);
        piocher.setEnabled(false);
        finTour.setEnabled(false);
    }
    
    public void enableAll() {
        jouer.setEnabled(true);
        restaurer.setEnabled(true);
        investir.setEnabled(true);
        piocher3.setEnabled(true);
        piocher.setEnabled(true);
        emprunter.setEnabled(true);
        finTour.setEnabled(true);
    }
    

    public void setTrueDefausseColor(String color)
    {
       for(int i=0;i<London.getSouth().getMain().getComponentCount();i++) // parcours la main du joueur
       {
           if(((JBCarte) London.getSouth().getMain().getComponent(i)).getCarte().getCouleur().equals(color)) // si la carte est de la même couleur
           {
               ((JBCarte) London.getSouth().getMain().getComponent(i)).setDefausse(true); // il peut la defausser
           }
           
       } 
    }
    
    public void setDefausseCarte(boolean bool)
    {
       for(int i=0;i<London.getSouth().getMain().getComponentCount();i++) // parcourt la main du joueuer
       {
           ((JBCarte) London.getSouth().getMain().getComponent(i)).setDefausse(bool); // il peut se defausser
       }
    }
    
    public void setFinTour()
    {
        disableAll();
        finTour.setEnabled(true);
        labelInfo.setText("Vous avez finit votre tour");
    }
    

    public JButton getJouer() {
        return jouer;
    }
    
    public JButton getRestaurer() {
        return restaurer;
    }
    
    public JButton getInvestir() {
        return investir;
    }
    
    public JButton getEmprunter() {
        return emprunter;
    }
    
    public JButton getPiocher3() {
        return piocher3;
    }
    
    public JButton getPiocher() {
        return piocher;
    }
    
    public JButton getFinTour() {
        return finTour;
    }
    
    public JLabel getLabelInfo() {
        return labelInfo;
    }
    
    public Frame3Cartes getF(){
        return this.f;
    }
    
    public void setFrame3Cartes(Frame3Cartes f){
        this.f = f;
    }
    
}
