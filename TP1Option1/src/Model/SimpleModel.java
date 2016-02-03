package Model;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import ClassLoader.GestionnaireCommande;

import java.util.Observable;

public class SimpleModel extends Observable {
	
	private javax.swing.JTree tree;
	private GestionnaireCommande gestionnaireCommandes = new GestionnaireCommande();
	private String selectedItem;
	
	public SimpleModel() throws ClassNotFoundException {
	}

	public JTree getTree(){
		return tree;
	}
	
	public void setTree(JTree treeToSet){
		tree = treeToSet;
		setChanged();
		notifyObservers("Create Tree");
	}
	//Vide l'arbre
	public void clearTree(){
		tree = new JTree();
		setChanged();
		notifyObservers("Clear Tree");
	}
	
	public void rootTree(){
		setChanged();
		notifyObservers("Root Tree");
	}
	
	public String getSelectedItem(){
		return selectedItem;
	}
	
	public void genererArbre(File racine){

		final JTree treeTemp = new JTree(new DefaultTreeModel(getTree(null, racine)));
				treeTemp.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				if(treeTemp.getSelectionPath() == null)
					return;
				
				String selectedNodeWPath = "";
				
				for(Object part : treeTemp.getSelectionPath().getPath())
					selectedNodeWPath += part.toString();
				
				System.out.println(selectedNodeWPath);
				selectedItem = selectedNodeWPath;
			}
		});
				
		tree = treeTemp;	
		setChanged();
		notifyObservers("Update Tree");
	}
	
	public void command1()
	{
		
		setChanged();
		notifyObservers("Update Tree");
	}
	
	//http://www.java2s.com/Code/Java/File-Input-Output/DisplayafilesysteminaJTreeview.htm
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
