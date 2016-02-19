package View;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;

import ClassLoader.ClassLoader;
import Controller.Controller;

import javax.swing.JCheckBox;

import java.io.File;

import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Model.SimpleModel;

import javax.swing.JLabel;

import Commands.CommandeAbstraite;


public class GraphicUserInterface implements Observer {

	
	//Déclaration des éléments de l'interface
	private Controller controller;
	private SimpleModel model;
	private JFrame frame;

	private JButton boutonFichier;
	private JButton boutonClear;
	private File root;
	private JScrollPane scrollPane;
	private JButton button[];
	private JTextField affichages[];
	private JCheckBox checkAutoRun;
	private ArrayList<CommandeAbstraite> listeCommandes_;
	private JTextField textField;
	
	/**
	 * Constructeur de la vue
	 */
	public GraphicUserInterface(SimpleModel modelToSet, Controller controllerToSet) {
		model = modelToSet;
		controller = controllerToSet;
		initialize();
	}

	/**
	 * Initialisation des elements de l'interface
	 */
	private void initialize() {
		
		ClassLoader chargeur = new ClassLoader();
		listeCommandes_ = chargeur.chargerCommandes();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(250, 100, 598, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 44, 207, 325);
		frame.getContentPane().add(scrollPane);
		
		button = new JButton[listeCommandes_.size()];
		affichages = new JTextField[button.length];
		String nom = "";
		for(Integer i = 0; i < button.length ; i++)
		{
			textField = new JTextField();
			textField.setBounds(385,(i+1)*50+2, 175, 35);
			textField.setColumns(10);
			textField.setEditable(false);
			textField.setEditable(false);
			affichages[i] = textField;
			
			
			nom = listeCommandes_.get(i).getNom();//"Commande " + (i).toString();
			JButton bouton = new JButton(nom);
			bouton.addActionListener(controller);
			bouton.setFont(new Font("Tahoma", Font.PLAIN, 10));
			bouton.setBounds(247,(i+1)*50, 130, 40);
			bouton.setActionCommand("commandButton"+i.toString());
			bouton.setPreferredSize(new Dimension(89, 50));
			bouton.setEnabled(false);
		    button[i] = bouton;

		    
		    frame.getContentPane().add(button[i]);
		    frame.getContentPane().add(textField);
		}
		
		checkAutoRun = new JCheckBox("Auto Run");
		checkAutoRun.setBackground(Color.LIGHT_GRAY);
		checkAutoRun.setBounds(381, 389, 97, 23);
		frame.getContentPane().add(checkAutoRun);
		
		boutonClear = new JButton("Clear");
		//Ajout d'un action listener qui sera notre contrôleur
		boutonClear.addActionListener(controller);
		//On nomme la commande "clearButton"
        boutonClear.setActionCommand("clearButton");
		boutonClear.setBounds(237, 380, 107, 40);
		frame.getContentPane().add(boutonClear);
		
		boutonFichier = new JButton("Select file/folder");
		boutonFichier.addActionListener(controller);
        boutonFichier.setActionCommand("browseButton");
        
		boutonFichier.setBounds(25, 380, 175, 40);
		frame.getContentPane().add(boutonFichier);
		
		JLabel label1 = new JLabel("");
		label1.setBackground(Color.WHITE);
		label1.setBounds(381, 44, 191, 40);
		frame.getContentPane().add(label1);
		
		
		//Ajout d'un observer du modèle
        model.addObserver(this);
        //
	}
	
	/**
	 * Retourne le pane de l'interface
	 * @return scrollPane
	 */
	public JScrollPane getScrollPane(){
		return scrollPane;
	}
	
	/**
	 * Retourne le frame de l'interface
	 * @return frame
	 */
	public JFrame getFrame(){
		return frame;
	}
	
	/**
	 * Retourne le boutonFichier de l'interface
	 * @return boutonFichier
	 */
	public JButton getBoutonFichier(){
		return boutonFichier;
	}
	
	/**
	 * Retourne le boutonClear de l'interface
	 * @return boutonClear
	 */
	public JButton getBoutonClear(){
		return boutonClear;
	}
	
	/**
	 * Methode de modification du scrollPane
	 * @param scrollToSet
	 */
	public void setScrollPane(JScrollPane scrollToSet){
		scrollPane = scrollToSet;
	}
	
	/**
	 * Methode de modification du frame
	 * @param frameToSet
	 */
	public void setFrame(JFrame frameToSet){
		frame = frameToSet;
	}
	
	/**
	 * Methode de modification du boutonFichier
	 * @param buttonToSet
	 */
	public void setBoutonFichier(JButton buttonToSet){
		boutonFichier = buttonToSet;
	}

	
	/**
	 * Methode de mise a jour de l'interface suite a des notification de l'observable
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		switch(arg1.toString()){
		
		case "Update Commands":
			activerToutesCommandes();
			break;
		
		case "Update Tree":
			scrollPane.setViewportView(model.getTree());
			scrollPane.repaint();
			break;
			
		case "Clear Tree": 
			for(Integer i = 0; i < affichages.length; i++){
				affichages[i].setText("");
			}

			break;
			
		case "Root Tree":
			JFileChooser chooser = new JFileChooser();
    		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //Permet la selection de file et folder
    		chooser.setCurrentDirectory(new java.io.File("C:\\"));
    		 int fileValue = chooser.showSaveDialog(null);
    		    if(fileValue == JFileChooser.APPROVE_OPTION){
    		    	root = chooser.getSelectedFile().getAbsoluteFile();
    		    	//System.out.println(root.getParent());
    		    	model.genererArbre(root);		
    		    }	
			break;
		case "Command":
			int j = model.getCommand();
			CommandeAbstraite commande = listeCommandes_.get(j);
			commande.setChemin(model.getSelectedItem());
			try 
			{
				commande.executerCommande();
			} 
			catch (Exception e) 
			{
				
			}
			affichages[j].setText(commande.getAffichage());
			break;
		}
	}
	
	/**
	 * Active toutees les commandes dans la listes de commandes
	 * Met a jour l'interface selon la disponibilité des commandes en changeant l'acces aux bouttons
	 */
	public void activerToutesCommandes()
	{
		for(int j = 0 ; j < listeCommandes_.size(); j++)
		{
			CommandeAbstraite commande = listeCommandes_.get(j);
			commande.setChemin(model.getSelectedItem());
			try {
				affichages[j].setText("");
				button[j].setEnabled(true);
				affichages[j].setEnabled(true);
				commande.executerCommande();
			} catch (Exception e) {
				button[j].setEnabled(false);
				affichages[j].setEnabled(false);
				continue;
			}
			if(checkAutoRun.isSelected())
				affichages[j].setText(commande.getAffichage());
		}
	}
}
