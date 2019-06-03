package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.util.DiseaseComparator;
import org.eok.medicalsupportsystem.view.CalculatedDiseasesPanel;
import org.eok.medicalsupportsystem.view.PatientDashboardPanel;
import org.eok.medicalsupportsystem.view.SymptomCheckerPanel;

public class ProceedToDiseaseCalculationAction extends AbstractAction {

	private static final long serialVersionUID = -3255368193115200386L;
	private SymptomCheckerPanel symptomCheckerPanel;
	
	public ProceedToDiseaseCalculationAction(SymptomCheckerPanel symptomCheckerPanel) {
		this.symptomCheckerPanel = symptomCheckerPanel;
		putValue(NAME, "Proceed forward");
		putValue(SHORT_DESCRIPTION, "Click to start calculation of the most probable diseases");
		putValue(SMALL_ICON, new ImageIcon("resources/images/proceed-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CalculatedDiseasesPanel calculatedDiseasesPanel = new CalculatedDiseasesPanel();
		List<Symptom> choosenSymptoms = symptomCheckerPanel.getChoosenSymptoms();
		List<Disease> diseases = AppSingleton.getInstance().getBayesReasonerApi().getDiseaseProbability(choosenSymptoms);
		diseases.sort(new DiseaseComparator());
		calculatedDiseasesPanel.generateDiseasesLabels(diseases);
		((PatientDashboardPanel)AppSingleton.getInstance().getAppFrame().getDashboardPanel()).setSymptomCheckerPanel(this.symptomCheckerPanel);
		((PatientDashboardPanel)AppSingleton.getInstance().getAppFrame().getDashboardPanel()).setCalculatedDiseasesPanel(calculatedDiseasesPanel);
		((PatientDashboardPanel)AppSingleton.getInstance().getAppFrame().getDashboardPanel()).setCentralPanel(calculatedDiseasesPanel);
	}
}