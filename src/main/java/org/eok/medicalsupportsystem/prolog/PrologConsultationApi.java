package org.eok.medicalsupportsystem.prolog;

import java.util.ArrayList;
import java.util.List;

import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Influence;
import org.eok.medicalsupportsystem.model.Symptom;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;

public class PrologConsultationApi {

	private JIPEngine engine;
	private static final String FILE_NAME = "resources/data/baza_bolesti_novija.pl";
	
	public PrologConsultationApi() {
		this.engine = new JIPEngine();
		engine.consultFile(FILE_NAME);
	}
	
	public List<Symptom> getSymptoms() {
		JIPQuery symptomQuery = engine.openSynchronousQuery("simptom(Naziv, Verovatnoca)");
		List<Symptom> symptoms = new ArrayList<Symptom>();
		JIPTerm solution;
		while ((solution = symptomQuery.nextSolution()) != null)
		{
			String name = solution.getVariables()[0].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[1].getValue().toString());
			symptoms.add(new Symptom(name, prob));
		}
		return symptoms;
	}
	
	public List<Disease> getDiseases() {
		JIPQuery diseaseQuery = engine.openSynchronousQuery("bolest(Naziv, Verovatnoca)");
		JIPTerm solution;
		List<Disease> diseases = new ArrayList<Disease>();
		while ((solution = diseaseQuery.nextSolution()) != null)
		{
			String name = solution.getVariables()[0].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[1].getValue().toString());
			diseases.add(new Disease(name, prob));
		}
		return diseases;
	}
	
	public List<Influence> getInfluences() {
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
}