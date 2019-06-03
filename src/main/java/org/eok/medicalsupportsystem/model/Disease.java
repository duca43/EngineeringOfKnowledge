package org.eok.medicalsupportsystem.model;

public class Disease implements Comparable<Disease> {

	private String name;
	private float prob;
	private String label;

	public Disease(String name, float prob, String label) {
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

	@Override
	public String toString() {
		return "Disease [name=" + name + ", prob=" + prob + "]";
	}

	@Override
	public int compareTo(Disease o) {
		return (this.getProb() - o.getProb()) < 0 ? -1 : (this.getProb() - o.getProb()) > 0 ? 1 : 0;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}