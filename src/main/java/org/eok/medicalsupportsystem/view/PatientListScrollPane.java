package org.eok.medicalsupportsystem.view;

import java.awt.Dimension;

import javax.swing.JScrollPane;

public class PatientListScrollPane extends JScrollPane {

	private static final long serialVersionUID = -8467606223400474146L;
	private PatientList patientList;
	
	public PatientListScrollPane() {
		this.patientList = new PatientList();
		this.setViewportView(patientList);
		this.setPreferredSize(new Dimension((int)(AppFrame.getScreenDimension().width / 8), 0));
	}
}