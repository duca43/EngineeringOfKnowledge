package org.eok.medicalsupportsystem.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.JPanel;

import org.eok.medicalsupportsystem.controller.OpenMainDashboardAction;
import org.eok.medicalsupportsystem.model.Patient;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;

public class PatientDashboardPanel extends JPanel {

	private static final long serialVersionUID = 1117327672646374420L;
	public static final String SYMPTOM_CHECKER_PANEL = "symptom-checker-panel";
	public static final String ADDITIONAL_SYMPTOM_CHECKER_PANEL = "additional-symptom-checker-panel";
	public static final String CALCULATED_DISEASES_PANEL = "calculated-diseases-panel";
	public static final String THERAPIES_AND_ADDITIONAL_PROCEDURES_PANEL = "therapies-and-additional-procedures-panel";
	public static final String ARGUMENTATION_GRAPH_PANEL = "argumentation-graph-panel";
	private JPanel cards;
	private CardLayout cardLayout;
	private SymptomCheckerPanel symptomCheckerPanel;
	private AdditionalSymptomCheckerPanel additionalSymptomCheckerPanel;
	private CalculatedDiseasesPanel calculatedDiseasesPanel;
	private TherapiesAndAdditionalProceduresPanel therapiesAndAdditionalProceduresPanel;
	private ArgumentationGraphPanel argumentationGraphPanel;
	
	public PatientDashboardPanel(Patient patient) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel detailsPanel = new JPanel();
		this.add(detailsPanel, BorderLayout.WEST);
		detailsPanel.setLayout(new MigLayout("", "[grow]", "[][grow][][grow][grow][grow][grow]"));
		
		JXButton btnOpenMainDashboard = new JXButton(new OpenMainDashboardAction());
		btnOpenMainDashboard.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		detailsPanel.add(btnOpenMainDashboard, "cell 0 0,alignx center");
		
		JXLabel lblPatientInfo = new JXLabel();
		detailsPanel.add(lblPatientInfo, "cell 0 2,alignx center");
		lblPatientInfo.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		lblPatientInfo.setText("Patient info:");
		
		PatientDetailsFields patientDetailsFields = new PatientDetailsFields(patient);
		detailsPanel.add(patientDetailsFields, "cell 0 3 1 2,grow");
		
		this.cardLayout = new CardLayout();
		this.cards = new JPanel(cardLayout);
		
		symptomCheckerPanel = new SymptomCheckerPanel();
		additionalSymptomCheckerPanel = new AdditionalSymptomCheckerPanel();
		calculatedDiseasesPanel = new CalculatedDiseasesPanel();
		therapiesAndAdditionalProceduresPanel = new TherapiesAndAdditionalProceduresPanel();
		argumentationGraphPanel = new ArgumentationGraphPanel();
		this.cards.add(symptomCheckerPanel, SYMPTOM_CHECKER_PANEL);
		this.cards.add(additionalSymptomCheckerPanel, ADDITIONAL_SYMPTOM_CHECKER_PANEL);
		this.cards.add(calculatedDiseasesPanel, CALCULATED_DISEASES_PANEL);
		this.cards.add(therapiesAndAdditionalProceduresPanel, THERAPIES_AND_ADDITIONAL_PROCEDURES_PANEL);
		this.cards.add(argumentationGraphPanel, ARGUMENTATION_GRAPH_PANEL);
		this.add(cards, BorderLayout.CENTER);
	}
	
	public void switchToPreviousPanel() {
		this.cardLayout.previous(this.cards);
	}
	
	public void switchToNextPanel() {
		this.cardLayout.next(this.cards);
	}

	public SymptomCheckerPanel getSymptomCheckerPanel() {
		for (int i = 0; i < this.cards.getComponentCount(); i++) {
			if (this.cards.getComponent(i) instanceof SymptomCheckerPanel) {
				return (SymptomCheckerPanel) this.cards.getComponent(i);  
			}
		}
		return null;
	}

	public AdditionalSymptomCheckerPanel getAdditionalSymptomCheckerPanel() {
		for (int i = 0; i < this.cards.getComponentCount(); i++) {
			if (this.cards.getComponent(i) instanceof AdditionalSymptomCheckerPanel) {
				return (AdditionalSymptomCheckerPanel) this.cards.getComponent(i);  
			}
		}
		return null;
	}
	
	public CalculatedDiseasesPanel getCalculatedDiseasesPanel() {
		for (int i = 0; i < this.cards.getComponentCount(); i++) {
			if (this.cards.getComponent(i) instanceof CalculatedDiseasesPanel) {
				return (CalculatedDiseasesPanel) this.cards.getComponent(i);  
			}
		}
		return null;
	}
	
	public TherapiesAndAdditionalProceduresPanel getTherapiesAndAdditionalProceduresPanel() {
		for (int i = 0; i < this.cards.getComponentCount(); i++) {
			if (this.cards.getComponent(i) instanceof TherapiesAndAdditionalProceduresPanel) {
				return (TherapiesAndAdditionalProceduresPanel) this.cards.getComponent(i);  
			}
		}
		return null;
	}

	public ArgumentationGraphPanel getArgumentationGraphPanel() {
		for (int i = 0; i < this.cards.getComponentCount(); i++) {
			if (this.cards.getComponent(i) instanceof ArgumentationGraphPanel) {
				return (ArgumentationGraphPanel) this.cards.getComponent(i);  
			}
		}
		return null;
	}

	public JPanel getCards() {
		return this.cards;
	}
}