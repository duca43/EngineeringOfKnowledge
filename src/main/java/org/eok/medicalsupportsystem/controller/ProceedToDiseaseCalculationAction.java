package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.util.DiseaseComparator;
import org.eok.medicalsupportsystem.view.AdditionalSymptomCheckerPanel;

public class ProceedToDiseaseCalculationAction extends AbstractAction {

	private static final long serialVersionUID = -3255368193115200386L;
	private AdditionalSymptomCheckerPanel additionalSymptomCheckerPanel;
	
	public ProceedToDiseaseCalculationAction(AdditionalSymptomCheckerPanel symptomCheckerPanel) {
		this.additionalSymptomCheckerPanel = symptomCheckerPanel;
		putValue(NAME, "Proceed forward");
		putValue(SHORT_DESCRIPTION, "Click to start calculation of the most probable diseases");
		putValue(SMALL_ICON, new ImageIcon("resources/images/proceed-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Symptom> choosenSymptoms = additionalSymptomCheckerPanel.getChoosenSymptoms();
		List<Symptom> symptomsPatientDontHave = additionalSymptomCheckerPanel.getSymptomsPatientDontHave();
		List<Disease> diseases = AppSingleton.getInstance().getBayesReasonerApi().getDiseaseProbability(choosenSymptoms, symptomsPatientDontHave);
		diseases.sort(new DiseaseComparator());	
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getCalculatedDiseasesPanel().setChoosenSymptoms(choosenSymptoms);
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getCalculatedDiseasesPanel().setSymptomsPatientDontHave(symptomsPatientDontHave);
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getCalculatedDiseasesPanel().generateDiseasesLabels(diseases);
		SwingUtilities.updateComponentTreeUI(AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getCards());
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().switchToNextPanel();
	}
}