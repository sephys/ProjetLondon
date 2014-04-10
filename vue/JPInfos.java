/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import model.Joueur;

/**
 *
 * @author Anh-Djuy
 */
public class JPInfos extends JPanel{
    
    JPSousInfos[] aljpsi;
    
    public JPInfos(Joueur listeJoueurs[]){
        aljpsi = new JPSousInfos[Joueur.getNbJoueur()];
        this.setPreferredSize(new Dimension(250,810));
        this.setLayout(new GridLayout(4,1,0,20));
        for(int i = 0; i < listeJoueurs.length; i++){
            JPSousInfos jps = new JPSousInfos(listeJoueurs[i].getNom());
            aljpsi[i] = jps;
            this.add(jps);

        }
    }
    
    public void maj_infos(){
        Joueur j = London.getListeJoueur().getJoueur();
        for(int i = 0; i < aljpsi.length; i++){
            if(aljpsi[i].nomJoueur.getText().equals(j.getNom())){
                aljpsi[i].setEmprunt(j.getNbPret());
                aljpsi[i].setPieces(j.getArgent());
                aljpsi[i].setPauvrete(j.getPointPauvrete());
                aljpsi[i].setVictoire(j.getPointVictoire());
                //LE NOMBRE DE METRO NE SE MET PAS A JOUR DANS CETTE FONCTION CAR INEXISTANT DANS LA CLASSE JOUEUR
            }
        }
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        Joueur liste[]=null;
        f.add(new JPInfos(liste));
        f.pack();
        f.setVisible(true);
    }
}
