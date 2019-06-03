package org.eok.medicalsupportsystem.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXFrame;

public class AppFrame extends JXFrame {
	
	private static final long serialVersionUID = -1334335675796492704L;
	private static final String MEDICAL_SUPPORT_SYSTEM = "Medical support system";
	private static final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	private JPanel dashboardPanel;
	private AppStatusBar statusBar;

	public AppFrame() {
		this.setTitle(MEDICAL_SUPPORT_SYSTEM);
		this.setIconImage(new ImageIcon("resources/images/app-logo.png").getImage());
		this.setSize((int) (3 * screenDimension.getWidth() / 4),
				(int) (3 * screenDimension.getHeight() / 4));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.dashboardPanel = new MainDashboardPanel();
		this.setContentPane(dashboardPanel);
		
		this.statusBar = new AppStatusBar();
		this.setStatusBar(statusBar);
	}

	public static Dimension getScreenDimension() {
		return screenDimension;
	}

	public JPanel getDashboardPanel() {
		return dashboardPanel;
	}

	public void setDashboardPanel(JPanel dashboardPanel) {
		this.dashboardPanel = dashboardPanel;
		this.setContentPane(dashboardPanel);
	}
}