package org.eok.medicalsupportsystem.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.jdesktop.swingx.JXFrame;

public class AppFrame extends JXFrame {
	
	private static final long serialVersionUID = -1334335675796492704L;
	private static final String MEDICAL_SUPPORT_SYSTEM = "Medical support system";
	private static final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	private AppToolBar toolBar;
	private AppStatusBar statusBar;
	private PatientListScrollPane patientListScrollPane;

	public AppFrame() {
		this.setTitle(MEDICAL_SUPPORT_SYSTEM);
		this.setSize((int) (2 * screenDimension.getWidth() / 3),
				(int) (3 * screenDimension.getHeight() / 4));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.toolBar = new AppToolBar();
		this.setToolBar(toolBar);
		
		this.patientListScrollPane = new PatientListScrollPane();
		this.add(this.patientListScrollPane, BorderLayout.WEST);
		
		this.statusBar = new AppStatusBar();
		this.setStatusBar(statusBar);
	}

	public static Dimension getScreenDimension() {
		return screenDimension;
	}
}