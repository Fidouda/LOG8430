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

	/**
	 * Notifie les observateurs
	 */
	private void notifierObservateur(){
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Constructeur de la classe CommandeAbstraite
	 * @param chemin
	 */
	public CommandeAbstraite(String chemin){
		chemin_ = chemin;
	}
	
	/**
	 * Efface l'affichage et notifie les observateurs
	 */
	public void clear(){
		affichage_ = "";
		notifierObservateur();
	}
	
	/**
	 * Fonction de modification pour l'attribut affichage_
	 * @param affichage
	 */
	public void setAffichage(String affichage){
		affichage_ = affichage;
		notifierObservateur();
	}
	
	/**
	 * Fonction d'acces pour l'attribut nom_
	 * @return nom_
	 */
	public String getNom(){
		return nom_;
	}
	
	/**
	 * Fonction d'acces pour l'attribut chemin_
	 * @return chemin_
	 */
	public String getChemin(){
		return chemin_;
	}
	
	/**
	 * Fonction d'acces pour l'attribut affichage_
	 * @return
	 */
	public String getAffichage(){
		return affichage_;
	}
	
	/**
	 * Assigne le bon type pour le noeud selectionne
	 * @return type
	 */
	public Type getType(){
		File fichier = new File(chemin_);
		Type type;
	
		if(fichier.isDirectory())
			type = Type.REPERTOIR;
		
		else if (fichier.isFile())
			type = Type.FICHIER;
		else
			type = Type.AUTRE;
		
		return type;
	}
	
	/**
	 * Fonction de modification pour l'attribut chemin_, appelle la fonction clear()
	 * @param chemin
	 */
	public void setChemin(String chemin){
		chemin_ = chemin;
		clear();
	}
	
	/**
	 * Fonction abstraite de verification de type
	 * @return boolean
	 */
	public abstract boolean isValid();
	
	/**
	 * Fonction abstraite d'execution de commande 
	 * @return String
	 * @throws Exception
	 */
	public abstract String executerCommande() throws Exception;
}
