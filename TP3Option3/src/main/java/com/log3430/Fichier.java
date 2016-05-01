package com.log3430;

/**
 * Class Fichier Classe représentant un fichier ou un dossier, dépendamment de
 * sa nature
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */
public class Fichier {

	private String nom_;
	private String pathParents_;
	private String folderOrFile_;

	/**
	 * Contructeur
	 * 
	 * @param nom
	 *            Nom du fichier
	 * @param pathParents
	 *            Chemin vers fichier
	 * @param folderORFile
	 *            Décrit si c'est un dossier ou un fichier
	 */
	Fichier(String nom, String pathParents, String folderORFile) {
		nom_ = nom;
		pathParents_ = pathParents;
		folderOrFile_ = folderORFile;
	}

	/**
	 * Fonction pour obtenir le nom du fichier
	 * 
	 * @return String
	 */
	public String getNom() {
		return nom_;
	}

	/**
	 * Fonction pour obtenir le chemin du parent
	 * 
	 * @return
	 */
	public String getCheminParents() {
		return pathParents_;
	}

	/**
	 * Fonction pour savoir si c'est un dossier ou fichier
	 * 
	 * @return
	 */
	public String getDossierOuFichier() {
		return folderOrFile_;
	}

}
