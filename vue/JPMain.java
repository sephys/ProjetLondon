/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *  
 * @author Anh-Djuy
 * 
 * Classe JPMain
 * 
 * Cette classe permet d'afficher la main d'un joueur.
 * 
 */
public class JPMain extends JPanel {
    
    private JScrollPane JSPMain;
    private static JPanel main;
    
    public JPMain(){
        super(new BorderLayout());
        main = new JPanel(new FlowLayout());
        main.setBackground(Color.red);
        JSPMain = new JScrollPane(main);
        JSPMain.setPreferredSize(new Dimension(675,131));
        this.add(JSPMain, BorderLayout.NORTH);
        //// D&D
        DropTarget dropTarget1 = new DropTarget(main, DnDConstants.ACTION_MOVE,
                London.dndListener);
    }
    
    // ajout d'une carte dans le panel
    public static void ajoutCarte(){
       
            
            main.add(new JBCarte("../img/cartes/CoventGarden.png"));
            main.revalidate();
      
    }
}
