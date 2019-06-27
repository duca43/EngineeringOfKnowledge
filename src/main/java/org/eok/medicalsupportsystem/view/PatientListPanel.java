package org.eok.medicalsupportsystem.view;

import org.eok.medicalsupportsystem.controller.OpenAddNewPatientDialogAction;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import org.eok.medicalsupportsystem.controller.DeletePatientAction;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;
import org.jdesktop.swingx.sort.RowFilters;

import net.miginfocom.swing.MigLayout;

public class PatientListPanel extends JXPanel {

	private static final long serialVersionUID = 1801681149197624094L;
	private PatientListScrollPane patientListScrollPane;
	
	public PatientListPanel() {
		this.patientListScrollPane = new PatientListScrollPane();
		this.setLayout(new MigLayout("", "[grow][]", "[][grow][]"));
		
		JXSearchField searchField = new JXSearchField();
		searchField.setSearchMode(SearchMode.INSTANT);
		searchField.setPrompt("Search patients");
		searchField.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		searchField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				patientListScrollPane.getPatientList().setRowFilter(RowFilters.regexFilter(Pattern.compile(".*" + searchField.getText() + ".*")));
				patientListScrollPane.getPatientList().getSearchable().search(Pattern.compile(".*" + searchField.getText() + ".*"));
			}
		});
		this.add(searchField, "cell 0 0 2 1,growx");
		
		patientListScrollPane = new PatientListScrollPane();
		this.add(patientListScrollPane, "cell 0 1 2 1,grow");
		
		JXButton btnAddPatient = new JXButton(new OpenAddNewPatientDialogAction());
		btnAddPatient.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		this.add(btnAddPatient, "cell 0 2");
		
		JXButton btnDeletePatient = new JXButton(new DeletePatientAction());
		btnDeletePatient.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		this.add(btnDeletePatient, "cell 1 2");
	}

	public PatientListScrollPane getPatientListScrollPane() {
		return patientListScrollPane;
	}
}