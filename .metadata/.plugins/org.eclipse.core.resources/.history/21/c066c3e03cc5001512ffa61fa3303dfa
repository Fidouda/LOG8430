import java.awt.EventQueue;

import Model.SimpleModel;
import View.GraphicUserInterface;
import Controller.Controller;

public class MainWindow {
	
	private static GraphicUserInterface window;
	private static SimpleModel model;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
				
		
					model = new SimpleModel(window);
					
					Controller controller = new Controller(model);
					//window.addObserver(controller);
				
					window = new GraphicUserInterface(model, controller);
					window.getFrame().setVisible(true);
					/*
					Controller controller = new Controller();
					
					controller.setWindow(new GraphicUserInterface());
					controller.getWindow().addObserver(controller);
					controller.setModel(new SimpleModel(controller.getWindow()));
					
					controller.getWindow().getFrame().setVisible(true);*/
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
