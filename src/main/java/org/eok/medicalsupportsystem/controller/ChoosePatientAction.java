package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.bayes.BayesReasonerApi;
import org.eok.medicalsupportsystem.view.AppFrame;
import org.eok.medicalsupportsystem.view.PatientDashboardPanel;

public class ChoosePatientAction extends AbstractAction {

	private static final long serialVersionUID = 6812921317602655126L;

	public ChoosePatientAction() {
		putValue(NAME, "Choose patient");
		putValue(SHORT_DESCRIPTION, "Click to choose current patient.");
		putValue(SMALL_ICON, new ImageIcon("resources/images/choose-patient-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AppSingleton.getInstance().setPatient(AppSingleton.getInstance()
												      	.getAppFrame()
													    .getMainDashboardPanel()
													    .getPatientListPanel()
													    .getPatientListScrollPane()
													    .getPatientList()
													    .getSelectedPatient());
		
		AppSingleton.getInstance().setBayesReasonerApi(new BayesReasonerApi(AppSingleton.getInstance().getPatient()));
		AppSingleton.getInstance().getAppFrame().setPatientDashboardPanel(new PatientDashboardPanel(AppSingleton.getInstance().getPatient()));
		AppSingleton.getInstance().getAppFrame().showDashboard(AppFrame.PATIENT_DASHBOARD);
	}
}