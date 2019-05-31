package org.eok.medicalsupportsystem.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.eok.medicalsupportsystem.model.Patient;

public class PatientRepository extends AbstractRepository<Patient> {

	private String queryString = 
			"BASE <http://www.medical-support-system.eok.org>" + 
			"PREFIX eok: <http://www.medical-support-system.eok.org#>" + 
			"PREFIX xsd: <http://w3.org/2001/XMLSchema#>" + 
			"SELECT ?ID ?Ime ?Prezime ?Pol ?Godine ?Rasa " + 
			"WHERE {" + 
			"	?a a eok:Patient." + 
			"  	?a eok:id ?ID." + 
			"  	?a eok:firstName ?Ime." + 
			"  	?a eok:lastName ?Prezime." + 
			"  	?a eok:gender ?Pol." + 
			"  	?a eok:age ?Godine." + 
			"  	?a eok:race ?Rasa.";
	
	public Patient findOne(String id) {
		String idString = "?a eok:id \"" + id + "\"^^xsd:string. }";
		
		ResultSet results = executeQuery(queryString + idString);
		QuerySolution solution = results.nextSolution() ;
			
		String firstName = solution.getLiteral("Ime").getString();
		String lastName = solution.getLiteral("Prezime").getString();
		String gender = solution.getLiteral("Pol").getString();
		int age = Integer.parseInt(solution.getLiteral("Godine").getString());
		String race = solution.getLiteral("Rasa").getString();
				
		System.out.println(id + " " + firstName + " " + lastName + " " + gender + " " + age + " " + race);
		
		return new Patient(id, firstName, lastName, gender, age, race);
		
	}

	public List<Patient> findAll() {
		List<Patient> patients = new ArrayList<Patient>();
		
		ResultSet results = executeQuery(queryString + "}");
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution() ;
			
			String id = solution.getLiteral("ID").getString();
			String firstName = solution.getLiteral("Ime").getString();
			String lastName = solution.getLiteral("Prezime").getString();
			String gender = solution.getLiteral("Pol").getString();
			int age = Integer.parseInt(solution.getLiteral("Godine").getString());						
			String race = solution.getLiteral("Rasa").getString();
			
			System.out.println(id + " " + firstName + " " + lastName + " " + gender + " " + age + " " + race);
			
			Patient patient = new Patient(id, firstName, lastName, gender, age, race);
			patients.add(patient);
		}
		return patients;
	}

	public Patient save(Patient entity) {
		entity.setId(UUID.randomUUID());
		String insertString = 
				"BASE <http://www.medical-support-system.eok.org>" + 
				"PREFIX eok: <http://www.medical-support-system.eok.org#>" + 
				"PREFIX xsd: <http://w3.org/2001/XMLSchema#>" + 
				"INSERT DATA {" + 
				"  eok:" + entity.getFirstName() + entity.getLastName() + " a eok:Patient;" + 
				"	 eok:id \"" + entity.getId() + "\"^^xsd:string;" +
				"    eok:firstName \"" + entity.getFirstName() + "\"^^xsd:string;" + 
				"    eok:lastName \"" + entity.getLastName() + "\"^^xsd:string;" + 
				"    eok:gender \"" + entity.getGender() + "\"^^xsd:string;" + 
				"    eok:age \"" + entity.getAge() + "\"^^xsd:int;" + 
				"    eok:race \"" + entity.getRace() + "\"^^xsd:string;" + 
				"}";

		System.out.println("Saving entity: " + entity);
		
		executeUpdate(insertString);		
        
		return entity;
	}

	public void delete(String id) {
		String deleteString = 
				"BASE <http://www.medical-support-system.eok.org>" + 
				"PREFIX eok: <http://www.medical-support-system.eok.org#>" + 
				"PREFIX xsd: <http://w3.org/2001/XMLSchema#>" + 
				"DELETE" + 
				"WHERE {" + 
				"	?a ?b eok:Patient." + 
				"  	?a eok:id ?ID." + 
				"  	?a eok:firstName ?Ime." + 
				"  	?a eok:lastName ?Prezime." + 
				"  	?a eok:gender ?Pol." + 
				"  	?a eok:age ?Godine." + 
				"  	?a eok:race ?Rasa." +
				"	?a eok:id \"" + id + "\"^^xsd:string. " +
				"}";

		System.out.println("Deleting entity with id: " + id);
		
		executeUpdate(deleteString);
		
	}

	public Patient update(Patient entity) {
		
		System.out.println("Updating entity: " + entity);
		
		delete(entity.getId().toString());
		
		return save(entity);
	}

}
