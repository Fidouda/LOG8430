package org.eclipse.ui.tutorials.rcp.part3;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class NavigationView extends ViewPart {
	public static final String ID = "org.eclipse.ui.tutorials.rcp.part3.navigationView";
	 
	public void createPartControl(Composite parent) {
		GraphicUserInterface ui = new GraphicUserInterface();
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		
	}
}