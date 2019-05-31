package org.eok.medicalsupportsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Doctor;
import org.jdesktop.swingx.auth.LoginService;

public class AppLoginService extends LoginService
{
	private static final Doctor dominator = new Doctor("", "", "No", "Credentials");
	private static final Doctor doctor1 = new Doctor("doctor1", "doctor123", "Milovan", "Milovanović");
	private static final Doctor doctor2 = new Doctor("doctor2", "doctor456", "Zoran", "Zoranović");
	private static final Doctor doctor3 = new Doctor("doctor3", "doctor789", "Dragutin", "Dragutinović");
	private Map<String, Doctor> doctors;
	
	public AppLoginService() 
	{
		this.doctors = new HashMap<String, Doctor>();
		this.doctors.put(dominator.getUsername(), dominator);
		this.doctors.put(doctor1.getUsername(), doctor1);
		this.doctors.put(doctor2.getUsername(), doctor2);
		this.doctors.put(doctor3.getUsername(), doctor3);
		
		AppSingleton.getInstance().setDoctor(dominator);
	}
	
	@Override
	public boolean authenticate(String name, char[] password, String server) throws Exception 
	{
		Doctor doctor = this.doctors.get(name);
		if (doctor == null || !doctor.getPassword().equals(new String(password)))
		{
			return false;			
		}	
		AppSingleton.getInstance().setDoctor(doctor);
		return true;
	}
}