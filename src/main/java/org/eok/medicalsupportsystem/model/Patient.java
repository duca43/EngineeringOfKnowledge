package org.eok.medicalsupportsystem.model;

public class Patient {

	public enum GenderEnum {
		MALE, FEMALE
	};

	public enum RaceEnum {
		WHITE, BLACK, ASIAN, HISPANIC, INDIAN
	};

	private String firstName;
	private String lastName;
	private GenderEnum gender;
	private int age;
	private RaceEnum race;

	public Patient(String firstName, String lastName, GenderEnum gender, int years, RaceEnum race) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = years;
		this.race = race;
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

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public int getYears() {
		return age;
	}

	public void setYears(int years) {
		this.age = years;
	}

	public RaceEnum getRace() {
		return race;
	}

	public void setRace(RaceEnum race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return this.lastName + " " + this.firstName;
	}
}