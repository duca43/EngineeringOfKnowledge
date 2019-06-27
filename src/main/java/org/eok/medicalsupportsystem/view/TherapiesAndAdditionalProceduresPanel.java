package org.eok.medicalsupportsystem.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

import org.eok.medicalsupportsystem.controller.BackAction;
import org.eok.medicalsupportsystem.controller.FinishExaminationAction;
import org.eok.medicalsupportsystem.controller.ProceedToArgumentationGraphAction;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.util.Util;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTextArea;

import net.miginfocom.swing.MigLayout;

public class TherapiesAndAdditionalProceduresPanel extends JPanel {

	private static final long serialVersionUID = 2665412194637171275L;
	private JPanel therapiesPanel;
	private JPanel additionalProceduresPanel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JXLabel lblDisease;
	private Disease disease;
	private List<Symptom> choosenSymptoms;
	private List<Symptom> symptomsPatientDontHave;
	private JXButton btnFinishExamination;
	private List<JCheckBox> therapiesCheckBoxes = new ArrayList<>();
	private List<JCheckBox> additionalProceduresCheckBoxes = new ArrayList<>();
	private static final Color THERAPIES_COLOR = new Color(135, 206, 250);
	private static final Color ADDITIONAL_PROCEDURES_COLOR = new Color(72, 209, 204);
	private JXLabel lblFinalNote;
	private JTextArea noteTextArea;
	private JScrollPane noteScrollPane;
	private JXButton btnShowGraph;
	ProceedToArgumentationGraphAction proceedToArgumentationGraphAction;

	/**
	 * Create the panel.
	 */
	public TherapiesAndAdditionalProceduresPanel() {
		setLayout(new MigLayout("", "[][grow][grow][grow][]", "[][][][grow][grow][][grow][][]"));
		
		lblDisease = new JXLabel();
		lblDisease.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
		add(lblDisease, "cell 1 0 3 1,alignx center");
		
		JXLabel lblRecommendedTherapies = new JXLabel();
		lblRecommendedTherapies.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblRecommendedTherapies.setText("Recommended therapies");
		add(lblRecommendedTherapies, "cell 1 1,alignx center");
		
		JXLabel lblRecommendedAdditionalProcedures = new JXLabel();
		lblRecommendedAdditionalProcedures.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblRecommendedAdditionalProcedures.setText("Recommended additional procedures");
		add(lblRecommendedAdditionalProcedures, "cell 3 1,alignx center");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 3 1 2,grow");
		
		therapiesPanel = new JPanel();
		scrollPane.setViewportView(therapiesPanel);
		therapiesPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		therapiesPanel.setBackground(THERAPIES_COLOR);
		therapiesPanel.setLayout(new MigLayout("wrap 2", "[grow]", "[grow,center]"));
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 3 3 1 2,grow");
		
		additionalProceduresPanel = new JPanel();
		scrollPane_1.setViewportView(additionalProceduresPanel);
		additionalProceduresPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		additionalProceduresPanel.setBackground(ADDITIONAL_PROCEDURES_COLOR);
		additionalProceduresPanel.setLayout(new MigLayout("wrap 2", "[grow,center]", "[grow,center]"));
		
		lblFinalNote = new JXLabel();
		add(lblFinalNote, "cell 1 5");
		lblFinalNote.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		lblFinalNote.setText("Final note:");
		
		noteScrollPane = new JScrollPane();
		noteScrollPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		add(noteScrollPane, "cell 1 6 3 1,grow");
		
		noteTextArea = new JXTextArea();
		noteTextArea.setRows(1);
		noteTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		noteTextArea.setBackground(new Color(255, 255, 240));
		noteScrollPane.setViewportView(noteTextArea);
		
		JXButton btnBack = new JXButton(new BackAction());
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnBack, "cell 1 8,alignx left");
		
		this.proceedToArgumentationGraphAction = new ProceedToArgumentationGraphAction();
		btnShowGraph = new JXButton(proceedToArgumentationGraphAction);
		btnShowGraph.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnShowGraph, "flowx,cell 3 8,alignx center");
		
		btnFinishExamination = new JXButton(new FinishExaminationAction(this));
		btnFinishExamination.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnFinishExamination, "cell 3 8 2 1,alignx right");
	}
	
	public void generateTherapiesInfo(List<String> therapies) {
		therapiesPanel.removeAll();
		therapies.stream().forEach(therapy -> {
			JCheckBox checkBox = this.generateCheckBox(therapy, THERAPIES_COLOR);
			this.therapiesPanel.add(checkBox, "alignx center");
			this.therapiesPanel.add(this.generateLabel(therapy), "alignx left");
			this.therapiesCheckBoxes.add(checkBox);
		});
	}
	
	public void generateAdditionalProcedureInfo(List<String> additionalProcedures) {
		additionalProceduresPanel.removeAll();
		additionalProcedures.stream().forEach(additionalProcedure -> {
			JCheckBox checkBox = this.generateCheckBox(additionalProcedure, ADDITIONAL_PROCEDURES_COLOR);
			this.additionalProceduresPanel.add(checkBox, "alignx center");
			this.additionalProceduresPanel.add(this.generateLabel(additionalProcedure), "alignx left");
			this.additionalProceduresCheckBoxes.add(checkBox);
		});
	}
	
	private JCheckBox generateCheckBox(String element, Color color) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setName(element);
		checkBox.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		checkBox.setBackground(color);
		return checkBox;
	}
	
	private JXLabel generateLabel(String element) {
		JXLabel label = new JXLabel(Util.filterNames(element));
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		return label;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
		this.proceedToArgumentationGraphAction.setDisease(disease);
		lblDisease.setText(disease.getLabel());
		SwingUtilities.updateComponentTreeUI(this);
	}

	public List<Symptom> getChoosenSymptoms() {
		return choosenSymptoms;
	}
	
	public void setChoosenSymptoms(List<Symptom> choosenSymptoms) {
		this.choosenSymptoms = choosenSymptoms;
	}

	public List<Symptom> getSymptomsPatientDontHave() {
		return symptomsPatientDontHave;
	}

	public void setSymptomsPatientDontHave(List<Symptom> symptomsPatientDontHave) {
		this.symptomsPatientDontHave = symptomsPatientDontHave;
	}

	public List<String> getSelectedTherapies() {
		return this.therapiesCheckBoxes.stream()
									   .filter(checkBox -> checkBox.isSelected())
									   .map(JCheckBox::getName)
									   .collect(Collectors.toList());
	}
	
	public List<String> getSelectedAdditionalProcedures() {
		return this.additionalProceduresCheckBoxes.stream()
												  .filter(checkBox -> checkBox.isSelected())
												  .map(JCheckBox::getName)
												  .collect(Collectors.toList());
	}
	
	public String getNote() {
		return this.noteTextArea.getText().replaceAll("\n", " ");
	}
}