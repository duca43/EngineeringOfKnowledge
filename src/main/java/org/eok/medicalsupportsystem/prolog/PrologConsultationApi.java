package org.eok.medicalsupportsystem.prolog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Influence;
import org.eok.medicalsupportsystem.model.Symptom;

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
			symptoms.put(name, new Symptom(name, prob, this.filterNames(name)));
		}
		return symptoms;
	}
	
	public List<Disease> getDiseasesFromPrologBase() {
		JIPQuery diseaseQuery = engine.openSynchronousQuery("bolest(Naziv, Verovatnoca)");
		JIPTerm solution;
		List<Disease> diseases = new ArrayList<Disease>();
		while ((solution = diseaseQuery.nextSolution()) != null)
		{
			String name = solution.getVariables()[0].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[1].getValue().toString());
			diseases.add(new Disease(name, prob, this.filterNames(name)));
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
	
	public String filterNames(String nameToFilter) {
		return nameToFilter.substring(0, 1).toUpperCase() + nameToFilter.substring(1).replace('_', ' ');
	}
}