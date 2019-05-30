package org.eok.medicalsupportsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Examination {

	private Patient patient;
	private Disease diagnose;
	private List<Symptom> symptomList = new ArrayList<Symptom>();
	private List<Therapy> therapies = new ArrayList<Therapy>();

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Disease getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(Disease diagnose) {
		this.diagnose = diagnose;
	}

	public List<Symptom> getSymptomList() {
		return symptomList;
	}

	public List<Therapy> getTherapies() {
		return therapies;
	}
}
