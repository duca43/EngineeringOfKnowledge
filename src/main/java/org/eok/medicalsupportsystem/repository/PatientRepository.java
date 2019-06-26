package org.eok.medicalsupportsystem.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.eok.medicalsupportsystem.model.Patient;

public class PatientRepository extends AbstractRepository<Patient> {
	
	@Override
	public Patient findOne(String id) {
		String queryString = 
				"SELECT ?firstName ?lastName ?gender ?age ?race " + 
				"WHERE {" + 
				"	eok:" + id + " ?a eok:Patient;" + 
				"  		eok:firstName ?firstName;" + 
				"  		eok:lastName ?lastName;" + 
				"  		eok:gender ?gender;" + 
				"  		eok:age ?age;" + 
				"  		eok:race ?race." + 
				"}";
		ResultSet results = executeQuery(queryString );
		QuerySolution solution = results.nextSolution() ;
			
		String firstName = solution.getLiteral("firstName").getString();
		String lastName = solution.getLiteral("lastName").getString();
		String gender = solution.getLiteral("gender").getString();
		int age = Integer.parseInt(solution.getLiteral("age").getString());
		String race = solution.getLiteral("race").getString();
				
		System.out.println(id + " " + firstName + " " + lastName + " " + gender + " " + age + " " + race);
		
		return new Patient(id, firstName, lastName, gender, age, race, null);
		
	}

	@Override
	public List<Patient> findAll() {
		List<Patient> patients = new ArrayList<Patient>();
		String queryString = 
				"SELECT ?id ?firstName ?lastName ?gender ?age ?race " + 
				"WHERE {" + 
				"	?id a eok:Patient;" + 
				"  		eok:firstName ?firstName;" + 
				"  		eok:lastName ?lastName;" + 
				"  		eok:gender ?gender;" + 
				"  		eok:age ?age;" + 
				"  		eok:race ?race." + 
				"}";
		ResultSet results = executeQuery(queryString);
		
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution() ;
			
			String id = solution.getResource("id").getLocalName();
			String firstName = solution.getLiteral("firstName").getString();
			String lastName = solution.getLiteral("lastName").getString();
			String gender = solution.getLiteral("gender").getString();
			int age = Integer.parseInt(solution.getLiteral("age").getString());						
			String race = solution.getLiteral("race").getString();
			
			System.out.println(id + " " + firstName + " " + lastName + " " + gender + " " + age + " " + race);
			
			Patient patient = new Patient(id, firstName, lastName, gender, age, race, null);
			patients.add(patient);
		}
		return patients;
	}

	@Override
	public Patient save(Patient entity) {
		entity.setId(UUID.randomUUID());
		String insertString = 
				"INSERT DATA {" + 
				"  eok:" + entity.getId() + " a eok:Patient;" + 
				"    eok:firstName \"" + entity.getFirstName() + "\"^^xsd:string;" + 
				"    eok:lastName \"" + entity.getLastName() + "\"^^xsd:string;" + 
				"    eok:gender \"" + entity.getGender() + "\"^^xsd:string;" + 
				"    eok:age \"" + entity.getAge() + "\"^^xsd:int;" + 
				"    eok:race \"" + entity.getRace() + "\"^^xsd:string." + 
				"}";

		System.out.println("Saving entity: " + entity);
		
		executeUpdate(insertString);		
        
		return entity;
	}

	@Override
	public Patient update(Patient entity) {
		
		System.out.println("Updating entity: " + entity);
		
		delete(entity.getId().toString());
		
		return save(entity);
	}

}
