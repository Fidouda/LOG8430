package Model;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.util.Observable;

public class SimpleModel extends Observable {
	
	private javax.swing.JTree tree;
	private String selectedItem;
	private File selectedNode_;
	private int commandClicked_;
	
	public SimpleModel() throws ClassNotFoundException {
	}

	/**
	 * Methode d'acces a l'attribut tree
	 * @return tree
	 */
	public JTree getTree(){
		return tree;
	}
	
	/**
	 * Methode de modification de l'attribut tree
	 * Notifie les observateur du changement de l'attribut
	 * @param treeToSet
	 */
	public void setTree(JTree treeToSet){
		tree = treeToSet;
		setChanged();
		notifyObservers("Create Tree");
	}
	
	/**
	 * Notifie les observateur qu'il faut vider l'arbre
	 */
	public void clearTree(){
		setChanged();
		notifyObservers("Clear Tree");
	}
	
	/**
	 * Notifie les observateur que la racine de l'arbre a changer
	 */
	public void rootTree(){
		setChanged();
		notifyObservers("Root Tree");
	}
	
	/**
	 * Methode d'acces pour l'attribut selectedItem
	 * @return selectedItem
	 */
	public String getSelectedItem(){
		return selectedItem;
	}
	
	/**
	 * Methode d'acces pour l'attribut selectedNode_
	 * @return
	 */
	public File getSelectedNode(){
		return selectedNode_;
	}
	
	/**
	 * Genere le JTree avec comme racine le fichier/repertoir selectionne dans le JChooser
	 * Notifie les observateur qu'un l'arbre a ete generer
	 * @param racine
	 */
	public void genererArbre(File racine){
		final JTree treeTemp = new JTree(new DefaultTreeModel(getTree(null, racine)));
				treeTemp.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				if(treeTemp.getSelectionPath() == null)
					return;

				String selectedNodeWPath = "";
				
				for(Object part : treeTemp.getSelectionPath().getPath())
					selectedNodeWPath += "\\"+part.toString();
				
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
	 * On recoit le numero de la commande clique par l<utilisateur.
	 * Cette methode est appel/ a partir du controlleur.
	 * Elle permet a la vue de savoir quelle classe de commande utiliser
	 * @param numero
	 */
	public void command(char numero)
	{
		commandClicked_ = Character.getNumericValue(numero);
		setChanged();
		notifyObservers("Command");
	}
	
	/**
	 * Retourne le numero de la commande clique par lutilisateur a la vue
	 * @return commandClicked_
	 */
	public int getCommand()
	{
		return commandClicked_;
	}
	
	/**
	 * Genere l'arbre
	 * Tiré de: http://www.java2s.com/Code/Java/File-Input-Output/DisplayafilesysteminaJTreeview.htm
	 * @param top
	 * @param repertoire
	 * @return
	 */
	private DefaultMutableTreeNode getTree(DefaultMutableTreeNode top, File repertoire){
		DefaultMutableTreeNode currentNode;
		
		//Si top est null, c'est le root
		if(top != null){
			currentNode = new DefaultMutableTreeNode(repertoire.getName());
			top.add(currentNode);
		}
		else
			currentNode = new DefaultMutableTreeNode(repertoire.getAbsolutePath());
		
		if(!repertoire.isDirectory())
			return currentNode;
		
		if(repertoire.listFiles() != null){
			for(File fichier : repertoire.listFiles()){
				if(fichier.canRead())
					getTree(currentNode, fichier);
			}
		}
		
		return currentNode;
	}
		
	
}
