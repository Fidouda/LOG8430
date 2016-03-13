package ClassLoader;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import Commands.CommandeAbstraite;

//Tutoriel suivi sur
//http://www.javaworld.com/article/2077477/learn-java/java-tip-113--identify-subclasses-at-runtime.html
//Modifie pour permettre d'ouvrir des fichiers .jar
public class ClassLoader extends java.lang.ClassLoader {
	
	/**
	 * Nous utilisons le chargeur du class trouvé sur le site cité plus haut pour lire le dossier "Commands"
	 * Toutes les classes héritant de la classe abstraite seront chargés grâce à ce chargeur de classes.
	 * @return
	 * @throws MalformedURLException 
	 * @throws ClassNotFoundException 
	 */
	public ArrayList<CommandeAbstraite> chargerCommandes() throws MalformedURLException, ClassNotFoundException {
       
		//Déclaration des variables pertinentes.
		//Folder "Commands"
		//ArrayList contenant les commandes qui seront chargées et retournées à la vue
		String nomPaquet = "Commands";
        String nom = new String(nomPaquet);
        ArrayList<CommandeAbstraite> listeDesCommandes = new ArrayList<CommandeAbstraite>();
        
        //Manoeuvre pour s'assurer de retourner le nom "/" + nom
        if (!nom.startsWith("/"))
        	nom = "/" + nom;
        nom = nom.replace('.','/');
        
        // À REFACTORISER + OUVRIR UN FILECHOOSER
        String absolutePath = new File(".").getAbsolutePath();
        absolutePath = absolutePath.substring(0, 2);//Remove the dot at the end of path
        absolutePath = absolutePath + "/Temp" + nom + "/";
        
        // URL vers le repertoire contenant les commandes
		URL url = new URL("file", "", absolutePath);
        File directory = new File(url.getFile());
        
        //Verification de l'existance du repertoire
        if (directory.exists()) 
        {
        	//Noms de toutes les fichiers contenu dans le repertoire
            String[] files = directory.list();
            for (int i=0; i<files.length; i++)
            {
            	//Pour tous les fichiers .jar
                if (files[i].endsWith(".jar")) 
                {
                	//Creation de l'URL vers le fichier .jar
                	URL[] urljar = new URL[1];
                	urljar[i] = new URL("jar", "", "file:" + directory.getAbsolutePath() + "\\" + files[i] + "!/");
                	
                	//ClassLoader
                	URLClassLoader cl = URLClassLoader.newInstance(urljar, this.getClass().getClassLoader());
					Class loadedClass = cl.loadClass("Commands." + files[i].substring(0, files[i].length() - 4));
					try {
						Object o = loadedClass.newInstance();
						//Accepter la commande seulement si c'est une instance de CommandeAbstraite
						if (o instanceof CommandeAbstraite)
                        	listeDesCommandes.add((CommandeAbstraite) o);
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
                }
            }
        }
		return listeDesCommandes;
    }
}
