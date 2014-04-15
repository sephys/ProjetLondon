/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vue;

/**
 *
 * @author Joke
 */

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Constructible;

public class DragDrop implements DragGestureListener, DragSourceListener,
        DropTargetListener, Transferable {

    static final DataFlavor[] supportedFlavors = {null};
    private boolean dragEnable = false;
    
    static {
        try {
            supportedFlavors[0] = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    Object object;
    // Transferable methods.
    public Object getTransferData(DataFlavor flavor) {
        if (flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType)) {
            return object;
        } else {
            return null;
        }
    }

    public boolean isDragEnable() {
        return dragEnable;
    }

    public void setDragEnable(boolean dragEnable) {
        this.dragEnable = dragEnable;
    }
    
    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType);
    }
    // DragGestureListener method.
    public void dragGestureRecognized(DragGestureEvent ev) {
        ev.startDrag(null, this, this);
    }
    // DragSourceListener methods.
    public void dragDropEnd(DragSourceDropEvent ev) {
    }

    public void dragEnter(DragSourceDragEvent ev) {
    }

    public void dragExit(DragSourceEvent ev) {
    }

    public void dragOver(DragSourceDragEvent ev) {
        object = ev.getSource();
    }

    public void dropActionChanged(DragSourceDragEvent ev) {
    }
    // DropTargetListener methods.
    public void dragEnter(DropTargetDragEvent ev) {
    }

    public void dragExit(DropTargetEvent ev) {
    }

    public void dragOver(DropTargetDragEvent ev) {
        dropTargetDrag(ev);
    }

    public void dropActionChanged(DropTargetDragEvent ev) {
        dropTargetDrag(ev);
    }

    void dropTargetDrag(DropTargetDragEvent ev) {
        ev.acceptDrag(ev.getDropAction());
    }

    public void drop(DropTargetDropEvent ev) {
        ev.acceptDrop(ev.getDropAction());
        try {
            Object target = ev.getSource();
            Object source = ev.getTransferable().getTransferData(supportedFlavors[0]);
            Component component = ((DragSourceContext) source).getComponent();
            Container oldContainer = component.getParent();
            Container container = (Container) ((DropTarget) target).getComponent();
            
             JBCarte JBcarte = (JBCarte) component;
             JPPileChantier chantier = (JPPileChantier) container;
             if(this.dragEnable){
                if(JBcarte.getCarte().getClass()==Constructible.class && chantier.isPosable() && !chantier.isCarte2() && (((DropTarget) target).getComponent() instanceof JPPileChantier)){
                    Constructible carte = (Constructible) JBcarte.getCarte();
                    int rep = JOptionPane.showConfirmDialog(London.acc,
                        "Êtes-vous sûr de vouloir construire cette carte ? Cela vous coutera "+carte.getCoutPose()+" pièces",
                        "Construire",
                        JOptionPane.YES_NO_OPTION);
                    if (rep == JOptionPane.YES_OPTION) {
                         if(!London.getListeJoueur().getJoueur().isPayeConstruction()){
                            if(carte.getCoutPose()<London.getListeJoueur().getJoueur().getArgent()){
                                    if(London.getListeJoueur().getJoueur().getListeChantier().size()<=chantier.getIndex()){
                                        London.getListeJoueur().getJoueur().nouveauChantier();
                                    }
                                    else{
                                        container.removeAll();
                                        container.validate();
                                        container.repaint();
                                    }

                                        chantier.setCarte2(true);
                                        /*Ajout de la carte visuellement*/
                                            container.add(component);
                                            oldContainer.validate();
                                            oldContainer.repaint();
                                            container.validate();
                                            container.repaint();

                                            System.out.println(JBcarte.getCarte().getCouleur());
                                            System.out.println("index du chantier : "+chantier.getIndex());

                                            /*appel de jouerCarte*/
                                            London.getListeJoueur().getJoueur().jouerCarte(null, JBcarte.getCarte(), chantier.getIndex());

                                            /*Passer le chantier suivant a posable=true*/
                                            London.getJpChantier().getChantiers()[chantier.getIndex()+1].setPosable(true);

                                            /*Mise a jour du panel d'information*/
                                            London.infos.maj_infos();



                                            /*Le joueur peut finir son tour*/
                                            London.getListeJoueur().getJoueur().setFinitTour(true);

                                             /*Le joueur peut finir son tour*/
                                            London.getListeJoueur().getJoueur().setPayeConstruction(true);

                                            London.getListeJoueur().getJoueur().setDefausse(1);
                                            London.getMenudroite().disableAll();
                                            London.getMenudroite().setTrueDefausseColor(carte.getCouleur());
                                    }
                                    else{
                                           JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent pour poser cette carte");
                                       }
                         }else{
                                JOptionPane.showMessageDialog(null, "Vous devez vous défaussez d'une carte de la même couleur");
                            }
                    }
                }
                else{
                     // informe le joueur qui joue
                    JOptionPane.showMessageDialog(null, "Vous ne pouvez pas jouer cette carte");
                }
             }
             else{
                 JOptionPane.showMessageDialog(null, "Vous devez choisir l'action 'Jouer des cartes'");
             }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        ev.dropComplete(true);
            
    }
    
    

    public static void main(String[] arg) {
        JButton button = new JButton("Drag this button");
        JLabel label = new JLabel("Drag this label");
        Checkbox checkbox = new Checkbox("Drag this check box");
        CheckboxGroup radiobutton = new CheckboxGroup();
        Checkbox checkbox1 = new Checkbox("Drag this check box", radiobutton, false);
        Choice country = new Choice();

        // adding possible choices
        country.add("India");
        country.add("US");
        country.add("Australia");

        JFrame source = new JFrame("Source Frame");
        source.setLayout(new FlowLayout());
        source.add(button);
        source.add(label);
        source.add(checkbox);
        source.add(checkbox1);
        source.add(country);

        JFrame target = new JFrame("Target Frame");
        target.setLayout(new FlowLayout());

        DragDrop dndListener = new DragDrop();

        DragSource dragSource = new DragSource();
        DropTarget dropTarget1 = new DropTarget(source, DnDConstants.ACTION_MOVE,
                dndListener);
        DropTarget dropTarget2 = new DropTarget(target, DnDConstants.ACTION_MOVE, 
                dndListener);
        DragGestureRecognizer dragRecognizer1 = dragSource.
                createDefaultDragGestureRecognizer(button, DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer2 = dragSource.
                createDefaultDragGestureRecognizer(label, DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer3 = dragSource.
                createDefaultDragGestureRecognizer(checkbox, DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer4 = dragSource.
                createDefaultDragGestureRecognizer(checkbox1, DnDConstants.ACTION_MOVE, dndListener);
        DragGestureRecognizer dragRecognizer5 = dragSource.
                createDefaultDragGestureRecognizer(country, DnDConstants.ACTION_MOVE, dndListener);

        source.setBounds(0, 200, 200, 200);
        target.setBounds(220, 200, 200, 200);
        source.show();
        target.show();
    }
}
