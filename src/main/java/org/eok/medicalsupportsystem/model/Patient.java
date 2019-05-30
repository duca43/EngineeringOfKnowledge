package org.eok.medicalsupportsystem.model;

public class Patient {

	enum GenderEnum {
		MALE, FEMALE
	};

	enum RaceEnum {
		WHITE, BLACK, YELLOW
	};

	private String firstName;
	private String lastName;
	private GenderEnum gender;
	private int years;
	private RaceEnum race;

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
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public RaceEnum getRace() {
		return race;
	}

	public void setRace(RaceEnum race) {
		this.race = race;
	}

}
