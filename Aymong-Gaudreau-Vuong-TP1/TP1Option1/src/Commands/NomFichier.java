package Commands;

import java.io.File;

public class NomFichier extends CommandeAbstraite {
	
	/**
	 * Constructeur de la classe NomFichier
	 * @param chemin
	 */
	public NomFichier(String chemin) {
		super(chemin); //generer automatiquement par eclipse
		nom_ = "Nom du fichier";
	}
	
	/**
	 * Verifie si le chemin est valide, cest a dire qu'il n'est pas null et pas vide
	 * et verifie que le noeud est bien un type FICHIER
	 * @return boolean
	 */
	@Override
	public boolean isValid(){
		if(chemin_ == null || chemin_.isEmpty())
			return false;
		
		if(getType() == Type.FICHIER)
			return true;
		
		return false;
	}
	
	/**
	 * Retourne le nom du fichier correspondant au noeud selectionne
	 */
	@Override
	public String executerCommande() throws Exception{
		if(!isValid())
			throw new Exception("Erreur, pas un fichier");
		
		int index = chemin_.lastIndexOf("\\");
		String cheminAffichage = chemin_;
		if(index != -1)
			cheminAffichage = chemin_.substring(index+1, chemin_.length());
		
		setAffichage(cheminAffichage);
		
		return getAffichage();
	}

}
