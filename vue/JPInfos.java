/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Anh-Djuy
 */
public class JPInfos extends JPanel{
    
    JComboBox joueurs;
    JTextPane pieces, pauvrete, emprunts;
    
    public JPInfos(){
        try {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            String listeJoueurs[] = {"Jean", "Baptiste", "Darin", "Allan", "Anh-Djuy"};
            joueurs = new JComboBox(listeJoueurs);
            pieces = new JTextPane();
            URL uri = JPMain.class.getResource("../img/jetons/JetonMetro.png");
            Image image = ImageIO.read(uri);
            pieces.insertIcon(new ImageIcon(image));
            pieces.setEditable(false);
            pieces.setText("Pieces : 5");
            pauvrete = new JTextPane();
            pauvrete.setEditable(false);
            pauvrete.setText("Pauvrete : 5");
            emprunts = new JTextPane();
            emprunts.setEditable(false);
            emprunts.setText("Emprunts : 0");
            this.add(this.joueurs);
            this.add(this.pieces);
            this.add(this.pauvrete);
            this.add(this.emprunts);
        } catch (IOException ex) {
            Logger.getLogger(JPInfos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public JPInfos(String[] listeJoueurs, String pieces, String pauvrete, String emprunts){
        this.joueurs = new JComboBox(listeJoueurs);
        this.pieces = new JTextPane();
        this.pieces.setEditable(false);
        this.pieces.setText(pieces);
        this.pauvrete = new JTextPane();
        this.pauvrete.setEditable(false);
        this.pauvrete.setText(pauvrete);
        this.emprunts = new JTextPane();
        this.emprunts.setEditable(false);
        this.emprunts.setText(emprunts);
        this.add(this.joueurs);
        this.add(this.pieces);
        this.add(this.pauvrete);
        this.add(this.emprunts);
    }
    
    public void setPieces(String pieces){
        this.pieces.setText(pieces);
    }
    
    public void setPauvrete(String pauvrete){
        this.pauvrete.setText(pauvrete);
    }
    
    public void setEmprunts(String emprunts){
        this.emprunts.setText(emprunts);
    }
    
    public static void main(String[] args){
        Frame f = new Frame();
        f.add(new JPInfos());
        f.setVisible(true);
    }
}
