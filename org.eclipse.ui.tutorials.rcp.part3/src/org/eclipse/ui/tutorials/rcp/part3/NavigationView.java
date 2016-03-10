package org.eclipse.ui.tutorials.rcp.part3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import Controller.Controller;
import Model.SimpleModel;

import org.eclipse.swt.widgets.Button;

public class NavigationView extends ViewPart {
	public NavigationView() {
	}
	public static final String ID = "org.eclipse.ui.tutorials.rcp.part3.navigationView";
	 
	public void createPartControl(Composite parent) {
		
		//Initialisation du model et du controlleur
		SimpleModel model = null;
		try {
			model = new SimpleModel();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Déclaration du contrôleur 
		Controller controller = new Controller(model);
		
		Composite composite = new Composite(parent, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		Frame frame = SWT_AWT.new_Frame(composite);
		
		GraphicUserInterface face = new GraphicUserInterface(frame, controller, model);
		frame.setLayout(frame.getLayout());
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		
	}

}