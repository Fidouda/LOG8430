package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ListeCommandes extends JPanel{

	private static final long serialVersionUID = 1L; //generer par eclipse
	private JPanel listePanel_;
	private final JButton btnBoutton = new JButton("Boutton");
	
	public ListeCommandes(){
		setLayout(new BorderLayout(0, 0));
		listePanel_ = new JPanel();
		listePanel_.setBackground(Color.WHITE);
		listePanel_.setBorder(new CompoundBorder(new LineBorder(new Color(192, 192, 192)), new EmptyBorder(10, 10, 10, 10)));
		add(listePanel_, BorderLayout.CENTER);
		listePanel_.setLayout(new GridLayout(0, 1, 10, 10));
		listePanel_.add(btnBoutton);
	}
	
}
