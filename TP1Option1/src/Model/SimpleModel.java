package Model;
import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import View.GraphicUserInterface;

public class SimpleModel {
	
	private javax.swing.JTree tree;
	private GraphicUserInterface view;
	

	public SimpleModel(GraphicUserInterface View) {
		view = View;
	}

	public JTree getTree(){
		return tree;
	}
	
	public void setTree(JTree treeToSet){
		tree = treeToSet;
	}
}
