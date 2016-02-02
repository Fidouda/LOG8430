package Controller;
import Model.SimpleModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

public class Controller implements ActionListener {
	
	private SimpleModel model;

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
        ////////////////////////////////////////
        //Mauvaise utilisation du controlleur
        ////////////////////////////////////////
        else if("commandButton0".equals(e.getActionCommand()))
        {
        	model.command1();
        }
        else if("commandButton1".equals(e.getActionCommand()))
        {
        	model.command1();
        }
        else if("commandButton2".equals(e.getActionCommand()))
        {
        	model.command1();
        }
    }
}
