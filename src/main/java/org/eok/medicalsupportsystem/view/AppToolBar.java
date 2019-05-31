package org.eok.medicalsupportsystem.view;

import javax.swing.JToolBar;

import org.eok.medicalsupportsystem.controller.AddNewPatientAction;

public class AppToolBar extends JToolBar {
	private static final long serialVersionUID = 841018468557681463L;
	
	public AppToolBar() {
		add(new AddNewPatientAction());
	}
}