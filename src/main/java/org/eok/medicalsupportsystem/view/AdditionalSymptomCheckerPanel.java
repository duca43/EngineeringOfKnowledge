package org.eok.medicalsupportsystem.view;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.controller.BackAction;
import org.eok.medicalsupportsystem.controller.ProceedToDiseaseCalculationAction;
import org.eok.medicalsupportsystem.model.Symptom;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;

public class AdditionalSymptomCheckerPanel extends JPanel {

	private static final long serialVersionUID = 5152550478517399319L;
	private Map<String, Symptom> choosenSymptomsMap;
	private Map<String, Symptom> relatedSymptomMap;
	private Map<String, Symptom> symptomsPatientDontHaveMap;
	private JPanel symptomItemsPanel;
	
	/**
	 * Create the panel.
	 */
	public AdditionalSymptomCheckerPanel() {
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][grow][]"));
		
		JXLabel lblCheckAdditionalSymptoms = new JXLabel();
		lblCheckAdditionalSymptoms.setText("Check additional symptoms in case you missed some");
		lblCheckAdditionalSymptoms.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		add(lblCheckAdditionalSymptoms, "cell 1 0 2 1,alignx center");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 2 2 1, grow");
		
		symptomItemsPanel = new JPanel();
		scrollPane.setViewportView(symptomItemsPanel);
		symptomItemsPanel.setLayout(new MigLayout("wrap 2", "[grow,center]", "[]"));		

		JXButton btnBack = new JXButton(new BackAction());
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnBack, "cell 1 3");
		
		JXButton btnProceedForward = new JXButton(new ProceedToDiseaseCalculationAction(this));
		btnProceedForward.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnProceedForward, "cell 2 3,alignx right");
	}
	
	private void generateSymptomItem(Symptom symptom) {
		JXLabel lblSymptom = new JXLabel();
		lblSymptom.setText(symptom.getLabel());
		lblSymptom.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		symptomItemsPanel.add(lblSymptom);
		JPanel buttonPane = new JPanel();
		JXButton btnHasSymtpom = this.generateButton("resources/images/add-icon.png");
		JXButton btnHasNoSymtpom = this.generateButton("resources/images/remove-icon.png");
		btnHasSymtpom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				choosenSymptomsMap.put(symptom.getName(), symptom);
				symptomItemsPanel.remove(lblSymptom);
				symptomItemsPanel.remove(buttonPane);
				extractRelated(symptom);
				AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getSymptomCheckerPanel().chooseSymptom(symptom);
			}
		});
		buttonPane.add(btnHasSymtpom);
		btnHasNoSymtpom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				symptomsPatientDontHaveMap.put(symptom.getName(), symptom);
				symptomItemsPanel.remove(lblSymptom);
				symptomItemsPanel.remove(buttonPane);
			}
		});
		buttonPane.add(btnHasNoSymtpom);
		symptomItemsPanel.add(buttonPane);
	}
	
	private JXButton generateButton(String iconPath) {
		JXButton button = new JXButton(new ImageIcon(iconPath));
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return button;
	}

	public List<Symptom> getChoosenSymptoms() {
		return new ArrayList<>(this.choosenSymptomsMap.values());
	}

	public void setChoosenSymptomsMap(Map<String, Symptom> choosenSymptomsMap) {
		this.choosenSymptomsMap = choosenSymptomsMap;
	}
	
	public List<Symptom> getSymptomsPatientDontHave() {
		return new ArrayList<>(this.symptomsPatientDontHaveMap.values());
	}

	public void findNotChoosenRelatedSymptoms() {
		this.symptomItemsPanel.removeAll();
		this.relatedSymptomMap = new HashMap<>();
		this.symptomsPatientDontHaveMap = new HashMap<>();
		for (Entry<String, Symptom> choosenSymptoms : this.choosenSymptomsMap.entrySet()) {
			extractRelated(choosenSymptoms.getValue());	
		}
	}

	private void extractRelated(Symptom symptom) {
		List<Symptom> relatedSymptoms = AppSingleton.getInstance().getPrologConsultationApi().getSimilarSymptomsFromPrologBase(symptom);
		for (Symptom relatedSymptom : relatedSymptoms) {
			if (!choosenSymptomsMap.containsKey(relatedSymptom.getName()) && !relatedSymptomMap.containsKey(relatedSymptom.getName())) {
				relatedSymptomMap.put(relatedSymptom.getName(), relatedSymptom);
				this.generateSymptomItem(relatedSymptom);
			}
		}
	}

	public Map<String, Symptom> getChoosenSymptomsMap() {
		return choosenSymptomsMap;
	}

	public Map<String, Symptom> getSymptomsPatientDontHaveMap() {
		return symptomsPatientDontHaveMap;
	}
}
