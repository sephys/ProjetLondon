/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Joke
 * @Vue
 * Représente la fenêtre où l'on peut choisir le nombre de joueur
 */
public class Menu extends JPanel{
        Image image;

    public Menu(){
        this.setLayout(new BorderLayout());
        //image = getToolkit().getImage("../img/menu.png");
        try {
			URL uri = Menu.class.getResource("../img/menu.png");
			image = ImageIO.read(uri);
			JButton test=new JButton("Jouer");
			test.setBounds(500,500,50,50);
			this.add(test,BorderLayout.SOUTH);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Pas bonne URL !");
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fichier pas trouv� !");
			//e.printStackTrace();
		}
    }
    
    @Override
    public void paintComponent(Graphics g){
     super.paintComponent(g);
     g.drawImage (image, 0, 0, getWidth(),getHeight(),this);
     repaint();
   }
}
