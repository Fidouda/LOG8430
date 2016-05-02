package Commands;

import java.io.File;

/**
 * Class NomRepertoire Classe de commande qui va chercher le nom d'un repertoir
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */

public class NomRepertoire extends CommandeAbstraite {

	/**
	 * Constructeur de la classe NomRepertoire
	 * 
	 * @param chemin
	 */
	public NomRepertoire(String chemin) {
		super(chemin); // generer automatiquement par eclipse
		nom_ = "Nom du repertoir";
	}

	/**
	 * Verifie si le chemin est valide, cest a dire qu'il n'est pas null et pas
	 * vide et verifie que le noeud est bien un type REPERTOIR
	 * 
	 * @return boolean
	 */
	@Override
	public boolean estValide() {
		if (chemin_ == null || chemin_.isEmpty())
			return false;

		if (getType() == Type.REPERTOIR)
			return true;

		return false;
	}

	/**
	 * Retourne le nom du repertoir correspondant au noeud selectionne
	 */
	@Override
	public String executerCommande() throws Exception {

		if (!estValide())
			throw new Exception("Erreur, pas un repertoir");

		int index = chemin_.lastIndexOf("\\");
		String cheminAffichage = chemin_;
		if (index >= 0)
			cheminAffichage = chemin_.substring(index + 1, chemin_.length());

		setAffichage(cheminAffichage);

		return getAffichage();
	}

}
