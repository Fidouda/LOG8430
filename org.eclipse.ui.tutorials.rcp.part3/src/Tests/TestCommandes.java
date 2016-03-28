package Tests;

import org.junit.*;

import ClassLoader.ClassLoader;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
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
		

		ClassLoader chargeur = new ClassLoader();
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
			String pathFile = root_;
			listeCommandes_.get(0).setAffichage(pathFile);
			
			assertEquals(listeCommandes_.get(0).executerCommande(), pathFile);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void cheminAbsolu_executerCommande_Null() {
		try{
			String pathFile = null;
			listeCommandes_.get(0).setAffichage(pathFile);
			assertNull(listeCommandes_.get(0).executerCommande());
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void cheminAbsolu_executerCommande_Fichier() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
			listeCommandes_.get(0).setAffichage(pathFile);
			
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
	
			listeCommandes_.get(1).setAffichage(pathFile);
			assertEquals(listeCommandes_.get(1).executerCommande(), "TestCommandes.java");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_estValide_Valide() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			listeCommandes_.get(1).setAffichage(pathFile);
			assertTrue(listeCommandes_.get(1).estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_estValide_Invalide() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			listeCommandes_.get(1).setAffichage(pathFile);
			assertFalse(listeCommandes_.get(1).estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_executerCommande() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			listeCommandes_.get(2).setAffichage(pathFile);
			assertEquals(listeCommandes_.get(2).executerCommande(), "Tests");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_estValide_Valide() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			listeCommandes_.get(2).setAffichage(pathFile);
			assertTrue(listeCommandes_.get(2).estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_estValide_Invalide() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			listeCommandes_.get(2).setAffichage(pathFile);
			assertFalse(listeCommandes_.get(2).estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void CommandeAbstraite_getType_Fichier() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			listeCommandes_.get(1).setAffichage(pathFile);
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
	
			listeCommandes_.get(2).setAffichage(pathFile);
			assertEquals(listeCommandes_.get(2).getType(), CommandeAbstraite.Type.REPERTOIR);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
