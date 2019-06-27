package org.eok.medicalsupportsystem.prolog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.jena.shared.NotFoundException;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Influence;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.model.Patient.GenderEnum;
import org.eok.medicalsupportsystem.model.Patient.RaceEnum;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.util.Util;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;

public class PrologConsultationApi {

	private JIPEngine engine;
	private static final String FILE_NAME = "resources/data/baza_bolesti.pl";
	private Map<String, Symptom> symptomsMap;
	
	public PrologConsultationApi() {
		this.engine = new JIPEngine();
		engine.consultFile(FILE_NAME);
		this.symptomsMap = this.getSymptomsFromPrologBase();
	}
	
	public Map<String, Symptom> getSymptomsMap() {
		return symptomsMap;
	}
	
	public Map<String, Symptom> getSymptomsFromPrologBase() {
		JIPQuery symptomQuery = engine.openSynchronousQuery("simptom(Naziv, Verovatnoca)");
		Map<String, Symptom> symptoms = new HashMap<>();
		JIPTerm solution;
		while ((solution = symptomQuery.nextSolution()) != null)
		{
			String name = solution.getVariables()[0].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[1].getValue().toString());
			symptoms.put(name, new Symptom(name, prob, Util.filterNames(name)));
		}
		return symptoms;
	}
	
	public List<Disease> getDiseasesFromPrologBase(Patient patient) {
		JIPQuery diseaseQuery = engine.openSynchronousQuery("bolest(Naziv, Verovatnoca)");
		JIPTerm solution;
		List<Disease> diseases = new ArrayList<Disease>();
		while ((solution = diseaseQuery.nextSolution()) != null)
		{
			String name = solution.getVariables()[0].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[1].getValue().toString());
			prob = evaluateDiseaseProb(name, prob, patient.getAge(), patient.getGender(), patient.getRace());
			diseases.add(new Disease(name, prob, Util.filterNames(name)));
		}
		return diseases;
	}
	
	public List<Influence> getInfluencesFromPrologBase() {
		JIPQuery symptomDiseaseQuery = engine.openSynchronousQuery("pojava_simptoma_za_bolest(NazivBolesti, NazivSimptoma, Verovatnoca)");
		List<Influence> influences = new ArrayList<Influence>();
		JIPTerm solution;
		while ((solution = symptomDiseaseQuery.nextSolution()) != null)
		{
			String diseaseName = solution.getVariables()[0].getValue().toString();
			String symptomName = solution.getVariables()[1].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[2].getValue().toString());
			
			influences.add(new Influence(symptomName, diseaseName, prob));
		} 
		
		return influences;
	}
	
	public List<Symptom> getSimilarSymptomsFromPrologBase(Symptom symptom) {
		List<Symptom> symptoms = new ArrayList<Symptom>();
		JIPQuery symptomQuery = engine.openSynchronousQuery("slican_simptom(" + symptom.getName() + ", Povezani_simptom)");
		symptoms.addAll(this.extractSimilarSymptomsFromPrologBase(symptomQuery));
		symptomQuery = engine.openSynchronousQuery("slican_simptom(Povezani_simptom, " + symptom.getName() +")");
		symptoms.addAll(this.extractSimilarSymptomsFromPrologBase(symptomQuery));
		return symptoms;
	}
	
	private List<Symptom> extractSimilarSymptomsFromPrologBase(JIPQuery symptomQuery) {
		List<Symptom> symptoms = new ArrayList<>();
		JIPTerm solution;
		while ((solution = symptomQuery.nextSolution()) != null)
		{
			String symptomName = solution.getVariables()[0].getValue().toString();
			symptoms.add(this.symptomsMap.get(symptomName));
		}
		return symptoms;
	}
	
	private float getAgeMultiplicator(String name, int age){
		JIPQuery ageMultiplicatorQuery = engine.openSynchronousQuery("pronadji_multiplikator_godine(" + name + "," + age + ", Multiplicator)");
		JIPTerm solution = ageMultiplicatorQuery.nextSolution();
		if (solution != null){
			float multiplicator = Float.parseFloat(solution.getVariables()[0].getValue().toString());
			return multiplicator;
		}else{
			throw new NotFoundException("Age multiplicator doesn't exist!");
		}
	}
	
	private float getGenderMultiplicator(String name, GenderEnum gender){
		JIPQuery genderMultiplicatorQuery = engine.openSynchronousQuery("pronadji_multiplikator_pol(" + name + "," + gender.toString().toLowerCase() + ", Multiplicator)");
		JIPTerm solution = genderMultiplicatorQuery.nextSolution();
		if (solution != null){
			float multiplicator = Float.parseFloat(solution.getVariables()[0].getValue().toString());
			return multiplicator;
		}else{
			throw new NotFoundException("Gender multiplicator doesn't exist!");
		}
	}
	
	private float getRaceMultiplicator(String name, RaceEnum race){
		JIPQuery raceMultiplicatorQuery = engine.openSynchronousQuery("pronadji_multiplikator_rasa(" + name + "," + race.toString().toLowerCase() + ", Multiplicator)");
		JIPTerm solution = raceMultiplicatorQuery.nextSolution();
		if (solution != null){
			float multiplicator = Float.parseFloat(solution.getVariables()[0].getValue().toString());
			return multiplicator;
		}else{
			throw new NotFoundException("Race multiplicator doesn't exist!");
		}
	}
		
	public float evaluateDiseaseProb(String name, float diseaseProb, int age, GenderEnum gender, RaceEnum race){
		List<Float> multiplicators = new ArrayList<>();
		multiplicators.add(diseaseProb * getAgeMultiplicator(name, age));
		multiplicators.add(diseaseProb * getGenderMultiplicator(name, gender));
		multiplicators.add(diseaseProb * getRaceMultiplicator(name, race));
		return new Float(multiplicators.stream().mapToDouble(Float::doubleValue).average().getAsDouble());
	}
}