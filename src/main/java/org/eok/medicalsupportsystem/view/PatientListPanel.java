package org.eok.medicalsupportsystem.view;

import org.eok.medicalsupportsystem.controller.OpenAddNewPatientDialogAction;
import org.eok.medicalsupportsystem.controller.DeletePatientAction;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.JXSearchField.SearchMode;

import net.miginfocom.swing.MigLayout;

public class PatientListPanel extends JXPanel {

	private static final long serialVersionUID = 1801681149197624094L;
	private PatientListScrollPane patientListScrollPane;
	
	public PatientListPanel() {
		this.patientListScrollPane = new PatientListScrollPane();
		// this.setPreferredSize(new Dimension((int)(AppFrame.getScreenDimension().width / 8), 0));
		this.setLayout(new MigLayout("", "[41.00,grow][28.00]", "[][grow][]"));
		
		JXSearchField searchField = new JXSearchField();
		searchField.setSearchMode(SearchMode.REGULAR);
		searchField.setPrompt("Search patients");
		this.add(searchField, "cell 0 0 2 1,growx");
		
		patientListScrollPane = new PatientListScrollPane();
		this.add(patientListScrollPane, "cell 0 1 2 1,grow");
		
		JXButton btnAddPatient = new JXButton(new OpenAddNewPatientDialogAction());
		this.add(btnAddPatient, "cell 0 2");
		
		JXButton btnDeletePatient = new JXButton(new DeletePatientAction());
		this.add(btnDeletePatient, "cell 1 2");
	}

	public PatientListScrollPane getPatientListScrollPane() {
		return patientListScrollPane;
	}
}