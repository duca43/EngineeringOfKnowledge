package org.eok.medicalsupportsystem.model;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class CbReasonerData implements CaseComponent {

	public enum GenderEnum {
		MALE, FEMALE
	};

	public enum RaceEnum {
		WHITE, BLACK, ASIAN, HISPANIC, INDIAN
	};

	private String disease;
	private String result;
	private GenderEnum gender;
	private int age;
	private RaceEnum race;

	public CbReasonerData() {
	}

	public CbReasonerData(String disease, String result, String gender, int age, String race) {
		super();
		this.disease = disease;
		this.result = result;
		this.gender = GenderEnum.valueOf(gender);
		this.age = age;
		this.race = RaceEnum.valueOf(race);
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getGender() {
		return gender.toString();
	}

	public void setGender(String gender) {
		this.gender = GenderEnum.valueOf(gender);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRace() {
		return race.toString();
	}

	public void setRace(String race) {
		this.race = RaceEnum.valueOf(race);
	}

	@Override
	public String toString() {
		return "CbReasonerResult [disease=" + disease + ", result=" + result + ", gender=" + gender + ", age=" + age
				+ ", race=" + race + "]";
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
