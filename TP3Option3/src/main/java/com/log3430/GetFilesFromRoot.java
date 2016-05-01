package com.log3430;

import java.io.File;
import java.util.Vector;

/**
 * Class GetFilesFromRoot Classe effectuant la gestion de récupération des
 * informations sur les fichiers et dossiers
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */
public class GetFilesFromRoot {

	private static Vector<Fichier> vector;

	/**
	 * Retourne un vecteur contenant les fichiers et dossiers
	 * 
	 * @return Vector<Fichiers>
	 */
	static public Vector<Fichier> retournerFichiers() {

		vector = new Vector<Fichier>();

		getFichiersDuDossier("root");

		return vector;
	}

	/**
	 * Ajoute un fichier ou dossier au vecteur
	 * 
	 * @param path
	 *            Chemin du dossier en cours d'analyse récursive
	 */
	private static void getFichiersDuDossier(String path) {

		File folder = new File(path);

		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				vector.addElement(new Fichier(fileEntry.getName(), path, "Folder"));
				getFichiersDuDossier(path + "/" + fileEntry.getName());
			} else {
				vector.addElement(new Fichier(fileEntry.getName(), path, "Files"));
			}

		}
	}

}
