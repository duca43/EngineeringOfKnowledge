package org.eok.medicalsupportsystem.controller;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Doctor;
import org.eok.medicalsupportsystem.repository.DoctorRepository;
import org.jdesktop.swingx.auth.LoginService;

public class AppLoginService extends LoginService {
	
//	private static final Doctor dominator = new Doctor(UUID.randomUUID().toString(), "123", "123", "No", "Credentials");
//	private static final Doctor doctor1 = new Doctor("doctor1", "doctor123", "Milovan", "Milovanović");
//	private static final Doctor doctor2 = new Doctor("doctor2", "doctor456", "Zoran", "Zoranović");
//	private static final Doctor doctor3 = new Doctor("doctor3", "doctor789", "Dragutin", "Dragutinović");
	private DoctorRepository doctorRepository;
	
	public AppLoginService() {
		this.doctorRepository = new DoctorRepository();
	}
	
	@Override
	public boolean authenticate(String name, char[] password, String server) throws Exception {	
		Doctor doctor = this.doctorRepository.findOneByUsername(name);
		if (doctor == null || !doctor.getPassword().equals(new String(password)))
		{
			return false;			
		}	
		AppSingleton.getInstance().setDoctor(doctor);
		return true;
	}	
}