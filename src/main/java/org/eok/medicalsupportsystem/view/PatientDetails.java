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
		setLayout(new MigLayout("", "[][grow][grow][grow][grow][grow][]", "[][][][][grow][]"));
		
		JXButton btnChoosePatient = new JXButton(new ChoosePatientAction());
		btnChoosePatient.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnChoosePatient, "cell 5 0,alignx trailing,aligny top");
		
		JXLabel lblDetailsAboutPatient = new JXLabel();
		lblDetailsAboutPatient.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 42));
		lblDetailsAboutPatient.setText("Patient's medical card ");
		add(lblDetailsAboutPatient, "cell 1 1 5 1,alignx center,growy");
		
		PatientExaminationHistory patientExaminationHistory = new PatientExaminationHistory(patient);
		add(patientExaminationHistory, "flowx,cell 1 4 5 1,grow");
	}
}