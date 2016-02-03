package ClassLoader;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;

import sun.misc.Launcher;
import Commands.InterfaceCommande;
import Commands.CommandeAbstraite;

//Tutoriel suivi sur
//http://www.javaworld.com/article/2077477/learn-java/java-tip-113--identify-subclasses-at-runtime.html
public class ClassLoader extends java.lang.ClassLoader {
	
	
	public ArrayList<CommandeAbstraite> chargerCommandes() {
        // Code from JWhich (source cit�e plus haut)
        // ======
        // Translate the package name into an absolute path
		String pckgname = "Commands";
        String name = new String(pckgname);
        ArrayList<CommandeAbstraite> commands = new ArrayList<CommandeAbstraite>();
        if (!name.startsWith("/")) {
            name = "/" + name;
        }        
        name = name.replace('.','/');
        
        // Get a File object for the package
        @SuppressWarnings("restriction")
		URL url = Launcher.class.getResource(name);
        String result = new String("");
        try {
        	// pour que les paths avec les espaces soient pas transformes en %20
			result = java.net.URLDecoder.decode(url.getFile(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        File directory = new File(result);
        // New code
        // ======
        if (directory.exists()) {
            // Get the list of the files contained in the package
            String [] files = directory.list();
            for (int i=0;i<files.length;i++) {
                 
                // we are only interested in .class files
                if (files[i].endsWith(".class")) {
                    // removes the .class extension
                    String classname = files[i].substring(0,files[i].length()-6);
                    try {
                        // Try to create an instance of the object
                        Object o = Class.forName(pckgname+"."+classname).getDeclaredConstructor(String.class).newInstance(new String(""));

                        if (o instanceof CommandeAbstraite) {
                        	commands.add((CommandeAbstraite) o);
                        }
                    } catch (ClassNotFoundException cnfex) {
                        System.err.println(cnfex);
                    } catch (InstantiationException iex) {
             
                    } catch (IllegalAccessException iaex) {
                      
                    } catch (IllegalArgumentException e) {
					
					} catch (InvocationTargetException e) {
					
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
					
					} catch (SecurityException e) {
						
						e.printStackTrace();
					}
                }
            }
        }
		return commands;
    }
	
	
	
	
	
	

}
