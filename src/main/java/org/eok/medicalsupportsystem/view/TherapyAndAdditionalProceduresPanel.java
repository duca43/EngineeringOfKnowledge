package org.eok.medicalsupportsystem.view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.controller.BackToCalculatedDiseasesPanelAction;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;

public class TherapyAndAdditionalProceduresPanel extends JPanel {

	private static final long serialVersionUID = 2665412194637171275L;
	private JPanel therapiesPanel;
	private JPanel additionalProceduresPanel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JXLabel lblDisease;
	private JXButton btnBack;

	/**
	 * Create the panel.
	 */
	public TherapyAndAdditionalProceduresPanel(String diseaseLabel) {
		setLayout(new MigLayout("", "[][grow][grow][][grow][grow][]", "[][15.00][][][grow][]"));
		
		lblDisease = new JXLabel(diseaseLabel);
		lblDisease.setFont(new Font("Comic Sans MS", Font.BOLD, 32));
		add(lblDisease, "cell 1 0 5 1,alignx center");
		
		JXLabel lblRecommendedTherapies = new JXLabel();
		lblRecommendedTherapies.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblRecommendedTherapies.setText("Recommended therapies");
		add(lblRecommendedTherapies, "cell 1 2 2 1,alignx center");
		
		JXLabel lblRecommendedAdditionalProcedures = new JXLabel();
		lblRecommendedAdditionalProcedures.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblRecommendedAdditionalProcedures.setText("Recommended additional procedures");
		add(lblRecommendedAdditionalProcedures, "cell 4 2 2 1,alignx center");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 4 2 1,grow");
		
		therapiesPanel = new JPanel();
		scrollPane.setViewportView(therapiesPanel);
		therapiesPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		therapiesPanel.setBackground(new Color(135, 206, 250));
		therapiesPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		scrollPane_1 = new JScrollPane();
		add(scrollPane_1, "cell 4 4 2 1,grow");
		
		additionalProceduresPanel = new JPanel();
		scrollPane_1.setViewportView(additionalProceduresPanel);
		additionalProceduresPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		additionalProceduresPanel.setBackground(new Color(72, 209, 204));
		additionalProceduresPanel.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JXButton btnBack = new JXButton(new BackToCalculatedDiseasesPanelAction());
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnBack, "cell 1 5,alignx left");
	}
	
	public void generateTherapiesInfo(List<String> therapies) {
		this.therapiesPanel.add(this.generateList(therapies), "alignx center");
	}
	
	public void generateAdditionalProcedureInfo(List<String> additionalProcedures) {
		this.additionalProceduresPanel.add(this.generateList(additionalProcedures), "alignx center");
	}
	
	private JXLabel generateList(List<String> infoElements) {
		JXLabel label = new JXLabel();
		StringBuilder stringBuilder = new StringBuilder();
		for (String element : infoElements) {
			stringBuilder.append("<li>");
			stringBuilder.append(AppSingleton.getInstance().getPrologConsultationApi().filterNames(element));
			stringBuilder.append("</li>\r\n");
			stringBuilder.append("<br>\r\n");
		}
		label.setText("<html>\r\n" + 
				"<body>\r\n" +  
				"\r\n" + 
				"<ul>\r\n" +
				stringBuilder.toString() +
				"</ul>  \r\n" + 
				"</body>\r\n" + 
				"</html>");
		label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		return label;
	}
}