package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.view.MainDashboardPanel;
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
		AppSingleton.getInstance().setPatient(((MainDashboardPanel)AppSingleton.getInstance().
																			    getAppFrame().
																			    getDashboardPanel()).
																			    getPatientListPanel().
																			    getPatientListScrollPane().
																			    getPatientList().
																			    getSelectedPatient());
		AppSingleton.getInstance().getAppFrame().setDashboardPanel(new PatientDashboardPanel());
	}
}