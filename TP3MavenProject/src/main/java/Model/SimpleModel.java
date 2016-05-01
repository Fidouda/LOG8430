package Model;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import ClassLoader.ClassLoader;
import Commands.CommandeAbstraite;
import Controller.Controller;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Class SimpleModel Classe qui sert de model à l'interface.
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */

public class SimpleModel extends Observable {

	private javax.swing.JTree tree;
	private String selectedItem;
	private File selectedNode_;
	private int commandClicked_;
	private ArrayList<String> commandResults_;
	private ArrayList<Boolean> commandEnable_;
	private ArrayList<CommandeAbstraite> listeCommandes_;

	public SimpleModel() throws ClassNotFoundException {
		commandResults_ = new ArrayList<String>();
		commandEnable_ = new ArrayList<Boolean>();
	}

	/**
	 * Methode d'acces a l'attribut tree
	 * 
	 * @return tree
	 */
	public JTree getArbre() {
		return tree;
	}

	/**
	 * Methode de modification de l'attribut tree Notifie les observateur du
	 * changement de l'attribut
	 * 
	 * @param treeToSet
	 */
	public void setArbre(JTree treeToSet) {
		tree = treeToSet;
		setChanged();
		notifyObservers("Create Tree");
	}

	/**
	 * Notifie les observateur qu'il faut vider l'arbre
	 */
	public void clearArbre() {
		setChanged();
		notifyObservers("Clear Tree");
	}

	/**
	 * Notifie les observateur que la racine de l'arbre a changer
	 */
	public void rootArbre() {
		setChanged();
		notifyObservers("Root Tree");
	}

	/**
	 * Itinialise la liste de commandes disponibles
	 * 
	 * @param listeCommandes
	 */
	public void initialiserListeCommande(ArrayList<CommandeAbstraite> listeCommandes) {
		listeCommandes_ = listeCommandes;
	}

	/**
	 * Initialise la liste de r�sultats des commandes
	 * 
	 * @param size
	 */
	public void initialiserResultatsCommande(int size) {
		for (Integer i = 0; i < size; i++) {
			commandResults_.add("");
		}
	}

	/**
	 * Initialise la liste de commandes actives
	 * 
	 * @param size
	 */
	public void initialiserActivationCommande(int size) {
		for (Integer i = 0; i < size; i++) {
			commandEnable_.add(false);
		}
	}

	/**
	 * Methode d'acces pour l'attribut selectedItem
	 * 
	 * @return selectedItem
	 */
	public String getSelectionItem() {
		return selectedItem;
	}

	/**
	 * Methode d'acces pour l'attribut selectedNode_
	 * 
	 * @return
	 */
	public File getSelectionNoeud() {
		return selectedNode_;
	}

	/**
	 * Genere le JTree avec comme racine le fichier/repertoir selectionne dans
	 * le JChooser Notifie les observateur qu'un l'arbre a ete generer
	 * 
	 * @param racine
	 */
	public void genererArbre(File racine) {
		selectedItem = "";
		final JTree treeTemp = new JTree(new DefaultTreeModel(getTree(null, racine)));
		treeTemp.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				if (treeTemp.getSelectionPath() == null)
					return;

				String selectedNodeWPath = "";

				for (Object part : treeTemp.getSelectionPath().getPath())
					selectedNodeWPath += "\\" + part.toString();

				selectedNodeWPath = selectedNodeWPath.substring(1);

				selectedItem = selectedNodeWPath;

				selectedNode_ = new File(selectedItem);

				setChanged();
				notifyObservers("Update Commands");
			}
		});
		tree = treeTemp;
		setChanged();
		notifyObservers("Update Tree");
	}

	/**
	 * Retourne le numero de la commande clique par lutilisateur a la vue
	 * 
	 * @return commandClicked_
	 */
	public int getCommand() {
		return commandClicked_;
	}

	/**
	 * Retourne la valeur � l'index de commandResults_
	 * 
	 * @param index
	 */
	public String getResultatsCommande(int index) {
		return commandResults_.get(index);
	}

	/**
	 * Mettre � jour la liste de r�ponse des commandes
	 * 
	 * @param index
	 * @param result
	 */
	public void setResultatsCommande(int index, String result) {
		commandResults_.set(index, result);
		setChanged();
		notifyObservers("Command");
	}

	/**
	 * Retourne la valeur � l'index de commandEnable_
	 * 
	 * @param index
	 */
	public Boolean getCommandeValide(int index) {
		return commandEnable_.get(index);
	}

	/**
	 * Mettre � jour la liste de r�ponse des commandes
	 * 
	 * @param index
	 * @param result
	 */
	public void setCommandeValide(int index, Boolean result) {
		commandEnable_.set(index, result);
	}

	/**
	 * Genere l'arbre Tir� de:
	 * http://www.java2s.com/Code/Java/File-Input-Output/
	 * DisplayafilesysteminaJTreeview.htm
	 * 
	 * @param top
	 * @param repertoire
	 * @return
	 */
	private DefaultMutableTreeNode getTree(DefaultMutableTreeNode top, File repertoire) {
		DefaultMutableTreeNode currentNode;

		// Si top est null, c'est le root
		if (top != null) {
			currentNode = new DefaultMutableTreeNode(repertoire.getName());
			top.add(currentNode);
		} else
			currentNode = new DefaultMutableTreeNode(repertoire.getAbsolutePath());

		if (!repertoire.isDirectory())
			return currentNode;

		if (repertoire.listFiles() != null) {
			for (File fichier : repertoire.listFiles()) {
				if (fichier.canRead())
					getTree(currentNode, fichier);
			}
		}

		return currentNode;
	}

	public ArrayList<CommandeAbstraite> getListeCommande() {
		return listeCommandes_;
	}
}
