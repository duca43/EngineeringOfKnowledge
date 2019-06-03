package org.eok.medicalsupportsystem.view;

import java.awt.Font;

import javax.swing.JPanel;

import org.eok.medicalsupportsystem.model.Patient;
import org.jdesktop.swingx.JXLabel;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

public class PatientDetailsFields extends JPanel {
	
	private static final long serialVersionUID = 8855506140317410443L;
	private JXLabel race;
	private JXLabel age;
	private JXLabel gender;
	private JXLabel lastname;
	private JXLabel firstname;

	/**
	 * Create the panel.
	 */
	public PatientDetailsFields(Patient patient) {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[grow,center][grow,center]", "[grow][grow][grow][grow][grow]"));
		
		JXLabel lblFirstName = new JXLabel();
		lblFirstName.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblFirstName.setText("First name:");
		add(lblFirstName, "cell 0 0");
		
		firstname = new JXLabel(patient.getFirstName());
		firstname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		add(firstname, "cell 1 0");
		
		JXLabel lblLastName = new JXLabel();
		lblLastName.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblLastName.setText("Last name:");
		add(lblLastName, "cell 0 1");
		
		lastname = new JXLabel(patient.getLastName());
		lastname.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		add(lastname, "cell 1 1");
		
		JXLabel lblGender = new JXLabel();
		lblGender.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblGender.setText("Gender:");
		add(lblGender, "cell 0 2");
		
		gender = new JXLabel(patient.getGender().toString());
		gender.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		add(gender, "cell 1 2");
		
		JXLabel lblAge = new JXLabel();
		lblAge.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblAge.setText("Age:");
		add(lblAge, "cell 0 3");
		
		age = new JXLabel(String.valueOf(patient.getAge()));
		age.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		add(age, "cell 1 3");
		
		JXLabel lblRace = new JXLabel();
		lblRace.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblRace.setText("Race:");
		add(lblRace, "cell 0 4");
		
		race = new JXLabel(patient.getRace().toString());
		race.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		add(race, "cell 1 4");
	}
}