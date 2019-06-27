package org.eok.medicalsupportsystem.view;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.event.PatientEvent;
import org.eok.medicalsupportsystem.event.PatientEvent.PatientEnum;
import org.eok.medicalsupportsystem.model.Doctor;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.repository.DoctorRepository;
import org.jdesktop.swingx.JXList;

public class PatientList extends JXList implements org.eok.medicalsupportsystem.view.Observer{

	private static final long serialVersionUID = 9205816465559385805L;
	private DefaultListModel<Patient> patientListModel;
	private DoctorRepository doctorRepository;

	public PatientList() {
		super(true);
		this.patientListModel = new DefaultListModel<Patient>();
		this.doctorRepository = new DoctorRepository();
		this.initModel();
		this.setModel(this.patientListModel);
		this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
	}

	public DefaultListModel<Patient> getPatientListModel() {
		return patientListModel;
	}

	private void initModel() {
		Doctor doctor = AppSingleton.getInstance().getDoctor();
		doctor.setPatients(this.doctorRepository.getPatients(doctor));

		for (Patient patient : doctor.getPatients()) {
			patient.attachObserver(this);
			this.patientListModel.addElement(patient);
		}
		
		this.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Patient patient = (Patient)PatientList.this.getSelectedValue();
				if (patient != null) {
					AppSingleton.getInstance().getAppFrame().getMainDashboardPanel().setPatientDetails(new PatientDetails(patient));					
				}
			}
		});
	}
	
	public Patient getSelectedPatient() {
		if (this.getSelectedValue() != null) {
			return (Patient) this.getSelectedValue();			
		}
		else {
			return null;
		}
	}

	@Override
	public void update(Object arg) {
		if (arg instanceof PatientEvent) {
			PatientEvent patientEvent = (PatientEvent) arg;
			if (patientEvent.getEvent() == PatientEnum.ADD_PATIENT) {
				this.patientListModel.addElement(patientEvent.getPatient());
			} else if (patientEvent.getEvent() == PatientEnum.DELETE_PATIENT) {
				for (int i = 0; i < this.patientListModel.getSize(); i++) {
					if (((Patient)this.patientListModel.get(i)).getId().equals(patientEvent.getPatient().getId())) {
						this.patientListModel.remove(i);
						break;
					}
				}
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
	}
}