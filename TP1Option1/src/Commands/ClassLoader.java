package Commands;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import Commands.InterfaceCommande;

//Tutoriel suivi sur
//http://www.javaworld.com/article/2077260/learn-java/learn-java-the-basics-of-java-class-loaders.html
public class ClassLoader extends java.lang.ClassLoader {
	
	//Lorsque nous recherchons une classe, nous devons retrouver celle qui a �t� instanti�. La hashmap nous assure que nous retournons la bonne classe
	private HashMap<String, Class<?>> tableDesClasses;
	private List<InterfaceCommande> listeDesClasses = new Vector<InterfaceCommande>();
	
	public synchronized Class loadClass(String className, boolean resolveIt) throws ClassNotFoundException 
	{ 
		 Class result; 
		 byte classData[]; 
		 System.out.println(" >>>>>> Load class : "+className);
		 
		 /* Check our local cache of classes */ 
		 result = (Class)tableDesClasses.get(className); 
		 if (result != null) 
		 { 
			 System.out.println(" >>>>>> returning cached result."); 
			  return result; 
		 }
		 try { 
			    result = super.findSystemClass(className); 
			    System.out.println(" >>>>>> returning system class (in CLASSPATH)."); 
			    return result; 
			 } 
		 catch (ClassNotFoundException e) 
		 { 
			System.out.println("        >>>>>> Not a system class."); 
		 } 
		 /* Try to load it from our repository */ 
		 classData = getClassImplFromDataBase(className); 
		 if (classData == null) { 
		     throw new ClassNotFoundException(); 
		 }
		//On finalise le processus de chargement de la classe
		 //Je comprends trop pas cette m�thode
		result = (Class<?>) defineClass(composeFileName(className),classData, 0, classData.length);
		if (resolveIt) 
		{ 
			 resolveClass(result); 
		}
		tableDesClasses.put(className, result); 
			 
		return result;  
	}
	
	private byte[] getClassImplFromDataBase(String className)
	{
		String path = className;
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		byte[] buffer = new byte[8000];

		int compteur;
		    
		try {
			InputStream is = new FileInputStream(path);
			while ((compteur = is.read(buffer)) > 0) {
				bytes.write(buffer, 0, compteur);
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 
		return bytes.toByteArray();
	}
	
	/**
	 * Methode qui s'occupe de composer le binary name de la classe pour le class loader
	 * @param className	Le nom de la classe
	 * @return	Le BinaryName de la classe
	 */
	private String composeFileName(String className){
		String[] split = className.split("\\.");

		return "model." + split[0];
	}
	
	public List<InterfaceCommande> changerCommandes()
	{
		File folder = new File("./command");
		File[] listOfFiles = folder.listFiles();
		listeDesClasses.clear();
	    for (File file : listOfFiles) {
	      if (file.isFile()) {
	        try {
					Object command;
				try 
				{
					command = (loadClass(file.getName(), true)).newInstance();
					listeDesClasses.add((InterfaceCommande)command);
				} 
				catch (InstantiationException | IllegalAccessException e)
				{
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	      } 
	    }
	    return listeDesClasses;
	}

}
