package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.CbReasonerData;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.model.Symptom;

public class ProceedToTherapyRecommendationAction extends AbstractAction {

	private static final long serialVersionUID = 2083751112109807820L;
	private Disease disease;
	private List<Symptom> choosenSymptoms;
	private List<Symptom> symptomsPatientDontHave;
	
	public ProceedToTherapyRecommendationAction(Disease disease, List<Symptom> choosenSymptoms, List<Symptom> symptomsPatientDontHave) {
		this.disease = disease;
		this.choosenSymptoms = choosenSymptoms;
		this.symptomsPatientDontHave = symptomsPatientDontHave;
		putValue(SHORT_DESCRIPTION, "Click to proceed to therapies and additional procedures recommendation");
		putValue(SMALL_ICON, new ImageIcon("resources/images/proceed-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Patient currentPatient = AppSingleton.getInstance().getPatient();
		CbReasonerData reasonerData = new CbReasonerData();
		reasonerData.setDisease(this.disease.getName());
		reasonerData.setGender(currentPatient.getGender().toString());
		reasonerData.setRace(currentPatient.getRace().toString());
		reasonerData.setAge(currentPatient.getAge());
		
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getTherapiesAndAdditionalProceduresPanel().setDisease(disease);
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getTherapiesAndAdditionalProceduresPanel().setChoosenSymptoms(choosenSymptoms);
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getTherapiesAndAdditionalProceduresPanel().setSymptomsPatientDontHave(symptomsPatientDontHave);
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getTherapiesAndAdditionalProceduresPanel().generateTherapiesInfo(AppSingleton.getInstance().getTherapyCbReasonerApi().getResults(reasonerData));
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getTherapiesAndAdditionalProceduresPanel().generateAdditionalProcedureInfo(AppSingleton.getInstance().getProcedureCbReasonerApi().getResults(reasonerData));
		SwingUtilities.updateComponentTreeUI(AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getCards());
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().switchToNextPanel();		
	}
}