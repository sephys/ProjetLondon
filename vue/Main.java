/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Carte;

/**
 *
 * @author Anh-Djuy
 */
public class Main extends Container implements MouseListener {
    
    ArrayList<Carte> cartes;
    JPanel haut;
    JPanel bas;
    int leftPadding;
    
    
    public Main(){
        cartes = new ArrayList<>();
        haut = new JPanel();
        bas = new JPanel();
        //JScrollPane scroll = new JScrollPane(haut);
        this.setLayout(new BorderLayout());
        haut.setSize(820, 308);
        bas.setSize(820, 512);
        this.add(haut, BorderLayout.NORTH);
        this.add(bas, BorderLayout.SOUTH);
        //this.add(scroll);
        this.leftPadding = 10;
    }
    
    public void ajoutCarte(Carte c){
        
        try {
            URL uri = Main.class.getResource(c.getPath());
            Image image = ImageIO.read(uri);
            haut.add(new PanelCartes(leftPadding, image));
            this.leftPadding += 100;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.out.println("Pas bonne URL !");
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Fichier pas trouvé !");
            //e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        Carte c = new Carte("Pauppers", "gris", "A", "../img/cartes/LePauvre.png");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        Main main = new Main();
        panel.add(main);
        frame.setContentPane(panel);
        frame.setSize(820,820);
        frame.setLocationRelativeTo(null);
        main.ajoutCarte(c);
        frame.setVisible(true);
    }
    
    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
