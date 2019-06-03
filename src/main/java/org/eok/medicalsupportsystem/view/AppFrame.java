package org.eok.medicalsupportsystem.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import org.jdesktop.swingx.JXFrame;

public class AppFrame extends JXFrame {
	
	private static final long serialVersionUID = -1334335675796492704L;
	private static final String MEDICAL_SUPPORT_SYSTEM = "Medical support system";
	private static final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	private MainDashboardPanel mainDashboardPanel;
	private AppStatusBar statusBar;

	public AppFrame() {
		this.setTitle(MEDICAL_SUPPORT_SYSTEM);
		this.setIconImage(new ImageIcon("resources/images/app-logo.png").getImage());
		this.setSize((int) (3 * screenDimension.getWidth() / 4),
				(int) (3 * screenDimension.getHeight() / 4));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.mainDashboardPanel = new MainDashboardPanel();
		this.setContentPane(mainDashboardPanel);
		
		this.statusBar = new AppStatusBar();
		this.setStatusBar(statusBar);
	}

	public static Dimension getScreenDimension() {
		return screenDimension;
	}

	public MainDashboardPanel getMainDashboardPanel() {
		return mainDashboardPanel;
	}

	public void setMainDashboardPanelPanel(MainDashboardPanel homePanel) {
		this.mainDashboardPanel = homePanel;
	}
}