package org.eok.medicalsupportsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor {

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private List<Patient> patients = new ArrayList<Patient>();
	
	public Doctor(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Patient> getPatients() {
		if (patients == null) {
			patients = new ArrayList<Patient>();			
		}
		return patients;
	}
}