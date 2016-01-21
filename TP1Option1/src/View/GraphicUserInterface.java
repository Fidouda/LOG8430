package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JFileChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controller.Controller;
import javax.swing.JCheckBox;

public class GraphicUserInterface {

	private JFrame frame;
	private JTextField reponse1;
	private JTextField reponse2;
	private JTextField reponse3;
	private JTextField reponse4;
	private JTextField reponse5;

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
				    if(fileValue == JFileChooser.APPROVE_OPTION)
				     System.out.println("allo");
			}
		});
		boutonFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		boutonFichier.setBounds(55, 380, 139, 40);
		frame.getContentPane().add(boutonFichier);
		
		JTextPane affichageArbre = new JTextPane();
		affichageArbre.setBackground(Color.WHITE);
		affichageArbre.setBounds(10, 44, 217, 325);
		frame.getContentPane().add(affichageArbre);
		
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
	}
}
