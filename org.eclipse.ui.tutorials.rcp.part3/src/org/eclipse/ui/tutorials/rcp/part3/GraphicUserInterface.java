package org.eclipse.ui.tutorials.rcp.part3;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

import Controller.Controller;
import Model.SimpleModel;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

/**
 * Class GraphicUserInterface
 * Interface utilisateur
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */

public class GraphicUserInterface implements Observer {

	// D�claration des �l�ments de l'interface
	// private JFrame frame;

	private JButton boutonFichier;
	private JButton boutonPathJAR;
	private JButton boutonClear;
	private JButton mockBouton;
	private JScrollPane scrollPane;
	private JButton button[];
	private JTextField affichages[];
	private JCheckBox checkAutoRun;
	private JTextField textField;

	private Controller controller;
	private SimpleModel model;
	private Frame frameGUI;

	/**
	 * Constructeur de la vue
	 */
	public GraphicUserInterface(Frame frame, Controller controller, SimpleModel model) {
		this.controller = controller;
		this.model = model;
		initialize(frame);
	}

	/**
	 * Initialisation des elements de l'interface
	 */
	private void initialize(Frame frame) {

		frameGUI = frame;
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(250, 100, 598, 485);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 44, 207, 325);
		frame.add(scrollPane);

		button = new JButton[model.getCommandList().size()];
		affichages = new JTextField[button.length];
		String nom = "";
		for (Integer i = 0; i < button.length; i++) {
			textField = new JTextField();
			textField.setBounds(385, (i + 1) * 50 + 2, 175, 35);
			textField.setColumns(10);
			textField.setEditable(false);
			textField.setEditable(false);
			affichages[i] = textField;

			nom = model.getCommandList().get(i).getNom();// "Commande " + (i).toString();
			JButton bouton = new JButton(nom);
			bouton.addActionListener(controller);
			bouton.setFont(new Font("Tahoma", Font.PLAIN, 10));
			bouton.setBounds(247, (i + 1) * 50, 130, 40);
			bouton.setActionCommand("commandButton" + i.toString());
			bouton.setPreferredSize(new Dimension(89, 50));
			bouton.setEnabled(false);
			button[i] = bouton;

			frame.add(button[i]);
			frame.add(textField);
		}
		mockBouton = new JButton();
		mockBouton.addActionListener(controller);
		mockBouton.setActionCommand("mockUpdate");

		checkAutoRun = new JCheckBox("Auto Run");
		// Ajout d'un action listener qui sera notre contr�leur
		checkAutoRun.addActionListener(controller);
		// On nomme la commande "checkAutoRun"
		checkAutoRun.setActionCommand("checkAutoRun");
		checkAutoRun.setBackground(Color.LIGHT_GRAY);
		checkAutoRun.setBounds(381, 389, 97, 23);
		frame.add(checkAutoRun);

		boutonClear = new JButton("Clear");
		// Ajout d'un action listener qui sera notre contr�leur
		boutonClear.addActionListener(controller);
		// On nomme la commande "clearButton"
		boutonClear.setActionCommand("clearButton");
		boutonClear.setBounds(237, 380, 107, 40);
		frame.add(boutonClear);

		boutonFichier = new JButton("Select file/folder");
		boutonFichier.addActionListener(controller);
		boutonFichier.setActionCommand("browseButton");
		boutonFichier.setBounds(25, 380, 175, 40);
		frame.add(boutonFichier);
		
		boutonPathJAR = new JButton("Select JARs' folder");
		boutonPathJAR.addActionListener(controller);
		boutonPathJAR.setActionCommand("jarsButton");
		boutonPathJAR.setBounds(25, 430, 175, 40);
		frame.add(boutonPathJAR);

		JLabel label1 = new JLabel("");
		label1.setBackground(Color.WHITE);
		label1.setBounds(381, 44, 191, 40);
		frame.add(label1);

		// Ajout d'un observer du mod�le
		model.addObserver(this);
	}

	/**
	 * Retourne le pane de l'interface
	 * 
	 * @return scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * Retourne le frame de l'interface
	 * 
	 * @return frame
	 */
	public JFrame getFrame() {
		// return frame;
		return null;
	}

	/**
	 * Retourne le boutonFichier de l'interface
	 * 
	 * @return boutonFichier
	 */
	public JButton getBoutonFichier() {
		return boutonFichier;
	}

	/**
	 * Retourne le boutonClear de l'interface
	 * 
	 * @return boutonClear
	 */
	public JButton getBoutonClear() {
		return boutonClear;
	}

	/**
	 * Methode de modification du scrollPane
	 * 
	 * @param scrollToSet
	 */
	public void setScrollPane(JScrollPane scrollToSet) {
		scrollPane = scrollToSet;
	}

	/**
	 * Methode de modification du frame
	 * 
	 * @param frameToSet
	 */
	public void setFrame(JFrame frameToSet) {
		// frame = frameToSet;
	}

	/**
	 * Methode de modification du boutonFichier
	 * 
	 * @param buttonToSet
	 */
	public void setBoutonFichier(JButton buttonToSet) {
		boutonFichier = buttonToSet;
	}

	/**
	 * Methode de mise a jour de l'interface suite a des notification de
	 * l'observable
	 * 
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		switch (arg1.toString()) {

		case "Update Commands":
			mockBouton.doClick();
			for (Integer i = 0; i < affichages.length; i++) {
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
			for (Integer i = 0; i < affichages.length; i++) {
				affichages[i].setText("");
			}
			break;

		case "Command":
			for (Integer i = 0; i < affichages.length; i++) {
				affichages[i].setText(model.getCommandResults(i));
			}
			break;
			
		case "Refresh Commands":
			//Clear old buttons
			for (Integer i = 0; i < button.length; i++) {
				frameGUI.remove(affichages[i]);
				frameGUI.remove(button[i]);
			}
			
			//Create new buttons
			button = new JButton[model.getCommandList().size()];
			affichages = new JTextField[button.length];
			String nom = "";
			for (Integer i = 0; i < button.length; i++) {
				textField = new JTextField();
				textField.setBounds(385, (i + 1) * 50 + 2, 175, 35);
				textField.setColumns(10);
				textField.setEditable(false);
				textField.setEditable(false);
				affichages[i] = textField;

				nom = model.getCommandList().get(i).getNom();// "Commande " + (i).toString();
				JButton bouton = new JButton(nom);
				bouton.addActionListener(controller);
				bouton.setFont(new Font("Tahoma", Font.PLAIN, 10));
				bouton.setBounds(247, (i + 1) * 50, 130, 40);
				bouton.setActionCommand("commandButton" + i.toString());
				bouton.setPreferredSize(new Dimension(89, 50));
				bouton.setEnabled(false);
				button[i] = bouton;

				frameGUI.add(button[i]);
				frameGUI.add(textField);
			}
			frameGUI.repaint();
			break;
		}
	}
}
