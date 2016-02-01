package View;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JFileChooser;
import Controller.Controller;
import javax.swing.JCheckBox;
import java.io.File;
import javax.swing.JScrollPane;
import java.util.Observable;
import java.util.Observer;

import Model.SimpleModel;

public class GraphicUserInterface implements Observer {

	
	//D�claration des �l�ments de l'interface
	private Controller controller;
	private SimpleModel model;
	private JFrame frame;

	private JButton boutonFichier;
	private JButton boutonClear;
	private File root;
	private JScrollPane scrollPane;
	private ListeCommandes listeCommandes_;
	
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
		
		JButton boutonCommande1 = new JButton("Commande 1");
		boutonCommande1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande1.setBounds(247, 44, 97, 40);
		boutonCommande1.addActionListener(controller);
		boutonCommande1.setActionCommand("commandButton1");
		frame.getContentPane().add(boutonCommande1);
		
		
		JButton boutonCommande2 = new JButton("Commande 2");
		boutonCommande2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande2.setBounds(247, 109, 97, 40);
		frame.getContentPane().add(boutonCommande2);
		
		JButton boutonCommande3 = new JButton("Commande 3");
		boutonCommande3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande3.setBounds(247, 172, 97, 40);
		frame.getContentPane().add(boutonCommande3);
		
		JButton boutonCommande4 = new JButton("Commande 4");
		boutonCommande4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande4.setBounds(247, 239, 97, 40);
		frame.getContentPane().add(boutonCommande4);
		
		JButton boutonCommande5 = new JButton("Commande 5");
		boutonCommande5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande5.setBounds(247, 302, 97, 40);
		frame.getContentPane().add(boutonCommande5);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(237, 367, 247, 19);
		frame.getContentPane().add(separator_1);
		
		JCheckBox checkAutoRun = new JCheckBox("Auto Run");
		checkAutoRun.setBackground(Color.LIGHT_GRAY);
		checkAutoRun.setBounds(381, 389, 97, 23);
		frame.getContentPane().add(checkAutoRun);
		
		/*
		JButton boutonCommande1 = new JButton("Commande 1");
		boutonCommande1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande1.setBounds(247, 44, 97, 40);
		frame.getContentPane().add(boutonCommande1);
		*/
		
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
		
		//Ajout d'un observer du mod�le
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
		System.out.println("Je suis dans la methode update");
		
		switch(arg1.toString()){
		
		case "Update Tree":
			scrollPane.setViewportView(model.getTree());
			scrollPane.repaint();
			break;
			
		case "Clear Tree": 
			frame.remove(scrollPane);
			JScrollPane scrollPaneTemp = new JScrollPane();
			scrollPaneTemp.setBounds(15, 44, 207, 325);
			scrollPane = scrollPaneTemp;
			frame.getContentPane().add(scrollPane);
			scrollPane.repaint();
			break;
			
		case "Root Tree":
			JFileChooser chooser = new JFileChooser();
    		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //Permet la selection de file et folder
    		chooser.setCurrentDirectory(new java.io.File("C:\\"));
    		 int fileValue = chooser.showSaveDialog(null);
    		    if(fileValue == JFileChooser.APPROVE_OPTION){
    		    	root = chooser.getSelectedFile();
    		    	System.out.println(root.getAbsolutePath());
    		    	//genererArbre(root);
    		    	model.genererArbre(root);
    		    }	
			break;
		case "command1":
			System.out.println("hater");
		}
	}
	
	
}
