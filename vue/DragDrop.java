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
import controleur.MenuDroiteControl;
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
import model.PouvoirBeta;

public class DragDrop implements DragGestureListener, DragSourceListener,
DropTargetListener, Transferable {

	static final DataFlavor[] supportedFlavors = {null};
	private static boolean dragEnable = false;
        MenuDroiteControl m=new MenuDroiteControl();

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

	public static boolean isDragEnable() {
		return dragEnable;
	}

	public static void setDragEnable(boolean dragEnable) {
		DragDrop.dragEnable = dragEnable;
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

			if (((DropTarget) target).getComponent() instanceof JPPileChantier) {
				if (this.dragEnable) { // test si on a clicker sur l'action jouer des cartes
					if (Main.getJeu().getListeJoueur().getJoueur().getDefausse() == 0) { // check si je le joueur doit pas de défausser
						JBCarte jbCarte = (JBCarte) component;
						JPPileChantier chantier = (JPPileChantier) container;
						// check si carte constructible - check si chantier constructible - check si pose 2 carte sur le même chantier même tour
						if (jbCarte.getCarte().getClass() == Constructible.class && chantier.isPosable() && !chantier.isCarte2()) {
							Constructible carte = (Constructible) jbCarte.getCarte();
							// check 2 carte de la même couleur pour la defausse
							if (Main.getJeu().getListeJoueur().getJoueur().nb_carte_couleur(carte.getCouleur()) > 1 || Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("School") >= 1 || Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("Wren") >= 1) {

								int rep = JOptionPane.showConfirmDialog(Main.getJeu().getFrame(),
										"Êtes-vous sûr de vouloir construire cette carte ? Cela vous coutera " + carte.getCoutPose() + " pièces",
										"Construire",
										JOptionPane.YES_NO_OPTION);
								// le joueur veut poser sa carte
								if (rep == JOptionPane.YES_OPTION) {
									// si le joueur a assez d'argent
									if (carte.getCoutPose() <= Main.getJeu().getListeJoueur().getJoueur().getArgent()) {
										// creer un chantier pour le joueur si pas deja créer
										if (Main.getJeu().getListeJoueur().getJoueur().getListeChantier().size() <= chantier.getIndex()) {
											Main.getJeu().getListeJoueur().getJoueur().nouveauChantier();
										} else {
											/* affichage de la dernière carte posée si plusieurs cartes sur le chantier*/
											container.removeAll();
											container.validate();
											container.repaint();
										}

										chantier.setCarte2(true);
										/*Ajout de la carte visuellement*/
										jbCarte.changeTailleBoutonImage(new Dimension(122, 168));
										container.add(jbCarte);
										oldContainer.validate();
										oldContainer.repaint();
										container.validate();
										container.repaint();

										// change le statut de la carte
										jbCarte.setPosition("construction");

										//London.getListeJoueur().getJoueur().setFinitTour(true);

										//System.out.println(JBcarte.getCarte().getCouleur());
										//System.out.println("index du chantier : " + chantier.getIndex());
										m.disableAll();
										//London.getMenudroite().setTrueDefausseColor(carte.getCouleur());
										//London.getListeJoueur().getJoueur().setPiocheDefausse("defausse");
										//JBCarte.setDoubleClick(true);
										if (Main.getJeu().getListeJoueur().getJoueur().getPouvoir().get("Wren") != 1) {
											Main.getJeu().getMenudroite().getLabelInfo().setText("Défaussez une carte de la même couleur");
                                                                                        
										}
                                                                                else
                                                                                {
                                                                                    m.setFinTour();
                                                                                }

										/*appel de jouerCarte*/
										Main.getJeu().getListeJoueur().getJoueur().jouerCarte2(jbCarte.getCarte(), chantier.getIndex());
										//System.out.println(Main.getJeu().getListeJoueur().getJoueur().getDefausse());


										/*Passer le chantier suivant a posable=true*/
										Main.getJeu().getJpChantier().getChantiers()[chantier.getIndex() + 1].setPosable(true);

										/*Mise a jour du panel d'information*/
										Main.getJeu().getInfos().maj_infos();
										// refresh
										Main.getJeu().getSouth().repaint();
										Main.getJeu().getSouth().revalidate();

									} else {
										JOptionPane.showMessageDialog(null, "Vous n'avez pas assez d'argent pour poser cette carte");
									}
								}
							} else {
								JOptionPane.showMessageDialog(null, "Vous ne pouvez pas jouer cette carte car vous n'avez aucune autre carte de la même couleur");
							}

						} else {
							// informe le joueur qui joue
							JOptionPane.showMessageDialog(null, "Vous ne pouvez pas jouer cette carte");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Vous devez vous défaussez d'une carte de la même couleur");
					}
				} else {
					if(Main.getJeu().getListeJoueur().getJoueur().getPioche()==0){
						JOptionPane.showMessageDialog(null, "Vous devez choisir l'action 'Jouer des cartes'");
					}else{
	            		JOptionPane.showMessageDialog(null, "Vous devez piocher");
	            	}
				}
			}
		} catch (Exception ex) {
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
