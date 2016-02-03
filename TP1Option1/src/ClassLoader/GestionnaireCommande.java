package ClassLoader;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Commands.InterfaceCommande;
import Commands.CommandeAbstraite;

public class GestionnaireCommande {
	
	private List<CommandeAbstraite> listeCommande = new ArrayList<CommandeAbstraite>();
	
	public List<CommandeAbstraite> getListeCommande() {
		return listeCommande;
	}
	
	public boolean ajoutCommande(CommandeAbstraite commande)
	{
		if(listeCommande != null)
		{
			listeCommande.add(commande);
			return true;
		}
		return false;
	}
	
	public void executeCommands(){
		for(CommandeAbstraite command : listeCommande){
			command.faireCommande();
		}
	}
	
	public void clearCommandResults() {
		for(CommandeAbstraite command: listeCommande){
			command.clearCommand();
		}
	}
	
	public void setCommandList(List<CommandeAbstraite> commandList) {
		listeCommande = commandList;
	}

}
