package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.event.PatientEvent;
import org.eok.medicalsupportsystem.event.PatientEvent.PatientEnum;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.repository.DoctorRepository;
import org.eok.medicalsupportsystem.repository.PatientRepository;
import org.eok.medicalsupportsystem.view.AddNewPatientDialog;

public class AddNewPatientAction extends AbstractAction {

	private static final long serialVersionUID = 8090175297404135426L;
	private AddNewPatientDialog dialog;
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;
	
	public AddNewPatientAction(AddNewPatientDialog dialog) {
		this.dialog = dialog;
		this.patientRepository = new PatientRepository();
		this.doctorRepository = new DoctorRepository();
		putValue(NAME, "Add patient");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Patient newPatient = new Patient(dialog.getFirstName(), 
										 dialog.getLastName(),
										 dialog.getGender(),
										 Integer.parseInt(dialog.getAge()),
										 dialog.getRace());
		
		newPatient.attachObserver(AppSingleton.getInstance().getAppFrame()
															.getMainDashboardPanel()
															.getPatientListPanel()
															.getPatientListScrollPane()
															.getPatientList());
		this.patientRepository.save(newPatient);
		this.doctorRepository.addPatient(AppSingleton.getInstance().getDoctor(), newPatient);
		AppSingleton.getInstance().getDoctor().getPatients().add(newPatient);
		PatientEvent patientEvent = new PatientEvent();
		patientEvent.setPatient(newPatient);
		patientEvent.setEvent(PatientEnum.ADD_PATIENT);
		newPatient.notifyObservers(patientEvent);
		this.dialog.dispose();
	}
}