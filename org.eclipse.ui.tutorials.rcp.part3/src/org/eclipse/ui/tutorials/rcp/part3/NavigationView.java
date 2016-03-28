package org.eclipse.ui.tutorials.rcp.part3;

import java.awt.Frame;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import Controller.Controller;
import Model.SimpleModel;

/**
 * Classe qui creer le controlleur, le model, et la vue.
 */
public class NavigationView extends ViewPart {
	public NavigationView() {
	}

	public static final String ID = "org.eclipse.ui.tutorials.rcp.part3.navigationView";

	/**
	 * Creation de la vue de base (reprenant les même caractéristiques que le TP1
	 */
	public void createPartControl(Composite parent) {

		// Initialisation du model et du controlleur
		SimpleModel model = null; //Necessaire, ceci n'est pas un bug
		// Déclaration du contrôleur
		Controller controller = null;
		try {
			model = new SimpleModel();
			controller = new Controller(model);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		Frame frame = SWT_AWT.new_Frame(composite);

		new GraphicUserInterface(frame, controller, model);
		frame.setLayout(frame.getLayout());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {

	}

}