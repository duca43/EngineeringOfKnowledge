package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.view.AddNewPatientDialog;

public class OpenAddNewPatientDialogAction extends AbstractAction {

	private static final long serialVersionUID = 8090175297404135426L;

	public OpenAddNewPatientDialogAction() {
		putValue(NAME, "Add new patient");
		putValue(SHORT_DESCRIPTION, "Click to add new patient in your evidence");
		putValue(SMALL_ICON, new ImageIcon("resources/images/add-patient-icon.png"));
	}
	
	public void actionPerformed(ActionEvent e) {
		AddNewPatientDialog addNewPatientDialog = new AddNewPatientDialog();
		addNewPatientDialog.setVisible(true);
	}
}