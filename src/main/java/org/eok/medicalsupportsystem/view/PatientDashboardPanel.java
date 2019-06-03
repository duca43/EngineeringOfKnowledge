package org.eok.medicalsupportsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.controller.OpenMainDashboardAction;
import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXButton;

public class PatientDashboardPanel extends JPanel {

	private static final long serialVersionUID = 1117327672646374420L;

	/**
	 * Create the panel.
	 */
	public PatientDashboardPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		panel.setLayout(new MigLayout("", "[grow]", "[][grow][grow]"));
		
		JXButton btnOpenMainDashboard = new JXButton(new OpenMainDashboardAction());
		panel.add(btnOpenMainDashboard, "cell 0 0,alignx center");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, "cell 0 1,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		JXLabel lblPatientInfo = new JXLabel();
		lblPatientInfo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		lblPatientInfo.setText("Patient info:");
		panel_1.add(lblPatientInfo, "cell 0 0,alignx center");
		
		PatientDetailsFields patientDetailsFields = new PatientDetailsFields(AppSingleton.getInstance().getPatient());
		panel_1.add(patientDetailsFields, "cell 0 1,grow");
		
		SymptomCheckerPanel symptomCheckerPanel = new SymptomCheckerPanel();
		add(symptomCheckerPanel, BorderLayout.CENTER);
		
		CalculatedDiseasesPanel calculatedDiseasesPanel = new CalculatedDiseasesPanel();
		add(calculatedDiseasesPanel, BorderLayout.EAST);

	}

}
