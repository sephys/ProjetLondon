/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author Anh-Djuy
 */
public class Frame3Cartes extends JFrame{
    
    JPanel panel, panelBas;
    JBCarte c1, c2, c3;
    
    public Frame3Cartes(JBCarte c1, JBCarte c2, JBCarte c3){
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        panel = new JPanel(new BorderLayout());
        panelBas = new JPanel(new GridLayout(1,3));
        JLabel jtp = new JLabel();
        jtp.setText("Veuillez double-cliquer la carte de votre choix, ou fermer la fenêtre pour piocher dans l'étalage.");
        panel.add(jtp, BorderLayout.NORTH);
        panel.add(panelBas, BorderLayout.SOUTH);
        c1.changeTailleBoutonImage(new Dimension(211, 325));
        c2.changeTailleBoutonImage(new Dimension(211, 325));
        c3.changeTailleBoutonImage(new Dimension(211, 325));
        c1.setPosition("fenetre");
        c2.setPosition("fenetre");
        c3.setPosition("fenetre");
        panelBas.add(c1);
        panelBas.add(c2);
        panelBas.add(c3);
        this.add(panel);
        this.pack();
        this.setVisible(true);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(
                (screenSize.width-this.getWidth())/2,
                (screenSize.height-this.getHeight())/2
        );
    }
    
    public void remetCartes(JBCarte aThis) {
        if(aThis == this.c1){
            Main.getJeu().getDeck().addFirst(c2.getCarte());
            Main.getJeu().getDeck().addFirst(c3.getCarte());
        }else{
            
            if(aThis == this.c2){
                Main.getJeu().getDeck().addFirst(c1.getCarte());
                Main.getJeu().getDeck().addFirst(c3.getCarte());
            }else{
                if(aThis == this.c3){
                    Main.getJeu().getDeck().addFirst(c1.getCarte());
                    Main.getJeu().getDeck().addFirst(c2.getCarte());
                }
            }
        }
    }
}
