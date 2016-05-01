package com.log3430;

import java.util.Vector;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * Class SampleController Classe s'occupant de gérer les requêtes HTTP provenant
 * du client
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */
@Controller
@EnableAutoConfiguration
public class SampleController {

	/**
	 * Retourne la liste de fichiers et dossiers du répertoire root
	 * 
	 * @return String avec les fichiers et dossiers
	 */
	@RequestMapping("/")
	@ResponseBody
	String home() {

		Vector<Fichier> vector = GetFilesFromRoot.retournerFichiers();
		String http = "";

		for (int i = 0; i < vector.size(); i++) {
			http += vector.get(i).getNom();
			http += "?";
			http += vector.get(i).getCheminParents();
			http += "?";
			http += vector.get(i).getDossierOuFichier();
			if (i != vector.size() - 1)
				http += "?";
		}

		return http;
	}
}
