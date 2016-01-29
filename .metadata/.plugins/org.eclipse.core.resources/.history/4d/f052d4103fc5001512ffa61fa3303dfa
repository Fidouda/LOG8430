package Controller;
import View.GraphicUserInterface;
import Model.SimpleModel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


public class Controller implements ActionListener {
	
	private SimpleModel model;
	private GraphicUserInterface window;
	private File root;

	public Controller(SimpleModel modelToSet){
		model = modelToSet;
	}
	
	
   public void actionPerformed(ActionEvent e)
    {
	   
        if("clearButton".equals(e.getActionCommand()))
        {
        	model.clearTree();
        }
        else if("browseButton".equals(e.getActionCommand()))
        {
    		JFileChooser chooser = new JFileChooser();
    		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //Permet la selection de file et folder
    		chooser.setCurrentDirectory(new java.io.File("C:\\"));
    		 int fileValue = chooser.showSaveDialog(null);
    		    if(fileValue == JFileChooser.APPROVE_OPTION){
    		    	root = chooser.getSelectedFile();
    		    	System.out.println(root.getAbsolutePath());
    		    	genererArbre(root);
    		    }
        }
    }

   
	private void genererArbre(File racine){

		final JTree treeTemp = new JTree(new DefaultTreeModel(getTree(null, racine)));
				treeTemp.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				if(treeTemp.getSelectionPath() == null)
					return;
				
				String selectedNodeWPath = "";
				
				for(Object part : treeTemp.getSelectionPath().getPath())
					selectedNodeWPath += part.toString();
				
				System.out.println(selectedNodeWPath);
			}
		});
				
		model.setTree(treeTemp);				
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
	
	public GraphicUserInterface getWindow(){
		return window;
	}
	
	public SimpleModel getModel(){
		return model;
	}
	
	public void setWindow(GraphicUserInterface windowToSet){
		window = windowToSet;
	}
	
	public void setModel(SimpleModel modelToSet){
		model = modelToSet;
	}
	
	//action bouton fichier, boutton dans view, evenement va appeler fonction ici
}
