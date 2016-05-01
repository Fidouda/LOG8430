import java.awt.EventQueue;

import Model.SimpleModel;
import View.GraphicUserInterface;
import Controller.Controller;

/**
 * Class MainWindow Classe permettant de démarrer l'application client
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */
public class MainWindow {

	private static GraphicUserInterface window;
	private static SimpleModel model;
	private static Controller controller;

	/**
	 * Fonction permettant de démarrer l'application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// D�claration du model
					model = new SimpleModel();
					// D�claration du contr�leur
					controller = new Controller(model);
					// D�claration de la vue
					window = new GraphicUserInterface(model, controller);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
