package ClassLoader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Commands.CommandeAbstraite;

//Tutoriel suivi sur
//http://www.javaworld.com/article/2077477/learn-java/java-tip-113--identify-subclasses-at-runtime.html
//Modifie pour permettre d'ouvrir des fichiers .jar
public class ClassLoader extends java.lang.ClassLoader {
	
	File directory = null;
	
	/**
	 * Constructeur de ClassLoader.
	 * Invite l'utilisateur à sélectionner le répertoire où sont situés les commandes en fichiers .jar
	 */
	public ClassLoader(){
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //Permet la selection de file et folder
		chooser.setCurrentDirectory(new java.io.File("C:\\"));
		chooser.setDialogTitle("Choisir le répertoire contenant les commandes au format .jar");
		 int fileValue = chooser.showSaveDialog(null);
		    if(fileValue == JFileChooser.APPROVE_OPTION){
		    	directory = chooser.getSelectedFile().getAbsoluteFile();
		    }
		    else {
		    	System.exit(0);
		    }
	}
	
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
                	urljar[0] = new URL("jar", "", "file:" + directory.getAbsolutePath() + "\\" + files[i] + "!/");
                	
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
