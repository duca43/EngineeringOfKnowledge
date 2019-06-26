package org.eok.medicalsupportsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Patient extends Observable {

	public enum GenderEnum {
		MALE, FEMALE
	};

	public enum RaceEnum {
		WHITE, BLACK, ASIAN, HISPANIC, INDIAN
	};

	private UUID id;
	private String firstName;
	private String lastName;
	private GenderEnum gender;
	private int age;
	private RaceEnum race;
	private List<Examination> examinations;

//	for reading from DB
	public Patient(String id, String firstName, String lastName, String gender, int years, String race, List<Examination> examinations) {
		this.id = UUID.fromString(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = GenderEnum.valueOf(gender);
		this.age = years;
		this.race = RaceEnum.valueOf(race);
		this.examinations = examinations;
	}
	
//	for creating new object
	public Patient(String firstName, String lastName, GenderEnum gender, int years, RaceEnum race) {
		this.id = UUID.randomUUID();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.age = years;
		this.race = race;
		this.examinations = new ArrayList<>();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public RaceEnum getRace() {
		return race;
	}

	public void setRace(RaceEnum race) {
		this.race = race;
	}

	public List<Examination> getExaminations() {
		return examinations;
	}

	@Override
	public String toString() {
		return this.lastName + " " + this.firstName;
	}
}