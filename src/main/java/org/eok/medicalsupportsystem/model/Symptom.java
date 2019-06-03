package org.eok.medicalsupportsystem.model;

public class Symptom {

	private String name;
	private float prob;

	public Symptom(String name, float prob) {
		super();
		this.name = name;
		this.prob = prob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getProb() {
		return prob;
	}

	public void setProb(float prob) {
		this.prob = prob;
	}

	@Override
	public String toString() {
		return this.name;
	}
}