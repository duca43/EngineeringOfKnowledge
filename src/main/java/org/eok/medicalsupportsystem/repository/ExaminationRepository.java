package org.eok.medicalsupportsystem.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Examination;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.util.Util;

public class ExaminationRepository extends AbstractRepository<Examination> {

	@Override
	public Examination findOne(String id) {
		String queryString =
				"SELECT ?diagnose ?note ?date " +
				"WHERE {" +
				"  eok:" + id + " a eok:Examination;" + 
				"	 eok:disease ?diagnose;" +
				"    eok:note ?note;" +
				"    eok:date ?date." +
				"}";
		ResultSet results = executeQuery(queryString);
		QuerySolution solution = results.nextSolution();
		
		String diseaseName = solution.getLiteral("diagnose").getString();
		Disease disease = new Disease(diseaseName, 0, Util.filterNames(diseaseName));
		
		String note = solution.getLiteral("note").getString();
		LocalDate date = LocalDate.parse(solution.getLiteral("date").getString());
		
		//THERAPIES
		List<String> therapies = new ArrayList<>();
		String queryTherapyString =
				"SELECT ?therapy " +
				"WHERE {" +
				"	eok:" + id + " a eok:Examination;" + 
				"		eok:therapy ?therapy." +                           
				"}";
		
		ResultSet therapyResults = executeQuery(queryTherapyString);
		while (therapyResults.hasNext()) {
			QuerySolution therapySolution = therapyResults.nextSolution();
			
			String therapyName = therapySolution.getLiteral("therapy").getString();
			therapies.add(therapyName);
		}
		
		//SYMPTOMS
		List<Symptom> symptoms = new ArrayList<>();
		String querySymptomString =
				"SELECT ?symptom " +
				"WHERE {" +
				"	eok:" + id + " a eok:Examination;" + 
				"		eok:symptom ?symptom." +                           
				"}";
		
		ResultSet symptomResults = executeQuery(querySymptomString);
		while (symptomResults.hasNext()) {
			QuerySolution symptomSolution = symptomResults.nextSolution();
			
			String sympthomName = symptomSolution.getLiteral("symptom").getString();
			symptoms.add(new Symptom(sympthomName, 0 , Util.filterNames(sympthomName)));
		}
		
		//ADDITIONAL PROCEDURES
		List<String> additionalProcedures = new ArrayList<>();
		String queryAdditionalProceduresString =
				"SELECT ?additionalProcedure " +
				"WHERE {" +
				"	eok:" + id + " a eok:Examination;" + 
				"		eok:additionalProcedure ?additionalProcedure." +                           
				"}";
		
		ResultSet additionalProceduresResults = executeQuery(queryAdditionalProceduresString);
		while (additionalProceduresResults.hasNext()) {
			QuerySolution additionalProcedureSolution = additionalProceduresResults.nextSolution();
			
			String additionalProcedureName = additionalProcedureSolution.getLiteral("additionalProcedure").getString();
			additionalProcedures.add(additionalProcedureName);
		}
		return new Examination(id, null, disease, symptoms, therapies, additionalProcedures , note , date); 
	}

	@Override
	public Examination save(Examination entity) {
		entity.setId(UUID.randomUUID());
		String insertString = 
				"INSERT DATA {" +
			    "  eok:" + entity.getId() + " a eok:Examination;" +
				"    eok:patient eok:" + entity.getPatient().getId() + ";" +	
				"    eok:disease \"" + entity.getDiagnose().getName() + "\"^^xsd:string;";
		for (Symptom symptom : entity.getSymptomList()){
			insertString += "eok:symptom \"" + symptom.getName() + "\"^^xsd:string;";
		}
		for (String therapy: entity.getTherapies()){
			insertString += "eok:therapy \"" + therapy + "\"^^xsd:string;";
		}
		for (String additionalProcedure: entity.getAdditionalProcedures()){
			insertString += "eok:additionalProcedure \"" + additionalProcedure + "\"^^xsd:string;";
		}
		insertString += "eok:note \"" + entity.getNote() + "\"^^xsd:string;" +
						"eok:date \"" + entity.getDate() + "\"^^xsd:string;" +
						"}";
		
		executeUpdate(insertString);
		return entity;
	}			

	@Override
	public Examination update(Examination entity) {
		delete(entity.getId().toString());
		return save(entity);
	}

	@Override
	public List<Examination> findAll() {
		return null;
	}
}
