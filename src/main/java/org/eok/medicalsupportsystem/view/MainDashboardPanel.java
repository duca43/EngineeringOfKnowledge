package org.eok.medicalsupportsystem.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainDashboardPanel extends JPanel {

	private static final long serialVersionUID = -4994106814333952706L;
	private PatientListPanel patientListPanel;
	private PatientDetails patientDetails;
	private JPanel patientDetailsPane;
	
	public MainDashboardPanel() {
		super(new BorderLayout());
		this.patientListPanel = new PatientListPanel();
		this.add(this.patientListPanel, BorderLayout.WEST);
		
		patientDetailsPane = new JPanel(new BorderLayout());
		this.add(patientDetailsPane, BorderLayout.CENTER);
	}
	
	public PatientListPanel getPatientListPanel() {
		return patientListPanel;
	}

	public PatientDetails getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(PatientDetails patientDetails) {
		this.patientDetails = patientDetails;
		for (int i = 0; i < this.patientDetailsPane.getComponentCount(); i++) {
			if (this.patientDetailsPane.getComponent(i) instanceof PatientDetails) {
				this.patientDetailsPane.remove(i);
				break;
			}
		}
		this.patientDetailsPane.add(this.patientDetails, BorderLayout.CENTER);
		SwingUtilities.updateComponentTreeUI(this.patientDetailsPane);
	}
}