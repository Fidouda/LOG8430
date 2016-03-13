package Commands;

import java.io.File;

public class CheminAbsolu extends CommandeAbstraite{

	/**
	 * Constructeur de la classe CheminAbsolu
	 */
	public CheminAbsolu(){
		super();
		nom_ = "Chemin absolu";
	}
	
	/**
	 * Verifie si le chemin est valide, cest a dire qu'il n'est pas null et pas vide
	 */
	@Override
	public boolean isValid() {
		if(chemin_ == null || chemin_.isEmpty())
			return false;
		return true;
	}

	/**
	 * Retourne le chemin absolu du fichier/repertoir selectionne et change l'attribut affichage_
	 * @return affichage_
	 */
	@Override
	public String executerCommande() throws Exception {
		if (!isValid()) {
			throw new Exception("Erreur, chemin null");
		}
		setAffichage(chemin_);
		return getAffichage();
	}

}
