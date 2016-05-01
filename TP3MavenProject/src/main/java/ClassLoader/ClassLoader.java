package ClassLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;

import sun.misc.Launcher;
import Commands.CommandeAbstraite;

/**
 * Class ClassLoader Charge des classes en .java dynamiquement Tutoriel suivi
 * sur
 * http://www.javaworld.com/article/2077477/learn-java/java-tip-113--identify-
 * subclasses-at-runtime.html
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */

public class ClassLoader extends java.lang.ClassLoader {

	/**
	 * Nous utilisons le chargeur du class trouv� sur le site cit� plus haut
	 * pour lire le dossier "Commands" Toutes les classes h�ritant de la classe
	 * abstraite seront charg�s gr�ce � ce chargeur de classes.
	 * 
	 * @return
	 */
	public ArrayList<CommandeAbstraite> chargerCommandes() {

		// D�claration des variables pertinentes.
		// Folder "Commands"
		// ArrayList contenant les commandes qui seront charg�es et retourn�es �
		// la vue
		String nomPaquet = "Commands";
		String nom = new String(nomPaquet);
		ArrayList<CommandeAbstraite> listeDesCommandes = new ArrayList<CommandeAbstraite>();

		// Manoeuvre pour s'assurer de retourner le nom "/" + nom
		if (!nom.startsWith("/"))
			nom = "/" + nom;
		nom = nom.replace('.', '/');

		// Il �tait impossible de compiler avec l'erreur en lien avec la
		// librairie sun.misc.Launcher
		// La version n'�tait plus support� ou trop r�cente pour ma version
		// d'Eclipse.
		// J'ai donc d� modifier ma configuration de gestion d'erreur de
		// librairie
		@SuppressWarnings("restriction")
		URL url = Launcher.class.getResource(nom);
		File directory = new File(url.getFile());

		// Verification de l'existance du directory
		if (directory.exists()) {
			String[] files = directory.list();
			for (int i = 0; i < files.length; i++) {
				if (files[i].endsWith(".class")) {
					// On retire l'extension du nom de la classe
					String classname = files[i].substring(0, files[i].length() - 6);
					try {
						Object o = Class.forName(nomPaquet + "." + classname).getDeclaredConstructor(String.class)
								.newInstance(new String(""));
						// Accepter la commande seulement si c'est une instance
						// de CommandeAbstraite
						if (o instanceof CommandeAbstraite) {
							listeDesCommandes.add((CommandeAbstraite) o);
						}
					} catch (ClassNotFoundException cnfex) {
						System.err.println(cnfex);
					} catch (InstantiationException e) {
						// e.printStackTrace();
					} catch (IllegalAccessException e) {
						// e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// e.printStackTrace();
					} catch (InvocationTargetException e) {
						// e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// e.printStackTrace();
					} catch (SecurityException e) {
						// e.printStackTrace();
					}
				}
			}
		}
		return listeDesCommandes;
	}
}
