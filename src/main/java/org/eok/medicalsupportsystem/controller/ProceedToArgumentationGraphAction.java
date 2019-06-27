package org.eok.medicalsupportsystem.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Disease;

public class ProceedToArgumentationGraphAction extends AbstractAction {

	private static final long serialVersionUID = -4003106189201885745L;
	private Disease disease;

	public ProceedToArgumentationGraphAction() {
		putValue(NAME, "Show argumentation graph");
		putValue(SHORT_DESCRIPTION, "Click to show how program has found a solution for the given disease");
		putValue(SMALL_ICON, new ImageIcon("resources/images/show-graph-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (disease == null) {
			return;
		}
		
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getArgumentationGraphPanel().generateGraph(disease);
		SwingUtilities.updateComponentTreeUI(AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().getCards());
		AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel().switchToNextPanel();
	}
	
	public void setDisease(Disease disease) {
		this.disease = disease;
	}
}
