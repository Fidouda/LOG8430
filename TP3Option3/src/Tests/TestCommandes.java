package Tests;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.File;

import Commands.*;

public class TestCommandes {

	private String root_;
	
	@Before
	public void setUp() throws Exception {
		
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
	
			CheminAbsolu cheminAbsolu_ = new CheminAbsolu(pathFile);
			assertEquals(cheminAbsolu_.executerCommande(), pathFile);
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void cheminAbsolu_executerCommande_Null() {
		try{
			String pathFile = null;
	
			CheminAbsolu cheminAbsolu_ = new CheminAbsolu(pathFile);
			assertNull(cheminAbsolu_.executerCommande());
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void cheminAbsolu_executerCommande_Fichier() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			CheminAbsolu cheminAbsolu_ = new CheminAbsolu(pathFile);
			assertEquals(cheminAbsolu_.executerCommande(), pathFile);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_executerCommande() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			NomFichier nomFichier = new NomFichier(pathFile);
			assertEquals(nomFichier.executerCommande(), "TestCommandes.java");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_isValid_Valide() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			NomFichier nomFichier = new NomFichier(pathFile);
			assertTrue(nomFichier.isValid());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_isValid_Invalide() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			NomFichier nomFichier = new NomFichier(pathFile);
			assertFalse(nomFichier.isValid());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_executerCommande() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			NomRepertoire nomRepertoir = new NomRepertoire(pathFile);
			assertEquals(nomRepertoir.executerCommande(), "Tests");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_isValid_Valide() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			NomRepertoire nomRepertoir = new NomRepertoire(pathFile);
			assertTrue(nomRepertoir.isValid());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_isValid_Invalide() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			NomRepertoire nomRepertoir = new NomRepertoire(pathFile);
			assertFalse(nomRepertoir.isValid());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void CommandeAbstraite_getType_Fichier() {
		try{
			String pathFile = root_+"\\src\\Tests\\TestCommandes.java";
	
			CommandeAbstraite commande = new NomFichier(pathFile);
			assertEquals(commande.getType(), CommandeAbstraite.Type.FICHIER);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void CommandeAbstraite_getType_Repertoir() {
		try{
			String pathFile = root_+"\\src\\Tests";
	
			CommandeAbstraite commande = new NomRepertoire(pathFile);
			assertEquals(commande.getType(), CommandeAbstraite.Type.REPERTOIR);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
