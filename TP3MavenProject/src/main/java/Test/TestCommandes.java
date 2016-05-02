package Test;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.*;

import com.dropbox.core.DbxException;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import Commands.*;
import api.DropBoxAPI;

/**
 * Class TestCommandes
 * Classe de tests unitaires
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */

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
			String pathFile = root_+"\\src\\main\\java\\Test\\TestCommandes.java";
	
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
			String pathFile = root_+"\\src\\main\\java\\Test\\TestCommandes.java";
	
			NomFichier nomFichier = new NomFichier(pathFile);
			assertEquals(nomFichier.executerCommande(), "TestCommandes.java");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_estValide_Valide() {
		try{
			String pathFile = root_+"\\src\\main\\java\\Test\\TestCommandes.java";
	
			NomFichier nomFichier = new NomFichier(pathFile);
			assertTrue(nomFichier.estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomFichier_estValide_Invalide() {
		try{
			String pathFile = root_+"\\src\\main\\java\\Test\\";
	
			NomFichier nomFichier = new NomFichier(pathFile);
			assertFalse(nomFichier.estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_executerCommande() {
		try{
			String pathFile = root_+"\\src\\main\\java\\Test";
	
			NomRepertoire nomRepertoir = new NomRepertoire(pathFile);
			assertEquals(nomRepertoir.executerCommande(), "Test");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_estValide_Valide() {
		try{
			String pathFile = root_+"\\src\\main\\java\\Test\\";
	
			NomRepertoire nomRepertoir = new NomRepertoire(pathFile);
			assertTrue(nomRepertoir.estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void NomRepertoire_estValide_Invalide() {
		try{
			String pathFile = root_+"\\src\\main\\java\\Test\\TestCommandes.java";
	
			NomRepertoire nomRepertoir = new NomRepertoire(pathFile);
			assertFalse(nomRepertoir.estValide());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void CommandeAbstraite_getType_Fichier() {
		try{
			String pathFile = root_+"\\src\\main\\java\\Test\\TestCommandes.java";
	
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
			String pathFile = root_+"\\src\\main\\java\\Test";
	
			CommandeAbstraite commande = new NomRepertoire(pathFile);
			assertEquals(commande.getType(), CommandeAbstraite.Type.REPERTOIR);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void Dropbox_obtenirFichiers_chargerFichier() {
		try{
			
			File f = new File("root");
        	if (f.exists())
        	{
        		try {
					FileUtils.deleteDirectory(f);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        	
        	File file = new File("root");
        	file.mkdir();
        	DropBoxAPI api = new DropBoxAPI();
        	
        	//Upload le fichier test.txt dans l'entrepot dropbox
        	api.chargerFichier();
        	
        	//L'application va creer le fichier text.txt localement avec obtenirFichiers
        	try {
				api.obtenirFichiers("");
			} catch (DbxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	File fileTest = new File("root/test.txt");
        	
        	//Check si test.txt a bien ete creer, donc si obtenirFichiers a reussi a chercher test.txt
			assertTrue(fileTest.exists());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	

}


