/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

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
    
    private JLabel labelJoueur; // indique quel joueur joue
    private JLabel labelInfo; // indique ce que doit faire l'utilisateur
    private JPanel menuBouton; // panel contenant tous les boutons
    private JButton jouer, restaurer, investir, emprunter, piocher3, piocher, regarder3Cartes, finTour; // les différents boutons du menu
    public static boolean invest; // a virer !

    private Image img;


    private Frame3Cartes f;
    

    public MenuDroite() {
        super(new BorderLayout());
        menuBouton = new JPanel();
        menuBouton.setLayout(new BoxLayout(menuBouton, BoxLayout.PAGE_AXIS));
        
        // position le menuBouton
        Dimension d = new Dimension(310, 260);
        

        menuBouton.setOpaque(false);


       
        

        // label du joueur qui joue
        labelJoueur = new JLabel(London.getListeJoueur().getJoueur().getNom());
        
        // intancie tous les boutons
        jouer = new JButton("Jouer des cartes");
        restaurer = new JButton("Restaurer la ville");
        investir = new JButton("Investir");
        emprunter = new JButton("Emprunter (10£)");
        this.invest = false;
        piocher3 = new JButton("Prendre trois cartes");
        regarder3Cartes = new JButton("Regarder les 3 premières cartes");
        finTour = new JButton("Fin du Tour");
        labelInfo = new JLabel("Vous devez piocher");
        this.piocher = new JButton("Piocher");
        // on les ajoute au menu
        menuBouton.add(labelJoueur);
        menuBouton.add(jouer);
        menuBouton.add(restaurer);
        menuBouton.add(investir);
        menuBouton.add(emprunter);
        menuBouton.add(piocher3);
        menuBouton.add(regarder3Cartes);
        menuBouton.add(piocher);
        menuBouton.add(finTour);
        menuBouton.add(labelInfo);

        // on doit piocher au début
        disableAll();
        piocher.setEnabled(true);
        
        
        
        try {
            URL uri = JPEtalage.class.getResource("../img/rgbg2.png");            
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }

        
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
                 disableAll();
                 finTour.setEnabled(true);
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
                    London.infos.maj_infos();
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
                    disableAll();
                    piocher.setEnabled(true);
                    finTour.setEnabled(true);
                    London.getListeJoueur().getJoueur().setPioche(3);
                    London.getListeJoueur().getJoueur().setFinTourPiocheCarte(true);
                    labelInfo.setText("Vous devez piocher 3 cartes");
                    London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");
                    JBCarte.setDoubleClick(true);
                    London.getMenudroite().repaint();
                    London.getMenudroite().revalidate();
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
                London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");
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
                        System.out.println(London.getListeJoueur().getJoueur().getMain().size());
                        JBCarte.setDoubleClick(true);
                        London.getListeJoueur().getJoueur().setPiocheDefausse("defausse");
                        setDefausseCarte(true);
                        JOptionPane.showMessageDialog(null, "Vous avez trop de cartes en main. Vous devez vous en défausser avant de finir votre tour");
                        if(London.getListeJoueur().getJoueur().getDefausse()==0)
                        {
                            London.getListeJoueur().getJoueur().setDefausse(London.getListeJoueur().getJoueur().getMain().size() - 9);
                        }
                        
                        disableAll();
                        labelInfo.setText("Vous avez trop de cartes en main");
                        finTour.setEnabled(true);
                        
                    } else { // ici le joueur finit son tour
                        actualiserMain();
                        disableAll();
                        piocher.setEnabled(true);
                        labelInfo.setText("Vous devez piocher");
                        invest = false;
                        London.getMenudroite().repaint();
                        London.getMenudroite().revalidate();
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
                    London.getPlateau().activerZonesInvestissables();
                    London.getPlateau().desactiveZonesInvesties();
                    invest = true;
                }
            }
        });
        
        // ajout du menuBOUTON
        this.add(menuBouton, BorderLayout.NORTH);
        
        // Panel conteant le zoom de la carte
        JPZoom zoom = new JPZoom();
        zoom.setPreferredSize(new Dimension(300, 400));
        // zoom.setBackground(Color.red);
        this.add(zoom, BorderLayout.SOUTH);
        regarder3Cartes.setVisible(false);
    }
    
    // methode qui permet de changer de joueur
    public void actualiserMain() {
        
        
        
        
        // sauvegarde de la main dans le tableau
        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()] = (JPMain) London.south;
        
        // sauvegarde zone de construction
        London.getTabJPChantiers()[London.getListeJoueur().getJoueur().getPlaceJoueur()]=London.getJpChantier();
        
        // on enleve la main
        London.central.remove(London.south);
        // on passe au joueur suivant
        London.setListeJoueur(London.getListeJoueur().getSuivant());
        // change le label nomJoeuur
        labelJoueur.setText(London.getListeJoueur().getJoueur().getNom());
        // on remplace le panel par celui du nouveau joueur
        London.south = London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()];
        // on ajoute le panel
        London.central.add(London.south, BorderLayout.SOUTH);
        
        //passer chantier.carte2 à false
        for(int i=0;i<London.getJpChantier().getChantiers().length;i++){
            JPPileChantier chantier = London.getJpChantier().getChantiers()[i];
            chantier.setCarte2(false);
        }
        
        
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
        London.getListeJoueur().getJoueur().setFinTourPiocheCarte(false);
        
        // on informe le joueur
        JOptionPane.showMessageDialog(null, "C'est au tour de " + London.getListeJoueur().getJoueur().getNom() + " de jouer");
        London.getListeJoueur().getJoueur().setPiocheDefausse("pioche");
        

        // on peut pas se defausser a la base
        setDefausseCarte(false);

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
        emprunter.setEnabled(false);
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
       for(int i=0;i<London.south.getMain().getComponentCount();i++) // parcours la main du joueur
       {
           if(((JBCarte) London.south.getMain().getComponent(i)).getCarte().getCouleur().equals(color)) // si la carte est de la même couleur
           {
               ((JBCarte) London.south.getMain().getComponent(i)).setDefausse(true); // il peut la defausser
           }
           
       } 
    }
    
    public void setDefausseCarte(boolean bool)
    {
       for(int i=0;i<London.south.getMain().getComponentCount();i++) // parcourt la main du joueuer
       {
           ((JBCarte) London.south.getMain().getComponent(i)).setDefausse(bool); // il peut se defausser
       }
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
