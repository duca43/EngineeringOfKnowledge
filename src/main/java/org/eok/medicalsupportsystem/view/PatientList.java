package org.eok.medicalsupportsystem.view;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Doctor;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.model.Patient.GenderEnum;
import org.eok.medicalsupportsystem.model.Patient.RaceEnum;
import org.jdesktop.swingx.JXList;

public class PatientList extends JXList {

	private static final long serialVersionUID = 9205816465559385805L;
	private DefaultListModel<Patient> patientListModel;
	
	public PatientList() {
		super(true);
		this.patientListModel = new DefaultListModel<Patient>();
		this.initModel();
		this.setModel(this.patientListModel);
		this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
	}

	public DefaultListModel<Patient> getPatientListModel() {
		return patientListModel;
	}
	
	private void initModel() {
		Doctor doctor = AppSingleton.getInstance().getDoctor();
		for (Patient patient : doctor.getPatients()) {
			this.patientListModel.addElement(patient);
		}
		
		this.patientListModel.addElement(new Patient("Zlatan", "Zlatanović", GenderEnum.MALE, 66, RaceEnum.BLACK));
		this.patientListModel.addElement(new Patient("Miodrag", "Miodragović", GenderEnum.MALE, 33, RaceEnum.ASIAN));
		
		this.addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(((Patient)PatientList.this.getSelectedValue()));
			}
		});
	}
}