package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Examination;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.repository.ExaminationRepository;
import org.eok.medicalsupportsystem.util.Util;
import org.eok.medicalsupportsystem.view.AppFrame;
import org.eok.medicalsupportsystem.view.MainDashboardPanel;
import org.eok.medicalsupportsystem.view.TherapiesAndAdditionalProceduresPanel;

public class FinishExaminationAction extends AbstractAction {

	private static final long serialVersionUID = 8368118143758980043L;
	private TherapiesAndAdditionalProceduresPanel therapyAndAdditionalProceduresPanel;
	private ExaminationRepository examinationRepository;
	
	public FinishExaminationAction(TherapiesAndAdditionalProceduresPanel therapyAndAdditionalProceduresPanel) {
		this.therapyAndAdditionalProceduresPanel = therapyAndAdditionalProceduresPanel;
		this.examinationRepository = new ExaminationRepository();
		putValue(NAME, "Finish examination");
		putValue(SHORT_DESCRIPTION, "Click to finish the examination process");
		putValue(SMALL_ICON, new ImageIcon("resources/images/finish-examination-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Patient patient = AppSingleton.getInstance().getPatient();
		Disease diagnose = therapyAndAdditionalProceduresPanel.getDisease();
		List<Symptom> symptomList = therapyAndAdditionalProceduresPanel.getChoosenSymptoms();
		List<String> therapies = therapyAndAdditionalProceduresPanel.getSelectedTherapies();
		List<String> additionalProcedures = therapyAndAdditionalProceduresPanel.getSelectedAdditionalProcedures();
		String note = therapyAndAdditionalProceduresPanel.getNote();
		Examination examination = new Examination(patient, diagnose, symptomList, therapies, additionalProcedures, note, LocalDate.now());
		this.examinationRepository.save(examination);
		
		List<String[]> additionalProceduresCases = new ArrayList<>();
		additionalProcedures.stream().forEach(additionalProcedure -> {
			String[] procedureCase = new String[] 
					{ diagnose.getName(), patient.getGender().toString(), patient.getRace().toString(), String.valueOf(patient.getAge()), additionalProcedure};
			additionalProceduresCases.add(procedureCase);
		});
		Util.addProcedureCase(additionalProceduresCases);
		List<String[]> therapiesCases = new ArrayList<>();
		therapies.stream().forEach(therapy -> {
			String[] therapyCase = new String[] 
					{ diagnose.getName(), patient.getGender().toString(), patient.getRace().toString(), String.valueOf(patient.getAge()), therapy};
			therapiesCases.add(therapyCase);
		});
		Util.addTherapyCase(therapiesCases);
		
		AppSingleton.getInstance().getAppFrame().setMainDashboardPanel(new MainDashboardPanel());
		AppSingleton.getInstance().getAppFrame().showDashboard(AppFrame.MAIN_DASHBOARD);
	}
}