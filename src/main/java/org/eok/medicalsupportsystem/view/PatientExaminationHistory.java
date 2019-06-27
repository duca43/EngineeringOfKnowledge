package org.eok.medicalsupportsystem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.eok.medicalsupportsystem.model.Examination;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.util.ExaminationComparator;
import org.eok.medicalsupportsystem.util.Util;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTextArea;

import net.miginfocom.swing.MigLayout;

public class PatientExaminationHistory extends JPanel {
	
	private static final long serialVersionUID = -997937516109947966L;
	private JPanel diseasesHistoryPanel;
	private JXCollapsiblePane collapsiblePane;
	private JXButton btnShowMoreDetails;
	private JScrollPane tableScrollPane;
	private List<Examination> examinations;
	private JXTable table;
	private JPanel collapsiblePaneContent;
	private JXLabel lblTherapies;
	private JXLabel lblAdditionalProcedures;
	private JXLabel therapies;
	private JXLabel additionalProcedures;
	private JXLabel lblNote;
	private JXTextArea note;
	private JXLabel lblSymptoms;
	private JXLabel symptoms;
	private PatientDetailsFields patientDetailsFields;
	
	public PatientExaminationHistory(Patient patient) {
		setBackground(new Color(255, 255, 240));
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		collapsiblePane = new JXCollapsiblePane();
		collapsiblePane.setCollapsed(true);
		
		collapsiblePaneContent = new JPanel();
		collapsiblePaneContent.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Details", TitledBorder.CENTER, TitledBorder.TOP, new Font("Comic Sans MS", Font.BOLD, 20), new Color(0, 0, 0)));
		collapsiblePane.add(collapsiblePaneContent);
		collapsiblePaneContent.setLayout(new MigLayout("", "[grow][grow][grow][grow]", "[][grow][][grow][grow]"));
		
		lblTherapies = new JXLabel();
		lblTherapies.setText("Therapies");
		lblTherapies.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(lblTherapies, "cell 1 0,alignx center");
		
		lblAdditionalProcedures = new JXLabel();
		lblAdditionalProcedures.setText("Additional procedures");
		lblAdditionalProcedures.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(lblAdditionalProcedures, "cell 2 0,alignx center");
		
		therapies = new JXLabel();
		therapies.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(therapies, "cell 1 1,alignx center,aligny top");
		
		additionalProcedures = new JXLabel();
		additionalProcedures.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(additionalProcedures, "cell 2 1,alignx center,aligny top");
		
		lblSymptoms = new JXLabel();
		lblSymptoms.setText("Symptoms");
		lblSymptoms.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(lblSymptoms, "cell 0 0,alignx center");
		
		lblNote = new JXLabel();
		lblNote.setText("Note");
		lblNote.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(lblNote, "cell 3 0,alignx center");
		
		symptoms = new JXLabel();
		symptoms.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(symptoms, "cell 0 1,alignx center,aligny top");
		
		note = new JXTextArea();
		note.setEditable(false);
		note.setWrapStyleWord(true);
		note.setLineWrap(true);
		note.setColumns(30);
		note.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		note.setFont(new Font("Comic Sans MS", Font.BOLD, 17));
		collapsiblePaneContent.add(note, "cell 3 1,alignx center,aligny top");
		
		add(collapsiblePane, BorderLayout.SOUTH);
		
		diseasesHistoryPanel = new JPanel();
		diseasesHistoryPanel.setLayout(new MigLayout("wrap 3", "[grow,fill][grow,fill][grow,fill][grow,right]", "[][][grow][]"));
		add(diseasesHistoryPanel, BorderLayout.CENTER);
		
		btnShowMoreDetails = new JXButton();
		btnShowMoreDetails.setText("Show more details");
		btnShowMoreDetails.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		btnShowMoreDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnShowMoreDetails.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					return;
				}
				collapsiblePane.setCollapsed(!collapsiblePane.isCollapsed());
				updateExaminationDetails();
			}
		});
		diseasesHistoryPanel.add(btnShowMoreDetails, "cell 0 0 4 1,alignx center");
		
		tableScrollPane = new JScrollPane();
		diseasesHistoryPanel.add(tableScrollPane, "cell 0 1 3 2,grow");
		
		this.examinations = patient.getExaminations();
		this.examinations.sort(new ExaminationComparator());
		Object[][] tableData = new Object[examinations.size()][2];
		
		for(int i = 0; i < this.examinations.size(); i++) {
			tableData[i][0] = this.examinations.get(i).getDate().toString();
			tableData[i][1] = this.examinations.get(i).getDiagnose().getLabel();
		}
		
		table = new JXTable();
		table.setEditable(false);
		table.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		table.setModel(new DefaultTableModel(tableData, new String[] { "Date", "Diagnose" }));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				updateExaminationDetails();
			}
		});
		tableScrollPane.setViewportView(table);
		
		patientDetailsFields = new PatientDetailsFields(patient);
		diseasesHistoryPanel.add(patientDetailsFields, "cell 3 2,grow");
	}
	
	private String generateElementsList(List<String> infoElements) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String element : infoElements) {
			stringBuilder.append("<li>");
			stringBuilder.append(Util.filterNames(element));
			stringBuilder.append("</li>\r\n");
			stringBuilder.append("<br>\r\n");
		}
		return  "<html>\r\n" + 
				"<body>\r\n" +  
				"\r\n" + 
				"<ul>\r\n" +
				stringBuilder.toString() +
				"</ul>  \r\n" + 
				"</body>\r\n" + 
				"</html>";
	}

	private void updateExaminationDetails() {
		if (!collapsiblePane.isCollapsed()) {
			therapies.setText(generateElementsList(examinations.get(table.getSelectedRow()).getTherapies()));
			additionalProcedures.setText(generateElementsList(examinations.get(table.getSelectedRow()).getAdditionalProcedures()));
			note.setText(examinations.get(table.getSelectedRow()).getNote());
			symptoms.setText(generateElementsList(examinations.get(table.getSelectedRow()).getSymptomList().stream().map(Symptom::getLabel).collect(Collectors.toList())));
			SwingUtilities.updateComponentTreeUI(collapsiblePane);
		}
	}
}