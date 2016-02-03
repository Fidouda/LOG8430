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
	
	/*
	 * Initialisation de la vue()
	*/
	public GraphicUserInterface(SimpleModel modelToSet, Controller controllerToSet) {
		model = modelToSet;
		controller = controllerToSet;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(237, 44, 28, 325);
		frame.getContentPane().add(separator);
		
		button = new JButton[listeCommandes_.size()];
		affichages = new JTextField[button.length];
		String nom = "";
		for(Integer i = 0; i < button.length ; i++)
		{
			textField = new JTextField();
			textField.setBounds(385,(i+1)*50+2, 175, 35);
			textField.setColumns(10);
			textField.setEditable(false);
			affichages[i] = textField;
			
			
			nom = listeCommandes_.get(i).getNom();//"Commande " + (i).toString();
			JButton bouton = new JButton(nom);
			bouton.addActionListener(controller);
			bouton.setFont(new Font("Tahoma", Font.PLAIN, 10));
			bouton.setBounds(247,(i+1)*50, 130, 40);
			bouton.setActionCommand("commandButton"+i.toString());
			bouton.setPreferredSize(new Dimension(89, 50));
		    button[i] = bouton;

		    
		    frame.getContentPane().add(button[i]);
		    frame.getContentPane().add(textField);
		}
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(237, 367, 247, 19);
		frame.getContentPane().add(separator_1);
		
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
	
	public JScrollPane getScrollPane(){
		return scrollPane;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public JButton getBoutonFichier(){
		return boutonFichier;
	}
	
	public JButton getBoutonClear(){
		return boutonClear;
	}
	
	public void setScrollPane(JScrollPane scrollToSet){
		scrollPane = scrollToSet;
	}
	
	public void setFrame(JFrame frameToSet){
		frame = frameToSet;
	}
	
	public void setBoutonFichier(JButton buttonToSet){
		boutonFichier = buttonToSet;
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		switch(arg1.toString()){
		
		case "Update Commands":
				updateUINodeClicked();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			affichages[j].setText(commande.getAffichage());
			break;
		}
	}
	
	public void updateUINodeClicked(){
		if(model.getSelectedNode().isDirectory()){
			button[1].setEnabled(false);
			button[2].setEnabled(true);
			affichages[1].setEnabled(false);
			affichages[2].setEnabled(true);
		}
		if(model.getSelectedNode().isFile()){
			button[1].setEnabled(true);
			button[2].setEnabled(false);
			affichages[1].setEnabled(true);
			affichages[2].setEnabled(false);
		}
		if(checkAutoRun.isSelected())
				activerToutesCommandes();	
	}
	
	public void activerToutesCommandes()
	{
		for(int j = 0 ; j < listeCommandes_.size(); j++)
		{
			CommandeAbstraite commande = listeCommandes_.get(j);
			commande.setChemin(model.getSelectedItem());
			try {
				commande.executerCommande();
			} catch (Exception e) {
				continue;
			}
			affichages[j].setText(commande.getAffichage());
		}
	}
}
