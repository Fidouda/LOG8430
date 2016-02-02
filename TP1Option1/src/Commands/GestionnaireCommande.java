package Commands;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class GestionnaireCommande {
	
	private List<InterfaceCommande> listeCommande = new Vector<InterfaceCommande>();
	
	public List<InterfaceCommande> getListeCommande() {
		return listeCommande;
	}
	
	public boolean ajoutCommande(InterfaceCommande commande)
	{
		if(listeCommande != null)
		{
			listeCommande.add(commande);
			return true;
		}
		return false;
	}
	
	public void executeCommands(){
		for(InterfaceCommande command : listeCommande){
			command.faireCommande();
		}
	}
	
	public void clearCommandResults() {
		for(InterfaceCommande command: listeCommande){
			command.clearCommand();
		}
	}

}
