package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.view.AppFrame;

public class OpenMainDashboardAction extends AbstractAction {

	private static final long serialVersionUID = -6762572709228150899L;

	public OpenMainDashboardAction() {
		putValue(NAME, "Open main dashboard");
		putValue(SHORT_DESCRIPTION, "Click to open main dashboard.");
		putValue(SMALL_ICON, new ImageIcon("resources/images/home-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		AppSingleton.getInstance().getAppFrame().showDashboard(AppFrame.MAIN_DASHBOARD);
	}
}