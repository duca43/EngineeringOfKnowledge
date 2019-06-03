package org.eok.medicalsupportsystem.view;

import java.awt.Font;

import javax.swing.JPanel;

import org.eok.medicalsupportsystem.controller.ChoosePatientAction;
import org.eok.medicalsupportsystem.model.Patient;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;

public class PatientDetails extends JPanel {

	private static final long serialVersionUID = -4560757278028272886L;

	/**
	 * Create the panel.
	 */
	public PatientDetails(Patient patient) {
		setLayout(new MigLayout("", "[grow][grow]", "[][][grow]"));
		
		JXButton btnChoosePatient = new JXButton(new ChoosePatientAction());
		add(btnChoosePatient, "cell 1 0,alignx trailing,aligny top");
		
		JXLabel lblDetailsAboutPatient = new JXLabel();
		lblDetailsAboutPatient.setFont(new Font("Comic Sans MS", Font.ITALIC, 24));
		lblDetailsAboutPatient.setText("Details about patient: ");
		add(lblDetailsAboutPatient, "cell 0 1 2 1,alignx center,growy");
		
		PatientDetailsFields patientDetailsFields = new PatientDetailsFields(patient);
		add(patientDetailsFields, "cell 0 2 2 1,grow");

	}

}
