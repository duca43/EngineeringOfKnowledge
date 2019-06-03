package org.eok.medicalsupportsystem.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Symptom;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;

public class SymptomCheckerPanel extends JPanel {

	private static final long serialVersionUID = 2862711375769048305L;
	private JComboBox<Symptom> symptomSearch;
	private Map<String, Symptom> choosenSymptomMap;
	private JPanel symptomItemsPanel;

	/**
	 * Create the panel.
	 */
	public SymptomCheckerPanel() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[grow][grow][grow][][grow]", "[][14.00][][][grow][13.00][]"));
		
		JXLabel symptomCheckerMsg = new JXLabel();
		symptomCheckerMsg.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		symptomCheckerMsg.setText("Start your examination by checking patient symptoms");
		add(symptomCheckerMsg, "cell 0 0 5 1,alignx center");
		
		symptomSearch = new JComboBox<>();
		symptomCheckerMsg.setLabelFor(symptomSearch);
		symptomSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(symptomSearch, "cell 1 2 2 1,growx");

		List<Symptom> symptoms = AppSingleton.getInstance().getPrologConsultationApi().getSymptoms();
		Symptom[] symptomArray = new Symptom[symptoms.size()];
		symptomArray = symptoms.toArray(symptomArray);		
		DefaultComboBoxModel<Symptom> comboBoxModel = new DefaultComboBoxModel<Symptom>(symptomArray);
		symptomSearch.setModel(comboBoxModel);
		symptomSearch.setSelectedIndex(-1);

		AutoCompleteDecorator.decorate(symptomSearch, new ObjectToStringConverter() {

			@Override
			public String getPreferredStringForItem(Object item) {
				if (item == null) {
					return "";
				}
				return item.toString();
			}
		});
		
		JXButton btnAdd = new JXButton();
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Symptom symptom = (Symptom) SymptomCheckerPanel.this.symptomSearch.getSelectedItem();
				if (symptom == null || choosenSymptomMap.get(symptom.getName()) != null) {
					return;
				}
				SymptomCheckerPanel.this.choosenSymptomMap.put(symptom.getName(), symptom);
				SymptomCheckerPanel.this.generateSymptomItem(symptom);
				symptomSearch.setSelectedIndex(-1);					
			}
		});
		btnAdd.setText("Add");
		add(btnAdd, "cell 3 2");
		
		this.choosenSymptomMap = new HashMap<>();
		
		JXLabel lblRelated = new JXLabel();
		lblRelated.setFont(new Font("Comic Sans MS", Font.ITALIC, 15));
		lblRelated.setText("Related: under construciton");
		add(lblRelated, "cell 1 3 3 1");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 4 3 1,grow");
		
		symptomItemsPanel = new JPanel();
		scrollPane.setViewportView(symptomItemsPanel);
		symptomItemsPanel.setLayout(new MigLayout("wrap 2", "[grow,center]", "[]"));
		
		JXButton btnProceedForward = new JXButton(new ImageIcon("resources/images/proceed-icon.png"));
		btnProceedForward.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnProceedForward.setText("Proceed forward");
		add(btnProceedForward, "cell 1 6 3 1,alignx center");
	}
	
	public void generateSymptomItem(Symptom symptom) {
		JXLabel lblSymptom = new JXLabel();
		lblSymptom.setText(symptom.getName());
		lblSymptom.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		symptomItemsPanel.add(lblSymptom);
		JXButton btnRemoveSymtpom = new JXButton(new ImageIcon("resources/images/remove-icon.png"));
		btnRemoveSymtpom.setContentAreaFilled(false);
		btnRemoveSymtpom.setBorderPainted(false);
		btnRemoveSymtpom.setFocusPainted(false);
		btnRemoveSymtpom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRemoveSymtpom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SymptomCheckerPanel.this.choosenSymptomMap.remove(symptom.getName());
				SymptomCheckerPanel.this.symptomItemsPanel.remove(lblSymptom);
				SymptomCheckerPanel.this.symptomItemsPanel.remove(btnRemoveSymtpom);
			}
		});
		symptomItemsPanel.add(btnRemoveSymtpom);
	}
}