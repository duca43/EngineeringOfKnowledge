package org.eok.medicalsupportsystem.event;

import org.eok.medicalsupportsystem.model.Patient;

public class PatientEvent {
	
	public enum PatientEnum {
		ADD_PATIENT,
		DELETE_PATIENT
	}
	
	private Patient patient;
	private PatientEnum event;
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PatientEnum getEvent() {
		return event;
	}

	public void setEvent(PatientEnum event) {
		this.event = event;
	}
}