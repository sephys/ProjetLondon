/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Joke
 */
public class MenuDroite extends JPanel{
    
    private JLabel labelJoueur; // indique quel joueur joue
    private JPanel main;
    private JButton jouer, restaurer, investir, emprunter, piocher3, piocher, finTour;
    public static boolean invest; // a virer !
    
    
    public MenuDroite()
    {
        super(new BorderLayout());
        main=new JPanel();
        main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
        
        // 1er attribut : largeur
        Dimension d=new Dimension(310,260);
        
        main.setPreferredSize(d);
        main.setBackground(Color.yellow);
        
        // a changer : dynamique
        labelJoueur=new JLabel(London.getListeJoueur().getJoueur().getNom());
        
        jouer = new JButton("Jouer des cartes");
        restaurer = new JButton("Restaurer la ville");
        investir = new JButton("Investir");
        emprunter = new JButton("Emprunter (10£)");
        this.invest = false;
        piocher3 = new JButton("Prendre trois cartes");
        finTour=new JButton("Fin du Tour");
        
        main.add(labelJoueur);
        main.add(jouer);
        main.add(restaurer);
        main.add(investir);
        main.add(emprunter);
        main.add(piocher3);
        
        
        emprunter.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                 int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir emprunter 10£ à la banque ?",
                        "Emprunter",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION){
                    London.getListeJoueur().getJoueur().setNbPret(London.getListeJoueur().getJoueur().getNbPret()+1);
                    London.getListeJoueur().getJoueur().setArgent(London.getListeJoueur().getJoueur().getArgent()+10);
                    London.infos.maj_infos();
                }
            }
        });
        
        this.piocher = new JButton("Piocher");
        piocher.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e) {
                London.getListeJoueur().getJoueur().piocheCarte(London.getDeck().peekFirst());
                London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(London.getDeck().poll());
            }
            
        });
        
        piocher3.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                 for(int i=0;i<3;i++)
                 {
                     London.getListeJoueur().getJoueur().piocheCarte(London.getDeck().peekFirst());
                     London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()].ajoutCarte(London.getDeck().poll());
                 }
            }
        });
        
        
        finTour.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // check trop grand nombre de cartes dans la main
                if(London.getListeJoueur().getJoueur().getMain().size()>9)
                {
                   JOptionPane.showMessageDialog(null, "Vous avez trop de cartes en main. Vous devez vous en défausser avant de finir votre tour");
                   disableAll();
                }
                
                
                actualiserMain();
                jouer.setEnabled(true);
                restaurer.setEnabled(true);
                investir.setEnabled(true);
                piocher3.setEnabled(true);
                piocher.setEnabled(true);
                invest = false;
                
                
            }
            
        });
        
        main.add(piocher);
        main.add(finTour);
        this.add(main,BorderLayout.NORTH);
        
        JPZoom zoom=new JPZoom();
        zoom.setPreferredSize(new Dimension(300,400));
        // zoom.setBackground(Color.red);
        this.add(zoom,BorderLayout.SOUTH);
        
        
        investir.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir investir ?",
                        "Investir",
                        JOptionPane.YES_NO_OPTION);
                if (rep == JOptionPane.YES_OPTION){
                    disableAll();
                    London.getPlateau().activerZonesInvestissables();
                    invest = true;
                }
            }
        });
    }
    
    public void actualiserMain()
    {
        
        
        
// sauvegarde de la main dans le tableau
        London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()]=(JPMain) London.south;
// on enleve la main
        London.central.remove(London.south);
// on passe au joueur suivant
        London.setListeJoueur(London.getListeJoueur().getSuivant());
// change le label nomJoeuur
        labelJoueur.setText(London.getListeJoueur().getJoueur().getNom());
// on remplace le panel par celui du nouveau joueur
        London.south=London.getTabJPMain()[London.getListeJoueur().getJoueur().getPlaceJoueur()];
// on ajoute le panel
        London.central.add(London.south,BorderLayout.SOUTH);
        
// actualiser la fenêtre
        London.frame.repaint();
        London.central.revalidate();
    }
    
    public void disableAll()
    {
                    jouer.setEnabled(false);
                    restaurer.setEnabled(false);
                    investir.setEnabled(false);
                    piocher3.setEnabled(false);
                    piocher.setEnabled(false);
                    emprunter.setEnabled(false);
                    finTour.setEnabled(false);
        
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
    

    
    
    
    
    
    
}
