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
        	model.rootTree();
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

}
