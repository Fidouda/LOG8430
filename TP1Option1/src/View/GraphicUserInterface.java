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
import java.awt.event.MouseListener;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GraphicUserInterface implements Observer {

	
	//D�claration des �l�ments de l'interface
	private Controller controller;
	private SimpleModel model;
	private JFrame frame;

	private JButton boutonFichier;
	private JButton boutonClear;
	private JButton mockBouton;
	private File root;
	private JScrollPane scrollPane;
	private JButton button[];
	private JTextField affichages[];
	private JCheckBox checkAutoRun;
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
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(250, 100, 598, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 44, 207, 325);
		frame.getContentPane().add(scrollPane);
		
		button = new JButton[model.getCommandList().size()];
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
			
			
			nom = model.getCommandList().get(i).getNom();//"Commande " + (i).toString();
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
		mockBouton = new JButton();
		mockBouton.addActionListener(controller);
		mockBouton.setActionCommand("mockUpdate");
		
		checkAutoRun = new JCheckBox("Auto Run");
		//Ajout d'un action listener qui sera notre contr�leur
		checkAutoRun.addActionListener(controller);
		//On nomme la commande "checkAutoRun"
		checkAutoRun.setActionCommand("checkAutoRun");
		checkAutoRun.setBackground(Color.LIGHT_GRAY);
		checkAutoRun.setBounds(381, 389, 97, 23);
		frame.getContentPane().add(checkAutoRun);
		
		boutonClear = new JButton("Clear");
		//Ajout d'un action listener qui sera notre contr�leur
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
		
		JButton boutonDrop = new JButton("DropBox");
		boutonDrop.addActionListener(controller);
		boutonDrop.setActionCommand("dropBoxButton");
		boutonDrop.setBounds(241, 346, 103, 23);
		frame.getContentPane().add(boutonDrop);
		
		JButton boutonGoogle = new JButton("Google Drive");
		boutonGoogle.addActionListener(controller);
		boutonGoogle.setActionCommand("googleButton");
		boutonGoogle.setBounds(241, 312, 103, 23);
		frame.getContentPane().add(boutonGoogle);
		
		JButton boutonServeur = new JButton("Server");
		boutonServeur.addActionListener(controller);
		boutonServeur.setActionCommand("serverButton");
		boutonServeur.setBounds(241, 278, 103, 23);
		frame.getContentPane().add(boutonServeur);
		
		
		//Ajout d'un observer du mod�le
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
			mockBouton.doClick();
			for(Integer i = 0; i < affichages.length; i++){
				button[i].setEnabled(model.getCommandEnable(i));
				affichages[i].setEnabled(model.getCommandEnable(i));
				affichages[i].setText(model.getCommandResults(i));
			}
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
			
		case "Command":
			for(Integer i = 0; i < affichages.length; i++){
				affichages[i].setText(model.getCommandResults(i));
			}
			break;
		}
	}
}
