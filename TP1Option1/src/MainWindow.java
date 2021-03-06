import java.awt.EventQueue;


import Model.SimpleModel;
import View.GraphicUserInterface;
import Controller.Controller;

public class MainWindow {
	
	private static GraphicUserInterface window;
	private static SimpleModel model;
	private static Controller controller;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					//Déclaration du model
					model = new SimpleModel();
					//Déclaration du contrôleur 
					controller = new Controller(model);
					//Déclaration de la vue
					window = new GraphicUserInterface(model, controller);
					window.getFrame().setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
