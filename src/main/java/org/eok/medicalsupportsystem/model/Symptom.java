package org.eok.medicalsupportsystem.model;

public class Symptom {

	private String name;
	private float prob;
	private String label;

	public Symptom(String name, float prob, String label) {
		super();
		this.name = name;
		this.prob = prob;
		this.label = label;
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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
}