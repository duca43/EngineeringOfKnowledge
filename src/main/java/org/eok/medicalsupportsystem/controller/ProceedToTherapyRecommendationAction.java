package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.CbReasonerData;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.view.PatientDashboardPanel;
import org.eok.medicalsupportsystem.view.TherapyAndAdditionalProceduresPanel;

public class ProceedToTherapyRecommendationAction extends AbstractAction {

	private static final long serialVersionUID = 2083751112109807820L;
	private Disease disease;
	
	public ProceedToTherapyRecommendationAction(Disease disease) {
		this.disease = disease;  
		putValue(SHORT_DESCRIPTION, "Click to proceed to therapies and additional procedures recommendation");
		putValue(SMALL_ICON, new ImageIcon("resources/images/proceed-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		TherapyAndAdditionalProceduresPanel therapyAndAdditionalProceduresPanel = new TherapyAndAdditionalProceduresPanel(this.disease.getLabel());

		Patient currentPatient = AppSingleton.getInstance().getPatient();
		CbReasonerData reasonerData = new CbReasonerData();
		reasonerData.setDisease(this.disease.getName());
		reasonerData.setGender(currentPatient.getGender().toString());
		reasonerData.setRace(currentPatient.getRace().toString());
		reasonerData.setAge(currentPatient.getAge());
		
		therapyAndAdditionalProceduresPanel.generateTherapiesInfo(AppSingleton.getInstance().getTherapyCbReasonerApi().getResults(reasonerData));
		therapyAndAdditionalProceduresPanel.generateAdditionalProcedureInfo(AppSingleton.getInstance().getProcedureCbReasonerApi().getResults(reasonerData));
		
		((PatientDashboardPanel)AppSingleton.getInstance().getAppFrame().getDashboardPanel()).setCentralPanel(therapyAndAdditionalProceduresPanel);
	}
}