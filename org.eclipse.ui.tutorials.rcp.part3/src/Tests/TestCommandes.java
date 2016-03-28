package Tests;

import org.junit.*;

import ClassLoader.ClassLoader;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Commands.*;
/**
 * Classe de tests unitaires
 */
public class TestCommandes {

	private String root_;
	private ArrayList<CommandeAbstraite> listeCommandes_;
	
	@Before
	public void setUp() throws Exception {
		// Open and read file to check if a path for JAR files for the ClassLoader already exist
		Path currentRelativePath = Paths.get("");
		File file = new File(currentRelativePath.toAbsolutePath().toString() + "\\pathToPlugins.txt");
		BufferedReader reader = null;
		String directory = null;

		try {
		    reader = new BufferedReader(new FileReader(file));
		    directory = reader.readLine();
		} catch (FileNotFoundException e) {
			// Do nothing, will prompt to choose folder later
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        if (reader != null) {
		            reader.close();
		        }
		    } catch (IOException e) {
		    }
		}	
		
		ClassLoader chargeur = new ClassLoader(directory);
		try {
			listeCommandes_ = chargeur.chargerCommandes();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try
		{
			root_ = System.getProperty("user.dir");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void cheminAbsolu_executerCommande_Repertoir() {
		try{
			CommandeAbstraite commande = listeCommandes_.get(0);
			String pathFile = root_;
			commande.setChemin(pathFile);
			
			assertEquals(commande.executerCommande(), pathFile);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void cheminAbsolu_executerCommande_Null() {
		try{
			String pathFile = null;
			CommandeAbstraite commande = listeCommandes_.get(0);
			commande.setChemin(pathFile);
			assertNull(commande.executerCommande());
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void cheminAbsolu_executerCommande_Fichier() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
			listeCommandes_.get(0).setChemin(pathFile);
			
			assertEquals(listeCommandes_.get(0).executerCommande(), pathFile);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_executerCommande() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			listeCommandes_.get(1).setChemin(pathFile);
			assertEquals(listeCommandes_.get(1).executerCommande(), "TestCommandes.java");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void NomRepertoire_executerCommande() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			listeCommandes_.get(2).setChemin(pathFile);
			assertEquals(listeCommandes_.get(2).executerCommande(), "Tests");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void CommandeAbstraite_getType_Fichier() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			listeCommandes_.get(1).setChemin(pathFile);
			assertEquals(listeCommandes_.get(1).getType(), CommandeAbstraite.Type.FICHIER);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void CommandeAbstraite_getType_Repertoir() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			listeCommandes_.get(2).setChemin(pathFile);
			assertEquals(listeCommandes_.get(2).getType(), CommandeAbstraite.Type.REPERTOIR);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
