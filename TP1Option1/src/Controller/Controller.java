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
        	model.clearTree();
        
        else if("browseButton".equals(e.getActionCommand()))
        	model.rootTree();
        
        //Vérification de la commande. On reti
        else if("commandButton".equals(e.getActionCommand().substring(0, e.getActionCommand().length()-1)))
        	model.command(e.getActionCommand().charAt(e.getActionCommand().length()-1));
    }
}
