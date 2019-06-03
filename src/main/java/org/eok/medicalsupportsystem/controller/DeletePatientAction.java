package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.event.PatientEvent;
import org.eok.medicalsupportsystem.event.PatientEvent.PatientEnum;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.repository.PatientRepository;

public class DeletePatientAction extends AbstractAction {

	private static final long serialVersionUID = 6892056307299431790L;
	private PatientRepository patientRepository;

	public DeletePatientAction() {
		this.patientRepository = new PatientRepository();
		putValue(NAME, "Delete patient");
		putValue(SHORT_DESCRIPTION, "Click to delete patient from system.");
		putValue(SMALL_ICON, new ImageIcon("resources/images/delete-patient-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Patient selectedPatient = AppSingleton.getInstance().
											   getAppFrame().
											   getMainDashboardPanel().
											   getPatientListPanel().
											   getPatientListScrollPane().
											   getPatientList().
											   getSelectedPatient();
		System.out.println(selectedPatient);
		if (selectedPatient != null) {		
			this.patientRepository.delete(selectedPatient.getId().toString());
			List<Patient> patients = AppSingleton.getInstance().getDoctor().getPatients();
			for (int i = 0; i < patients.size(); i++) {
				if (patients.get(i).getId().equals(selectedPatient.getId())) {
					AppSingleton.getInstance().getDoctor().getPatients().remove(i);
					break;
				}
			}
			PatientEvent patientEvent = new PatientEvent();
			patientEvent.setPatient(selectedPatient);
			patientEvent.setEvent(PatientEnum.DELETE_PATIENT);
			selectedPatient.notifyObservers(patientEvent);
		}
	}
}