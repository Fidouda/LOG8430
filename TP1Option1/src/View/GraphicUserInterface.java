package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controller.Controller;
import javax.swing.JCheckBox;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import java.io.File;
import javax.swing.JScrollPane;

public class GraphicUserInterface {

	private JFrame frame;
	private JTextField reponse1;
	private JTextField reponse2;
	private JTextField reponse3;
	private JTextField reponse4;
	private JTextField reponse5;

	private File root;
	private javax.swing.JTree tree;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicUserInterface window = new GraphicUserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicUserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(250, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton boutonFichier = new JButton("Bouton fichier");
		boutonFichier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("C:\\"));
				 int fileValue = chooser.showSaveDialog(null);
				    if(fileValue == JFileChooser.APPROVE_OPTION){
				    	root = chooser.getSelectedFile();
				    	System.out.println(root.getAbsolutePath());
				    	//updateArbre(root);
				    }
				  
			}
		});
		boutonFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		boutonFichier.setBounds(55, 380, 139, 40);
		frame.getContentPane().add(boutonFichier);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.DARK_GRAY);
		separator.setForeground(Color.DARK_GRAY);
		separator.setBounds(237, 44, 28, 325);
		frame.getContentPane().add(separator);
		
		JButton boutonCommande1 = new JButton("Commande 1");
		boutonCommande1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande1.setBounds(247, 44, 97, 40);
		frame.getContentPane().add(boutonCommande1);
		
		reponse1 = new JTextField();
		reponse1.setBounds(354, 44, 120, 40);
		frame.getContentPane().add(reponse1);
		reponse1.setColumns(10);
		
		JButton boutonCommande2 = new JButton("Commande 2");
		boutonCommande2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande2.setBounds(247, 109, 97, 40);
		frame.getContentPane().add(boutonCommande2);
		
		JButton boutonCommande3 = new JButton("Commande 3");
		boutonCommande3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boutonCommande3.setBounds(247, 172, 97, 40);
		frame.getContentPane().add(boutonCommande3);
		
		JButton boutonCommande4 = new JButton("Commande 4");
		boutonCommande4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande4.setBounds(247, 239, 97, 40);
		frame.getContentPane().add(boutonCommande4);
		
		reponse2 = new JTextField();
		reponse2.setColumns(10);
		reponse2.setBounds(354, 109, 120, 40);
		frame.getContentPane().add(reponse2);
		
		reponse3 = new JTextField();
		reponse3.setColumns(10);
		reponse3.setBounds(354, 172, 120, 40);
		frame.getContentPane().add(reponse3);
		
		reponse4 = new JTextField();
		reponse4.setColumns(10);
		reponse4.setBounds(354, 239, 120, 40);
		frame.getContentPane().add(reponse4);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.DARK_GRAY);
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(237, 367, 247, 19);
		frame.getContentPane().add(separator_1);
		
		JButton boutonClear = new JButton("Bouton Clear");
		boutonClear.setBounds(237, 380, 107, 40);
		frame.getContentPane().add(boutonClear);
		
		JCheckBox checkAutoRun = new JCheckBox("Auto Run");
		checkAutoRun.setBackground(Color.LIGHT_GRAY);
		checkAutoRun.setBounds(381, 389, 97, 23);
		frame.getContentPane().add(checkAutoRun);
		
		JButton boutonCommande5 = new JButton("Commande 5");
		boutonCommande5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		boutonCommande5.setBounds(247, 302, 97, 40);
		frame.getContentPane().add(boutonCommande5);
		
		reponse5 = new JTextField();
		reponse5.setColumns(10);
		reponse5.setBounds(354, 302, 120, 40);
		frame.getContentPane().add(reponse5);
		
		//ARBRE
		tree = new JTree(new DefaultTreeModel(getTree(null, new File("."))));
		
		//Lorsqu'on clique sur un element de l'arbre
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				if(tree.getSelectionPath() == null)
					return;
				
				String selectedNodeWPath = "";
				
				for(Object part : tree.getSelectionPath().getPath())
					selectedNodeWPath += part.toString();
				
				System.out.println(selectedNodeWPath);
			}
		});
		
		
		//Ajouter l'arbre au JScrollPane
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(15, 44, 207, 325);
		frame.getContentPane().add(scrollPane);
	}
	
	//http://www.java2s.com/Code/Java/File-Input-Output/DisplayafilesysteminaJTreeview.htm
	private DefaultMutableTreeNode getTree(DefaultMutableTreeNode top, File repertoire){
		DefaultMutableTreeNode currentNode;
		
		//Si top est null, c'est le root
		if(top != null){
			currentNode = new DefaultMutableTreeNode(repertoire.getName());
			top.add(currentNode);
		}
		else
			currentNode = new DefaultMutableTreeNode(repertoire.getAbsolutePath());
		
		if(!repertoire.isDirectory())
			return currentNode;
		
		if(repertoire.listFiles() != null){
			for(File fichier : repertoire.listFiles()){
				if(fichier.canRead())
					getTree(currentNode, fichier);
			}
		}
		
		return currentNode;
	}
}
