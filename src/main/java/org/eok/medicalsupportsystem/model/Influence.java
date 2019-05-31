package org.eok.medicalsupportsystem.model;

public class Influence 
{
	private String symptomName;
	private String diseaseName;
	private float prob;
	
	public Influence(String symptomName, String diseaseName, float prob) {
		super();
		this.symptomName = symptomName;
		this.diseaseName = diseaseName;
		this.prob = prob;
	}
	
	public String getSymptomName() {
		return symptomName;
	}
	
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	
	public String getDiseaseName() {
		return diseaseName;
	}
	
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	
	public float getProb() {
		return prob;
	}
	
	public void setProb(float prob) {
		this.prob = prob;
	}
	
	@Override
	public String toString() {
		return "Infulence [symptomName=" + symptomName + ", diseaseName=" + diseaseName + ", prob=" + prob + "]";
	}
}