package model;

import controleur.DeuxJoueurs;
import controleur.QuatreJoueurs;
import controleur.TroisJoueurs;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import vue.London;
import vue.Main;

public class PouvoirBeta {

    private static int specialDouble = 0; // Wren //huguenots Jewish
    private static int speChoix; //

    //pouvoir illimité.
    public static boolean pouvoirSchool(Joueur j, Carte cD) {
        boolean res = false;
        int test = j.getPouvoir().get("School");
        if (cD.getCouleur().compareTo(j.getLastCarte().getCouleur()) != 0) {			//les cartes joueur sont de couleur différente
            if (test != 0) { 							//possède le pouvoir de l'école
                System.out.println("utilisation school" + j.getArgent());		//paye 1 pour que la carte "change de couleur"
                res = true;
                j.addArgent(-1);
                j.defausseMoins(cD);
                Main.getJeu().getInfos().maj_infos();
            }
        } else { 										//carte de même couleur
            res = true;
            System.out.println("res school" + res);
        }
        return res;
    }

    public static boolean pouvoirHuguenots(Joueur j) {
        boolean res = false;
        System.out.println(j.isFinitTour());
        int test = j.getPouvoir().get("Huguenots");
        if (test >= 1) {
            res = true;
            j.getPouvoir().put("Huguenots", new Integer(j.getPouvoir().get("Huguenots").intValue() - 1));
            j.setPioche(2);
        }
        return res;
    }

    //pouvoir limité.
    public static boolean pouvoirWren(Joueur j) {
        boolean res = false;
        int test = j.getPouvoir().get("Wren");
        if (test != 0) {
            res = true;//possède le pouvoir wren
            if (specialDouble == 0) {
                //si première carte 
                specialDouble = 1;				//première utilisation						//on peut utiliser le pouvoir
            } else {
                //sinon 2 carte on décremente le pouvoir wren

                j.getPouvoir().put("Wren", new Integer(j.getPouvoir().get("Wren").intValue()) - 1);
                specialDouble = 0;

            }
        } else {									// ne possède pas le pouvoir wren
            res = false;
        }
        // TODO Auto-generated method stub
        return res;
    }

    public static void pouvoirCoffee(Joueur J, Constructible carte) {
        JOptionPane.showMessageDialog(null, "Coffee House activé !");
        Main.getJeu().getMenudroite().getLabelInfo().setText("Choississez une carte de l'étalage");
        
        /*On ajoute le pouvoir dans le tableau de pouvoirs du joueur (obligatoire car on doit faire des conditions dans JBCarte)*/
        if (J.getPouvoir().get("Coffee") != null) {
            Main.getJeu().getJpChantier().getChantiers();
            J.getPouvoir().put("Coffee", new Integer(1));
        }
        // change onglet sur etélage
        Main.getJeu().getPanelOnglet().setSelectedIndex(1);
    }

    // done
    public static void pouvoirFireBrigade(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null && (!n.getProprietaire().equals(j))) {
                // si le jouer a plus d'argent ou l'oblige à prendre un pret
                if (n.getProprietaire().getArgent() == 0) {
                    n.getProprietaire().addPret(1);
                    n.getProprietaire().addArgent(-1);
                } else {
                    n.getProprietaire().addArgent(-1);
                }

            }
        }
    }

    public static void pouvoirFleetStreet() {
        JOptionPane.showMessageDialog(null, "Fleet Street activé !");

        final JFrame choixJoueur = new JFrame();
        choixJoueur.setLayout(new GridLayout(4, 1));
        choixJoueur.setSize(300, 200);
        choixJoueur.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // premet de lier les radio boutons
        ButtonGroup bg = new ButtonGroup();

        final String nomJ1 = Main.getJeu().getListeJoueur().getSuivant().getJoueur().getNom();
        final String nomJ2 = Main.getJeu().getListeJoueur().getSuivant().getSuivant().getJoueur().getNom();
        final String nomJ3 = Main.getJeu().getListeJoueur().getSuivant().getSuivant().getSuivant().getJoueur().getNom();

        final JRadioButton b1 = new JRadioButton(nomJ1);
        final JRadioButton b2 = new JRadioButton(nomJ2);
        final JRadioButton b3 = new JRadioButton(nomJ3);

        bg.add(b1);
        bg.add(b2);
        bg.add(b3);

        choixJoueur.add(b1);
        choixJoueur.add(b2);
        choixJoueur.add(b3);

        JButton p = new JButton("Ok");

        p.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (b1.isSelected()) {
                    Main.getJeu().getListeJoueur().getSuivant().getJoueur().addPointPauvrete(2);
                } else if (b2.isSelected()) {
                    Main.getJeu().getListeJoueur().getSuivant().getSuivant().getJoueur().addPointPauvrete(2);
                } else {
                    Main.getJeu().getListeJoueur().getSuivant().getSuivant().getSuivant().getJoueur().addPointPauvrete(2);

                }
                Main.getJeu().getInfos().maj_infos();

                choixJoueur.dispose();
            }
        });
        choixJoueur.setUndecorated(true);
        choixJoueur.add(p);
        choixJoueur.setLocationRelativeTo(null);
        choixJoueur.setVisible(true);

    }

    public static void pouvoirLloydsOfLondon() {
        JOptionPane.showMessageDialog(null, "Lloyds of Lonfon activé !");
        for (int i = 0; i < Main.getJeu().getTabJoueur().length; i++) {
            Main.getJeu().getTabJoueur()[i].addArgent(-2);
        }
        Main.getJeu().getListeJoueur().getJoueur().addArgent(Main.getJeu().getTabJoueur().length * 2);
        Main.getJeu().getInfos().maj_infos();

    }

    public static void pouvoirOmnibus() {
        JOptionPane.showMessageDialog(null, "Omnibus activé !");
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null) {
                if (n.getProprietaire() != null) {
                    if (n.getProprietaire().equals(Main.getJeu().getListeJoueur().getJoueur())) {
                        Main.getJeu().getListeJoueur().getJoueur().addArgent(1);
                    }
                }

            }

        }
        Main.getJeu().getInfos().maj_infos();
    }

    public void pouvoirPoliceForce(Joueur donneur, Joueur donne) {
        donneur.addPointPauvrete(-1);
        donne.addPointPauvrete(1);
    }

    public void pouvoirSteamBoats(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null && n.getProprietaire().equals(j) && n.isAdjacentTamise()) {
                j.addArgent(2);
            }
        }
    }

    public void pouvoirTownHouse(Joueur j) {
        int argent = 0;
        for (Carte c : j.getMain()) {
            if (!c.getCouleur().equals("marron")) {
                argent++;
            }
        }
        j.addArgent(argent);
    }

    public static void pouvoirNorthTrainStation(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null && n.getProprietaire().equals(j) && (!n.isDessousTamise())) {
                j.addArgent(2);
            }
        }
    }

    public static void pouvoirSouthTrainStation(Joueur j) {
        for (Zone n : Main.getJeu().getZones().values()) {
            if (n != null && n.getProprietaire().equals(j) && n.isDessousTamise()) {
                j.addArgent(2);
            }
        }
    }

}
