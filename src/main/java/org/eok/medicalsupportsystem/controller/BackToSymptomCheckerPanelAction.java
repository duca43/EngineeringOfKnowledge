package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.view.PatientDashboardPanel;

public class BackToSymptomCheckerPanelAction extends AbstractAction {

	private static final long serialVersionUID = 1502722559714053783L;

	public BackToSymptomCheckerPanelAction() {
		putValue(NAME, "Back");
		putValue(SMALL_ICON, new ImageIcon("resources/images/back-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		((PatientDashboardPanel)AppSingleton.getInstance().getAppFrame().getDashboardPanel()).setCentralPanel(((PatientDashboardPanel)AppSingleton.getInstance().getAppFrame().getDashboardPanel()).getSymptomCheckerPanel());
	}
}