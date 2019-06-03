package org.eok.medicalsupportsystem.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;

import org.eok.medicalsupportsystem.controller.BackToSymptomCheckerPanelAction;
import org.eok.medicalsupportsystem.controller.ProceedToTherapyRecommendationAction;
import org.eok.medicalsupportsystem.model.Disease;
import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXButton;

public class CalculatedDiseasesPanel extends JPanel {

	private static final long serialVersionUID = -2456120838136377788L;
	private JPanel diseasesCard;

	/**
	 * Create the panel.
	 */
	public CalculatedDiseasesPanel() {
		setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][][grow][]"));
		
		JXLabel calculatedDiseasesMsg = new JXLabel();
		calculatedDiseasesMsg.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		calculatedDiseasesMsg.setText("Diagnosis of the most probable diseases");
		add(calculatedDiseasesMsg, "cell 0 0 4 1,alignx center");
		
		JScrollPane scrollPane = new JScrollPane();
		
		diseasesCard = new JPanel();
		diseasesCard.setBackground(new Color(255, 228, 196));
		diseasesCard.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		diseasesCard.setLayout(new MigLayout("wrap 4", "[grow, center]", "[grow]"));
		
		scrollPane.setViewportView(diseasesCard);
		add(scrollPane, "cell 1 2 2 1,grow");
		
		JXButton btnBack = new JXButton(new BackToSymptomCheckerPanelAction());
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnBack, "cell 1 3,alignx left");
	}
	
	public void generateDiseasesLabels(List<Disease> diseases) {
		for (Disease disease : diseases) {
			this.diseasesCard.add(this.generateDiseaseNameLabel(disease));
			this.diseasesCard.add(this.generateArrowLabel());
			this.diseasesCard.add(this.generateDiseaseProbLabel(disease));
			this.diseasesCard.add(this.generateProceedToTherapyAndAdditionalProcedurePanel(disease));
		}
	}
	
	private JXLabel generateDiseaseNameLabel(Disease disease) {
		JXLabel diseaseNameLabel = new JXLabel(disease.getLabel() + "  ");
		diseaseNameLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		return diseaseNameLabel;
	}
	
	private JXLabel generateArrowLabel() {
		JXLabel arrowLabel = new JXLabel(new ImageIcon("resources/images/arrow-icon.png"));
		return arrowLabel;
	}
	
	private JXLabel generateDiseaseProbLabel(Disease disease) {
		JXLabel diseaseProbLabel = new JXLabel("  " + ((int)(disease.getProb() * 100)) + "%");
		diseaseProbLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		return diseaseProbLabel;
	}
	
	private JXButton generateProceedToTherapyAndAdditionalProcedurePanel(Disease disease) {
		JXButton button = new JXButton(new ProceedToTherapyRecommendationAction(disease));
		button.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		return button;
	}
}