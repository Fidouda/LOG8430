package Controller;
import Model.SimpleModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.dropbox.core.DbxException;

import ClassLoader.ClassLoader;
import Commands.CommandeAbstraite;

import api.DropBoxAPI;

public class Controller implements ActionListener {
	
	private SimpleModel model;
	private ArrayList<CommandeAbstraite> listeCommandes_;
	private Boolean autoRunEnabled;

	/**
	 * Constructeur de la classe Controller
	 * @param modelToSet
	 */
	public Controller(SimpleModel modelToSet){
		autoRunEnabled = false;
		
		ClassLoader chargeur = new ClassLoader();
		listeCommandes_ = chargeur.chargerCommandes();
		
		model = modelToSet;
		
		model.initializeCommandResults(listeCommandes_.size());
		model.initializeCommandEnable(listeCommandes_.size());
		
	}
	
	/**
	 * Fonction qui gere les actions de clique sur les boutons correspondant aux commandes
	 */
   public void actionPerformed(ActionEvent e)
    {
        if("clearButton".equals(e.getActionCommand()))
        {
        	model.clearTree();
        	for(int j = 0 ; j < listeCommandes_.size(); j++)
    		{
    			model.setCommandResults(j, "");
    		}
        }
        
        else if("checkAutoRun".equals(e.getActionCommand()))
        {
        	//Toggle his value
        	autoRunEnabled = !autoRunEnabled;
        }
        
        else if("browseButton".equals(e.getActionCommand()))
        {
        	JFileChooser chooser = new JFileChooser();
    		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //Permet la selection de file et folder
    		chooser.setCurrentDirectory(new java.io.File("C:\\"));
    		 int fileValue = chooser.showSaveDialog(null);
    		    if(fileValue == JFileChooser.APPROVE_OPTION){
    		    	File root = chooser.getSelectedFile().getAbsoluteFile();
    		    	model.genererArbre(root);
    		    	activerToutesCommandes(chooser.getSelectedFile().toString());
    		    }
        }
        
        //V�rification de la commande. On reti
        else if("commandButton".equals(e.getActionCommand().substring(0, e.getActionCommand().length()-1))) {
        	int commandIndex = Character.getNumericValue(e.getActionCommand().charAt(e.getActionCommand().length()-1));
        	CommandeAbstraite commande = listeCommandes_.get(commandIndex);
        	System.out.println(model.getSelectedItem());
			commande.setChemin(model.getSelectedItem());
			try 
			{
				commande.executerCommande();
			} 
			catch (Exception exception) 
			{
				
			}
			model.setCommandResults(commandIndex, commande.getAffichage());
        }
        
        else if("mockUpdate".equals(e.getActionCommand()))
        {
        	if(model.getSelectedItem() != "")
        		activerToutesCommandes(model.getSelectedItem());
        }
        
        else if("dropBoxButton".equals(e.getActionCommand()))
        {
        	DropBoxAPI api = new DropBoxAPI();
        	try {
				api.getFiles("");
			} catch (DbxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        
        else if("googleButton".equals(e.getActionCommand()))
        {
        	
        }
        	
        else if("serverButton".equals(e.getActionCommand()))
        {
        	
        }
    }
   
   /**
	 * Active toutees les commandes dans la listes de commandes
	 * Met a jour l'interface selon la disponibilit� des commandes en changeant l'acces aux bouttons
	 */
	public void activerToutesCommandes(String chemin)
	{
		for(int j = 0 ; j < listeCommandes_.size(); j++)
		{
			CommandeAbstraite commande = listeCommandes_.get(j);
			commande.setChemin(chemin);
			try {
				model.setCommandResults(j, "");
				model.setCommandEnable(j, true);
				commande.executerCommande();
			} catch (Exception e) {
				model.setCommandEnable(j, false);
				continue;
			}
			if(autoRunEnabled){
				model.setCommandResults(j, commande.getAffichage());
			}
		}
	}
}
