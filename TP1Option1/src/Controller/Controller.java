package Controller;
import View.GraphicUserInterface;
import Model.SimpleModel;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import java.util.Observable;
import java.util.Observer;
public class Controller implements Observer {
	
	private SimpleModel model;
	private GraphicUserInterface window;

	public Controller(SimpleModel modelSet, GraphicUserInterface windowSet ) {
		model = modelSet;
		window = windowSet;
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
		window.getScrollPane().setViewportView(model.getTree());
		window.getScrollPane().repaint();
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

	@Override
	public void update(Observable arg0, Object arg1) {
		
		switch(arg1.toString()){
		
		case "Clear tree": 
			//model.setTree(new Jtree); getTree()
			JFrame frameTemp =  window.getFrame();
			frameTemp.getContentPane().remove(window.getScrollPane());
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(15, 44, 207, 325);
			frameTemp.getContentPane().add(scrollPane);
			scrollPane.repaint();
			
			window.setFrame(frameTemp);
			
			break;
		}
	
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
