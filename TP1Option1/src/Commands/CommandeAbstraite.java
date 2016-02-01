package Commands;

import java.io.File;
import java.util.Observable;

public abstract class CommandeAbstraite extends Observable {
	
	private String affichage_;
	protected String nom_;
	protected String chemin_;
	
	public enum Type {
	    FICHIER, REPERTOIR, AUTRE
	}

	private void notifierObservateur(){
		setChanged();
		notifyObservers();
	}
	
	public CommandeAbstraite(String chemin){
		chemin_ = chemin;
	}
	
	public void clear(){
		affichage_ = "";
		notifierObservateur();
	}
	
	public void setAffichage(String affichage){
		affichage_ = affichage;
		notifierObservateur();
	}
	
	public String getNom(){
		return nom_;
	}
	
	public String getChemin(){
		return chemin_;
	}
	
	public String getAffichage(){
		return affichage_;
	}
	
	protected Type getType(){
		File fichier = new File(chemin_);
		Type type;
		
		if(fichier.exists())
			type = Type.REPERTOIR;
		else if (fichier.isFile())
			type = Type.FICHIER;
		else
			type = Type.AUTRE;
		
		return type;
	}
	
	public void setChemin(String chemin){
		chemin_ = chemin;
		clear();
	}
	
	public abstract boolean isValid();
	public abstract String executerCommande() throws Exception;
}
