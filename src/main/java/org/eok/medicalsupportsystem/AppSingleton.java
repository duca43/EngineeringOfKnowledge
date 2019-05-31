package org.eok.medicalsupportsystem;

import org.eok.medicalsupportsystem.model.Doctor;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.view.AppFrame;

public class AppSingleton {
	
	private static AppSingleton instance = new AppSingleton();
	private Doctor doctor;
	private Patient patient;
	private AppFrame appFrame;
	
	private AppSingleton() { }
	
	public static AppSingleton getInstance() {
		return instance;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public AppFrame getAppFrame() {
		return appFrame;
	}

	public void setAppFrame(AppFrame appFrame) {
		this.appFrame = appFrame;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}