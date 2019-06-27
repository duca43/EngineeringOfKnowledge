package org.eok.medicalsupportsystem.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.controller.ProceedToAdditionalSymptomCheckingAction;
import org.eok.medicalsupportsystem.model.Symptom;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import net.miginfocom.swing.MigLayout;

public class SymptomCheckerPanel extends JPanel {

	private static final long serialVersionUID = 2862711375769048305L;
	private JComboBox<Symptom> symptomSearch;
	private Map<String, Symptom> choosenSymptomsMap;
	private Map<String, Symptom> allSymptomsMap;
	private JPanel symptomItemsPanel;
	private JPanel relatedPanel;

	/**
	 * Create the panel.
	 */
	public SymptomCheckerPanel() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow][grow][grow][][grow]", "[][14.00][][][grow][13.00][]"));
		
		JXLabel symptomCheckerMsg = new JXLabel();
		symptomCheckerMsg.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		symptomCheckerMsg.setText("Start your examination by checking patient symptoms");
		add(symptomCheckerMsg, "cell 0 0 5 1,alignx center");
		
		symptomSearch = new JComboBox<>();
		symptomCheckerMsg.setLabelFor(symptomSearch);
		symptomSearch.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		add(symptomSearch, "cell 1 2 2 1,growx");

		this.allSymptomsMap = AppSingleton.getInstance().getPrologConsultationApi().getSymptomsFromPrologBase();
		List<Symptom> symptoms = new ArrayList<>(this.allSymptomsMap.values());
		Symptom[] symptomArray = new Symptom[symptoms.size()];
		symptomArray = symptoms.toArray(symptomArray);		
		DefaultComboBoxModel<Symptom> comboBoxModel = new DefaultComboBoxModel<Symptom>(symptomArray);
		this.symptomSearch.setModel(comboBoxModel);
		this.symptomSearch.setSelectedIndex(-1);

		AutoCompleteDecorator.decorate(symptomSearch, new ObjectToStringConverter() {

			@Override
			public String getPreferredStringForItem(Object item) {
				if (item == null) {
					return "";
				}
				return item.toString();
			}
		});
		
		this.symptomSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Symptom selectedSymptom = (Symptom) SymptomCheckerPanel.this.symptomSearch.getSelectedItem();
				if (selectedSymptom != null) {
					for (int i = SymptomCheckerPanel.this.relatedPanel.getComponentCount() - 1; i > 0; i--) {
						SymptomCheckerPanel.this.relatedPanel.remove(i);
					}
					List<Symptom> similarSymptoms = AppSingleton.getInstance().getPrologConsultationApi().getSimilarSymptomsFromPrologBase(selectedSymptom);
					for (Symptom similarSymptom : similarSymptoms) {
						if (SymptomCheckerPanel.this.choosenSymptomsMap.get(similarSymptom.getName()) == null) {
							SymptomCheckerPanel.this.createRelatedSymptomLink(similarSymptom);							
						}
					}					
				}
			}
		});
		
		JXButton btnAdd = new JXButton();
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Symptom symptom = (Symptom) SymptomCheckerPanel.this.symptomSearch.getSelectedItem();
				if (symptom == null || choosenSymptomsMap.get(symptom.getName()) != null) {
					return;
				}
				chooseSymptom(symptom);
				symptomSearch.setSelectedIndex(-1);					
			}
		});
		btnAdd.setText("Add");
		add(btnAdd, "cell 3 2");
		
		this.choosenSymptomsMap = new HashMap<>();
		
		relatedPanel = new JPanel();
		add(relatedPanel, "cell 1 3 3 1,alignx leading,aligny center");
		relatedPanel.setLayout(new GridLayout(0, 4, 10, 0));
		
		JXLabel lblRelated = new JXLabel();
		lblRelated.setHorizontalAlignment(SwingConstants.CENTER);
		relatedPanel.add(lblRelated);
		lblRelated.setFont(new Font("Comic Sans MS", Font.ITALIC, 17));
		lblRelated.setText("Related:");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 4 3 1,grow");
		
		symptomItemsPanel = new JPanel();
		scrollPane.setViewportView(symptomItemsPanel);
		symptomItemsPanel.setLayout(new MigLayout("wrap 2", "[grow,center]", "[]"));
		
		JXButton btnProceedForward = new JXButton(new ProceedToAdditionalSymptomCheckingAction(this));
		btnProceedForward.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnProceedForward, "cell 1 6 3 1,alignx right");
	}
	
	private void generateSymptomItem(Symptom symptom) {
		JXLabel lblSymptom = new JXLabel();
		lblSymptom.setText(symptom.getLabel());
		lblSymptom.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		symptomItemsPanel.add(lblSymptom);
		JXButton btnRemoveSymtpom = new JXButton(new ImageIcon("resources/images/remove-icon.png"));
		btnRemoveSymtpom.setContentAreaFilled(false);
		btnRemoveSymtpom.setBorderPainted(false);
		btnRemoveSymtpom.setFocusPainted(false);
		btnRemoveSymtpom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemoveSymtpom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SymptomCheckerPanel.this.choosenSymptomsMap.remove(symptom.getName());
				SymptomCheckerPanel.this.symptomItemsPanel.remove(lblSymptom);
				SymptomCheckerPanel.this.symptomItemsPanel.remove(btnRemoveSymtpom);
			}
		});
		symptomItemsPanel.add(btnRemoveSymtpom);
	}
	
	private void createRelatedSymptomLink(Symptom symptom) {
		JXButton button = new JXButton(symptom.getLabel());
		button.setForeground(Color.RED);
		button.setName(symptom.getName());
		button.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		button.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		button.setFocusPainted(false);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setHorizontalAlignment(SwingConstants.CENTER);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseSymptom(symptom);
				symptomSearch.setSelectedIndex(-1);
				for (int i = SymptomCheckerPanel.this.relatedPanel.getComponentCount() - 1; i > 0; i--) {
					if (SymptomCheckerPanel.this.relatedPanel.getComponent(i).getName().equals(button.getName())) {
						SymptomCheckerPanel.this.relatedPanel.remove(i);
						break;
					}
				}
			}
		});
		relatedPanel.add(button);
	}
	
	public List<Symptom> getChoosenSymptoms() {
		return new ArrayList<>(this.choosenSymptomsMap.values());
	}

	public Map<String, Symptom> getChoosenSymptomsMap() {
		return choosenSymptomsMap;
	}

	public void chooseSymptom(Symptom symptom) {
		SymptomCheckerPanel.this.choosenSymptomsMap.put(symptom.getName(), symptom);
		SymptomCheckerPanel.this.generateSymptomItem(symptom);
	}
}