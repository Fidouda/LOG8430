package org.eclipse.ui.tutorials.rcp.part3;

import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		layout.addView(NavigationView.ID, 1, 1, editorArea);
		layout.getViewLayout(NavigationView.ID).setCloseable(false);
	}
}
