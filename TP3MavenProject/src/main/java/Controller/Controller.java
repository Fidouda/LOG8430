package Controller;

import Model.SimpleModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.dropbox.core.DbxException;

import ClassLoader.ClassLoader;
import Commands.CommandeAbstraite;

import api.DropBoxAPI;
import api.GetHttp;
import api.GoogleDriveAPI;

/**
 * Class Controller Class qui sert de controlleur a l'interface utilisateur
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */

public class Controller implements ActionListener {

	private SimpleModel model;
	private ArrayList<CommandeAbstraite> listeCommandes_;
	private Boolean autoRunEnabled;

	/**
	 * Constructeur de la classe Controller
	 * 
	 * @param modelToSet
	 */
	public Controller(SimpleModel modelToSet) {
		autoRunEnabled = false;

		ClassLoader chargeur = new ClassLoader();
		listeCommandes_ = chargeur.chargerCommandes();

		model = modelToSet;

		model.initialiserListeCommande(listeCommandes_);
		model.initialiserResultatsCommande(listeCommandes_.size());
		model.initialiserActivationCommande(listeCommandes_.size());

	}

	/**
	 * Fonction qui gere les actions de clique sur les boutons correspondant aux
	 * commandes
	 */
	public void actionPerformed(ActionEvent e) {
		if ("clearButton".equals(e.getActionCommand())) {
			model.clearArbre();
			for (int j = 0; j < listeCommandes_.size(); j++) {
				model.setResultatsCommande(j, "");
			}
		}

		else if ("checkAutoRun".equals(e.getActionCommand())) {
			// Toggle his value
			autoRunEnabled = !autoRunEnabled;
		}

		else if ("browseButton".equals(e.getActionCommand())) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Permet
																				// la
																				// selection
																				// de
																				// file
																				// et
																				// folder
			chooser.setCurrentDirectory(new java.io.File("C:\\"));
			int fileValue = chooser.showSaveDialog(null);
			if (fileValue == JFileChooser.APPROVE_OPTION) {
				File root = chooser.getSelectedFile().getAbsoluteFile();
				model.genererArbre(root);
				activerToutesCommandes(chooser.getSelectedFile().toString());
			}
		}

		// V�rification de la commande. On reti
		else if ("commandButton".equals(e.getActionCommand().substring(0, e.getActionCommand().length() - 1))) {
			int commandIndex = Character
					.getNumericValue(e.getActionCommand().charAt(e.getActionCommand().length() - 1));
			CommandeAbstraite commande = listeCommandes_.get(commandIndex);
			System.out.println(model.getSelectionItem());
			commande.setChemin(model.getSelectionItem());
			try {
				commande.executerCommande();
			} catch (Exception exception) {

			}
			model.setResultatsCommande(commandIndex, commande.getAffichage());
		}

		else if ("mockUpdate".equals(e.getActionCommand())) {
			if (model.getSelectionItem() != "")
				activerToutesCommandes(model.getSelectionItem());
		}

		else if ("dropBoxButton".equals(e.getActionCommand())) {
			File f = new File("root");
			if (f.exists()) {
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
			try {
				api.obtenirFichiers("");
			} catch (DbxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			File root = new File("root");
			model.genererArbre(root.getAbsoluteFile());
			activerToutesCommandes(root.toString());
		}

		else if ("googleButton".equals(e.getActionCommand())) {
			File f = new File("root");
			if (f.exists()) {
				try {
					FileUtils.deleteDirectory(f);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			File file = new File("root");
			file.mkdir();
			GoogleDriveAPI drive = new GoogleDriveAPI();

			try {
				drive.startApi();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			File root = new File("root");
			model.genererArbre(root.getAbsoluteFile());
			activerToutesCommandes(root.toString());
		}

		else if ("serverButton".equals(e.getActionCommand())) {
			File f = new File("root");
			if (f.exists()) {
				try {
					FileUtils.deleteDirectory(f);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			File file = new File("root");
			file.mkdir();

			try {
				GetHttp.getHTML();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			File root = new File("root");
			model.genererArbre(root.getAbsoluteFile());
			activerToutesCommandes(root.toString());
		}
	}

	/**
	 * Active toutees les commandes dans la listes de commandes Met a jour
	 * l'interface selon la disponibilit� des commandes en changeant l'acces aux
	 * bouttons
	 */
	public void activerToutesCommandes(String chemin) {
		for (int j = 0; j < listeCommandes_.size(); j++) {
			CommandeAbstraite commande = listeCommandes_.get(j);
			commande.setChemin(chemin);
			try {
				model.setResultatsCommande(j, "");
				model.setCommandeValide(j, true);
				commande.executerCommande();
			} catch (Exception e) {
				model.setCommandeValide(j, false);
				continue;
			}
			if (autoRunEnabled) {
				model.setResultatsCommande(j, commande.getAffichage());
			}
		}
	}
}
