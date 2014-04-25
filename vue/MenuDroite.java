/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controleur.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.*;

/**
 *
 * @author Joke
 */
public class MenuDroite extends JPanel {

    //private JLabel labelJoueur; // indique quel joueur joue
    private JLabel labelInfo; // indique ce que doit faire l'utilisateur
    private JPanel menuBouton; // panel contenant tous les boutons
    private JButton jouer, restaurer, investir, emprunter, piocher3, piocher, regarder3Cartes, finTour; // les différents boutons du menu

    private JPnomGaucheImageDroite jpsij;
    MenuDroiteControl m;

    private Image img;

    private Frame3Cartes f;

    public MenuDroite() {
        super(new BorderLayout());
        m = new MenuDroiteControl();
        menuBouton = new JPanel();
        menuBouton.setLayout(new BoxLayout(menuBouton, BoxLayout.Y_AXIS));

        // position le menuBouton
        Dimension d = new Dimension(310, 260);

        // panel contenant image + nom du joueur
        jpsij = new JPnomGaucheImageDroite();
        this.add(jpsij);

        // transparance
        menuBouton.setOpaque(false);

        try {
            URL uri = JPEtalage.class.getResource("../img/rgbg3.png");
            img = ImageIO.read(uri);
        } catch (IOException ex) {
            Logger.getLogger(JPEtalage.class.getName()).log(Level.SEVERE, null, ex);
        }

        // intancie tous les boutons
        jouer = new JButton("    Jouer des cartes    ");
        jouer.setAlignmentX(Component.CENTER_ALIGNMENT);
        restaurer = new JButton("   Restaurer la ville   ");
        restaurer.setAlignmentX(Component.CENTER_ALIGNMENT);
        investir = new JButton("          Investir           ");
        investir.setAlignmentX(Component.CENTER_ALIGNMENT);
        emprunter = new JButton("    Emprunter (10£)   ");
        emprunter.setAlignmentX(Component.CENTER_ALIGNMENT);

        piocher3 = new JButton(" Prendre trois cartes ");
        piocher3.setAlignmentX(Component.CENTER_ALIGNMENT);
        regarder3Cartes = new JButton("Regarder les 3 premières cartes");
        regarder3Cartes.setAlignmentX(Component.CENTER_ALIGNMENT);
        finTour = new JButton("        Fin du Tour       ");
        finTour.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelInfo = new JLabel("");
        labelInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        int nbCarteRestantes = Main.getJeu().getDeck().size();
        this.piocher = new JButton("          Piocher (" + nbCarteRestantes + ")      ");
        piocher.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton test = new JButton("test");
        test.addActionListener(new TestControl());

        jouer.setPreferredSize(new Dimension(149, 26));
        restaurer.setPreferredSize(new Dimension(149, 26));
        investir.setPreferredSize(new Dimension(149, 26));
        emprunter.setPreferredSize(new Dimension(149, 26));
        piocher.setPreferredSize(new Dimension(149, 26));
        piocher3.setPreferredSize(new Dimension(149, 26));
        finTour.setPreferredSize(new Dimension(149, 26));

        // on les ajoute au menu
        menuBouton.add(test);
        menuBouton.add(jpsij);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 6)));
        menuBouton.add(jouer);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 6)));
        menuBouton.add(restaurer);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 6)));
        menuBouton.add(investir);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 6)));
        menuBouton.add(emprunter);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 6)));
        menuBouton.add(piocher3);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 4)));
        menuBouton.add(regarder3Cartes);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 6)));
        menuBouton.add(piocher);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 6)));
        menuBouton.add(finTour);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 30)));
        menuBouton.add(labelInfo);
        menuBouton.add(Box.createRigidArea(new Dimension(0, 30)));

        // ACTION BOUTON JOUERCARTES
        jouer.addActionListener(new JouerControl());

        // ACTION BOUTON RESTAURER LA VILLE
        restaurer.addActionListener(new RestaurerControl());

        // ACTION BOUTON EMPRUNTER
        emprunter.addActionListener(new EmprunterControl());

        // ACTION BOUTON PIOCHER
        piocher.addActionListener(new PiocherControl());

        // ACTION BOUTON PIOCHER 3
        piocher3.addActionListener(new Piocher3Control());

        // POUVOIR DE "UNIVERSITY OF LONDON"
        regarder3Cartes.addActionListener(new Regarder3CartesControl());

        // ACTION BOUTON FIN DE TOUR
        finTour.addActionListener(new FinTourControl());

        // ACTION BOUTON INVESTIR
        investir.addActionListener(new InvestirControl());

        // ajout du menuBOUTON
        this.add(menuBouton, BorderLayout.CENTER);

        // Panel conteant le zoom de la carte
        JPZoom zoom = new JPZoom();
        zoom.setPreferredSize(new Dimension(300, 400));
        // zoom.setBackground(Color.red);
        this.add(zoom, BorderLayout.SOUTH);
        regarder3Cartes.setVisible(false);

        // on doit piocher au début
        investir.setEnabled(false);
        piocher3.setEnabled(false);
        finTour.setEnabled(false);
        restaurer.setEnabled(false);
        jouer.setEnabled(false);
        System.out.println("fin de tour:" + Main.getJeu().getListeJoueur().getFinTour());
        
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 310, 810, this);
    }

    public JPnomGaucheImageDroite getJpsij() {
        return jpsij;
    }

    public void setJpsij(JPnomGaucheImageDroite jpsij) {
        this.jpsij = jpsij;
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

    public Frame3Cartes getF() {
        return this.f;
    }

    public void setFrame3Cartes(Frame3Cartes f) {
        this.f = f;
    }

    public JButton getRegarder3Cartes() {
        return this.regarder3Cartes;
    }
}
