/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Anh-Djuy
 */
public class JPInfos extends JPanel{
    
    //JComboBox joueurs;
    ArrayList<JPSousInfos> aljpsi;
    
    public JPInfos(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        aljpsi = new ArrayList<JPSousInfos>();
        String listeJoueurs[] = {"Jean", "Baptiste", "Darin", "Allan"};
        for(int i = 0; i < listeJoueurs.length; i++){
            JPSousInfos jps = new JPSousInfos(listeJoueurs[i]);
            aljpsi.add(jps);
            this.add(jps);
            if(i != listeJoueurs.length-1){
                JSeparator separateur = new JSeparator();
                separateur.setPreferredSize(new Dimension(1, 10));
                this.add(new JSeparator());
            }
        }
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.add(new JPInfos());
        f.setVisible(true);
    }
}
