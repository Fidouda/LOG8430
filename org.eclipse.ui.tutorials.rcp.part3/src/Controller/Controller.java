package Controller;

import Model.SimpleModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import ClassLoader.ClassLoader;
import Commands.CommandeAbstraite;

/**
 * Class Controller
 * Class qui sert de controlleur a l'interface utilisateur
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
	 * @throws IOException 
	 */
	public Controller(SimpleModel modelToSet) throws IOException {
		autoRunEnabled = false;

		ClassLoader chargeur = new ClassLoader();
		try {
			listeCommandes_ = chargeur.chargerCommandes();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		model = modelToSet;

		model.initialiserListeCommande(listeCommandes_);
		model.initialiserCommandeResultats(listeCommandes_.size());
		model.initializeCommandEnable(listeCommandes_.size());

	}

	/**
	 * Fonction qui gere les actions de clique sur les boutons correspondant aux
	 * commandes
	 */
	public void actionPerformed(ActionEvent e) {
		if ("clearButton".equals(e.getActionCommand())) {
			model.clearTree();
			for (int j = 0; j < listeCommandes_.size(); j++) {
				model.setCommandResults(j, "");
			}
		}

		else if ("checkAutoRun".equals(e.getActionCommand())) {
			// Toggle his value
			autoRunEnabled = !autoRunEnabled;
		}

		else if ("browseButton".equals(e.getActionCommand())) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chooser.setCurrentDirectory(new java.io.File("C:\\"));
			int fileValue = chooser.showSaveDialog(null);
			if (fileValue == JFileChooser.APPROVE_OPTION) {
				File root = chooser.getSelectedFile().getAbsoluteFile();
				model.genererArbre(root);
				activerToutesCommandes(chooser.getSelectedFile().toString());
			}
		}

		// Vérification de la commande. On reti
		else if ("commandButton".equals(e.getActionCommand().substring(0, e.getActionCommand().length() - 1))) {
			int commandIndex = Character
					.getNumericValue(e.getActionCommand().charAt(e.getActionCommand().length() - 1));
			CommandeAbstraite commande = listeCommandes_.get(commandIndex);
			System.out.println(model.obtenirItemSelectionne());
			commande.setChemin(model.obtenirItemSelectionne());
			try {
				commande.executerCommande();
			} catch (Exception exception) {

			}
			model.setCommandResults(commandIndex, commande.getAffichage());
		}

		else if ("mockUpdate".equals(e.getActionCommand())) {
			if (model.obtenirItemSelectionne() != "")
				activerToutesCommandes(model.obtenirItemSelectionne());
		}

	}

	/**
	 * Active toutees les commandes dans la listes de commandes Met a jour
	 * l'interface selon la disponibilité des commandes en changeant l'acces aux
	 * bouttons
	 */
	public void activerToutesCommandes(String chemin) {
		for (int j = 0; j < listeCommandes_.size(); j++) {
			CommandeAbstraite commande = listeCommandes_.get(j);
			commande.setChemin(chemin);
			try {
				model.setCommandResults(j, "");
				model.setCommandEnable(j, true);
				commande.executerCommande();
			} catch (Exception e) {
				model.setCommandEnable(j, false);
				continue;
			}
			if (autoRunEnabled) {
				model.setCommandResults(j, commande.getAffichage());
			}
		}
	}
}
