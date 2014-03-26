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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Anh-Djuy
 */
public class JPMain extends JPanel {
    
    private JScrollPane JSPMain;
    private JPanel main;
    
    public JPMain(){
        super();
        JSPMain = new JScrollPane();
        main = new JPanel(new FlowLayout(20));
        main.setBackground(Color.red);
        this.setPreferredSize(new Dimension(820, 308));
        JSPMain.add(main);
        this.add(JSPMain, BorderLayout.NORTH);
    }
    
    
}
