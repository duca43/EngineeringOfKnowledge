package org.eok.medicalsupportsystem.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXFrame;

public class AppFrame extends JXFrame {
	
	private static final long serialVersionUID = -1334335675796492704L;
	private static final String MEDICAL_SUPPORT_SYSTEM = "Medical support system";
	private static final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	public static final String MAIN_DASHBOARD = "main-dashboard";
	public static final String PATIENT_DASHBOARD = "patient-dashboard";
	private MainDashboardPanel mainDashboardPanel;
	private PatientDashboardPanel patientDashboardPanel;
	private JPanel cards;
	private AppStatusBar statusBar;
	private CardLayout cardLayout;

	public AppFrame() {
		this.setTitle(MEDICAL_SUPPORT_SYSTEM);
		this.setIconImage(new ImageIcon("resources/images/app-logo.png").getImage());
		this.setMinimumSize(new Dimension((int) (3 * screenDimension.getWidth() / 4), (int) (3 * screenDimension.getHeight() / 4)));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.cardLayout = new CardLayout();
		this.cards = new JPanel(cardLayout);
		this.mainDashboardPanel = new MainDashboardPanel();
		this.cards.add(this.mainDashboardPanel, MAIN_DASHBOARD);
		this.getContentPane().add(cards); 
		
		this.statusBar = new AppStatusBar();
		this.setStatusBar(statusBar);
	}

	public static Dimension getScreenDimension() {
		return screenDimension;
	}

	public void showDashboard(String dashboardName) {
		this.cardLayout.show(cards, dashboardName);
	}

	public PatientDashboardPanel getPatientDashboardPanel() {
		return patientDashboardPanel;
	}

	public void setPatientDashboardPanel(PatientDashboardPanel patientDashboardPanel) {
		this.patientDashboardPanel = patientDashboardPanel;
		for (int i = 0; i < this.cards.getComponentCount(); i++) {
			if (this.cards.getComponent(i) instanceof PatientDashboardPanel) {
				this.cards.remove(i);
				break;
			}
		}
		this.cards.add(this.patientDashboardPanel, PATIENT_DASHBOARD);
	}

	public MainDashboardPanel getMainDashboardPanel() {
		return mainDashboardPanel;
	}

	public void setMainDashboardPanel(MainDashboardPanel mainDashboardPanel) {
		this.mainDashboardPanel = mainDashboardPanel;
		for (int i = 0; i < this.cards.getComponentCount(); i++) {
			if (this.cards.getComponent(i) instanceof MainDashboardPanel) {
				this.cards.remove(i);
				break;
			}
		}
		this.cards.add(this.mainDashboardPanel, MAIN_DASHBOARD);
	}
}