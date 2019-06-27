package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.view.SymptomCheckerPanel;

public class ProceedToAdditionalSymptomCheckingAction extends AbstractAction {

	private static final long serialVersionUID = -4925569746810726711L;
	private SymptomCheckerPanel symptomCheckerPanel;
	
	public ProceedToAdditionalSymptomCheckingAction(SymptomCheckerPanel symptomCheckerPanel) {
		this.symptomCheckerPanel = symptomCheckerPanel;
		putValue(NAME, "Proceed forward");
		putValue(SHORT_DESCRIPTION, "Click to start additional symptoms check");
		putValue(SMALL_ICON, new ImageIcon("resources/images/proceed-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getAdditionalSymptomCheckerPanel().setChoosenSymptomsMap(symptomCheckerPanel.getChoosenSymptomsMap());
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getAdditionalSymptomCheckerPanel().findNotChoosenRelatedSymptoms();
		SwingUtilities.updateComponentTreeUI(AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getCards());
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().switchToNextPanel();
	}
}