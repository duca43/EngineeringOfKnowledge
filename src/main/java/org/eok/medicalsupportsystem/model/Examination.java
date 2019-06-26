package org.eok.medicalsupportsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Examination {

	private UUID id;
	private Patient patient;
	private Disease diagnose;
	private List<Symptom> symptomList = new ArrayList<>();
	private List<String> therapies = new ArrayList<>();
	private List<String> additionalProcedures = new ArrayList<>();
	private String note;
	private LocalDate date;
	
	public Examination(String id, Patient patient, Disease diagnose, List<Symptom> symptomList, List<String> therapies,
			List<String> additionalProcedures, String note, LocalDate date) {
		this.id = UUID.fromString(id);
		this.patient = patient;
		this.diagnose = diagnose;
		this.symptomList = symptomList;
		this.therapies = therapies;
		this.additionalProcedures = additionalProcedures;
		this.note = note;
		this.date = date;
	}
	
	public Examination(Patient patient, Disease diagnose, List<Symptom> symptomList, List<String> therapies,
			List<String> additionalProcedures, String note, LocalDate date) {
		this.id = UUID.randomUUID();
		this.patient = patient;
		this.diagnose = diagnose;
		this.symptomList = symptomList;
		this.therapies = therapies;
		this.additionalProcedures = additionalProcedures;
		this.note = note;
		this.date = date;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	public void setSymptomList(List<Symptom> symptomList) {
		this.symptomList = symptomList;
	}
	public List<String> getTherapies() {
		return therapies;
	}
	public void setTherapies(List<String> therapies) {
		this.therapies = therapies;
	}
	public List<String> getAdditionalProcedures() {
		return additionalProcedures;
	}
	public void setAdditionalProcedures(List<String> additionalProcedures) {
		this.additionalProcedures = additionalProcedures;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Examination [id=" + id + ", patient=" + patient + ", diagnose=" + diagnose + ", symptomList="
				+ symptomList + ", therapies=" + therapies + ", additionalProcedures=" + additionalProcedures
				+ ", note=" + note + ", date=" + date + "]";
	}
}
