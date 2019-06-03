package org.eok.medicalsupportsystem.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class MainDashboardPanel extends JPanel {

	private static final long serialVersionUID = -4994106814333952706L;
	private PatientListPanel patientListPanel;
	private PatientDetails patientDetails;
	private JScrollPane patientDetailsScrollPane;
	
	public MainDashboardPanel() {
		super(new BorderLayout());
		this.patientListPanel = new PatientListPanel();
		this.add(this.patientListPanel, BorderLayout.WEST);
		
		patientDetailsScrollPane = new JScrollPane();
		this.add(patientDetailsScrollPane, BorderLayout.CENTER);
	}
	
	public PatientListPanel getPatientListPanel() {
		return patientListPanel;
	}

	public PatientDetails getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(PatientDetails patientDetails) {
		this.patientDetails = patientDetails;
		this.patientDetailsScrollPane.setViewportView(this.patientDetails);
		SwingUtilities.updateComponentTreeUI(this.patientDetailsScrollPane);
	}
}