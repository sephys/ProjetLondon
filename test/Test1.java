/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Joke
 */
//CTRL + SHIFT + O pour générer les imports
public class Test1 extends JFrame{

  public Test1(){
    super("Test de Drag'n Drop");
    setSize(300, 200);
      
    JPanel pan = new JPanel();
    pan.setBackground(Color.white);
    pan.setLayout(new BorderLayout());
      
    //Notre textearea avec son contenu déplaçable
    JTextArea label = new JTextArea("Texte déplaçable !");
    label.setPreferredSize(new Dimension(300, 130));
    //--------------------------------------------------
    //C'est cette instruction qui permet le déplacement de son contenu
    label.setDragEnabled(true);
    //--------------------------------------------------
      
    pan.add(new JScrollPane(label), BorderLayout.NORTH);
      
    JPanel pan2 = new JPanel();
    pan2.setBackground(Color.white);
    pan2.setLayout(new BorderLayout());
      
    //On crée le premier textfield avec contenu déplaçable
    JTextField text = new JTextField();
    //--------------------------------------------------
    text.setDragEnabled(true);
    //--------------------------------------------------
    //Et le second, sans
    JTextField text2 = new JTextField();
      
    pan2.add(text2, BorderLayout.SOUTH);
    pan2.add(text, BorderLayout.NORTH);
      
    pan.add(pan2, BorderLayout.SOUTH);
    add(pan, BorderLayout.CENTER);
      
    setVisible(true);
  }

  public static void main(String[] args){
    //new Test1();
      
      JFrame f=new JFrame();
      JPanel p=new JPanel();
      JButton jb=new JButton("plop");
      jb.setDropTarget(null);
      
  }  
}
