package org.eok.medicalsupportsystem.view;

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
import org.eok.medicalsupportsystem.repository.PatientRepository;
import org.jdesktop.swingx.JXList;

public class PatientList extends JXList implements org.eok.medicalsupportsystem.view.Observer{

	private static final long serialVersionUID = 9205816465559385805L;
	private DefaultListModel<Patient> patientListModel;
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;

	public PatientList() {
		super(true);
		this.patientListModel = new DefaultListModel<Patient>();
		this.patientRepository = new PatientRepository();
		this.doctorRepository = new DoctorRepository();
		this.initModel();
		this.setModel(this.patientListModel);
		this.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
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

		// SAVE TEST
//		Patient patient1 = new Patient("Zlatan", "Zlatanović", GenderEnum.MALE, 66, RaceEnum.BLACK);
//		Patient patient2 = new Patient("Miodrag", "Miodragović", GenderEnum.MALE, 33, RaceEnum.ASIAN);
//		this.patientListModel.addElement(patientRepository.save(patient1));
//		this.patientListModel.addElement(patientRepository.save(patient2));
//		
		// FIND ALL TEST
//		List<Patient> patients = this.patientRepository.findAll();
//		for(Patient patient : patients) {
//			this.patientListModel.addElement(patient);
//		}

		// FIND ONE TEST
//		this.patientListModel.addElement(patientRepository.findOne("d957bf6f-c181-4fa4-85d3-294403bd30b7"));

		// DELETE TEST
//		this.patientRepository.delete("d957bf6f-c181-4fa4-85d3-294403bd30b7");

		// UPDATE TEST
//		Patient patient = patients.get(0);
//		patient.setFirstName("Zoran");
//		this.patientRepository.update(patient);	
		
		
		this.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Patient patient = (Patient)PatientList.this.getSelectedValue();
				if (patient != null) {
					((MainDashboardPanel)AppSingleton.getInstance().getAppFrame().getDashboardPanel()).setPatientDetails(new PatientDetails(patient));					
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