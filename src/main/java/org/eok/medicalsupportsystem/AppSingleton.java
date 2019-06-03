package org.eok.medicalsupportsystem;

import org.eok.medicalsupportsystem.bayes.BayesReasonerApi;
import org.eok.medicalsupportsystem.model.Doctor;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.prolog.PrologConsultationApi;
import org.eok.medicalsupportsystem.view.AppFrame;

public class AppSingleton {

	private static AppSingleton instance = new AppSingleton();
	private Doctor doctor;
	private Patient patient;
	private AppFrame appFrame;
	private PrologConsultationApi prologConsultationApi;
	private BayesReasonerApi bayesReasonerApi;

	private AppSingleton() {
	}

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

	public PrologConsultationApi getPrologConsultationApi() {
		return prologConsultationApi;
	}

	public void setPrologConsultationApi(PrologConsultationApi prologConsultationApi) {
		this.prologConsultationApi = prologConsultationApi;
	}

	public BayesReasonerApi getBayesReasonerApi() {
		return bayesReasonerApi;
	}

	public void setBayesReasonerApi(BayesReasonerApi bayesReasonerApi) {
		this.bayesReasonerApi = bayesReasonerApi;
	}
}