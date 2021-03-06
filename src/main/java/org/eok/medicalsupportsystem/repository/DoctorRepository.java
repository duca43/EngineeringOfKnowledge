package org.eok.medicalsupportsystem.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.eok.medicalsupportsystem.model.Doctor;
import org.eok.medicalsupportsystem.model.Examination;
import org.eok.medicalsupportsystem.model.Patient;

public class DoctorRepository extends AbstractRepository<Doctor> {

	@Override
	public Doctor findOne(String id) {
		String queryString = 
				"SELECT ?username ?password ?firstName ?lastName " + 
				"WHERE {" + 
				"	eok:" + id + " ?a eok:Doctor;" + 
				"  		eok:username ?username;" + 
				"  		eok:password ?password;" + 
				"  		eok:firstName ?firstName;" + 
				"  		eok:lastName ?lastName." +
				"}";
		ResultSet results = executeQuery(queryString );
		QuerySolution solution = results.nextSolution() ;
			
		String username = solution.getLiteral("username").getString();
		String password = solution.getLiteral("password").getString();
		String firstName = solution.getLiteral("firstName").getString();
		String lastName = solution.getLiteral("lastName").getString();
		
		return new Doctor(id, username, password, firstName, lastName);
	}
	
	public Doctor findOneByUsername(String username) {
		String queryString = 
				"SELECT ?id	 ?password ?firstName ?lastName " + 
				"WHERE {" + 
				"	?id eok:username " + "\"" + username + "\"^^xsd:string;" + 
				"  		eok:username ?username;" + 
				"  		eok:password ?password;" + 
				"  		eok:firstName ?firstName;" + 
				"  		eok:lastName ?lastName." +
				"}";
		ResultSet results = executeQuery(queryString );
		QuerySolution solution = results.nextSolution();
			
		String id = solution.getResource("id").getLocalName();
		String password = solution.getLiteral("password").getString();
		String firstName = solution.getLiteral("firstName").getString();
		String lastName = solution.getLiteral("lastName").getString();
		
		return new Doctor(id, username, password, firstName, lastName);
	}

	@Override
	public List<Doctor> findAll() {
		List<Doctor> doctors = new ArrayList<Doctor>();
		String queryString = 
				"SELECT ?id ?username ?password ?firstName ?lastName " + 
				"WHERE {" + 
				"	?id a eok:Doctor;" + 
				"  		eok:username ?username;" + 
				"  		eok:password ?password;" + 
				"  		eok:firstName ?firstName;" + 
				"  		eok:lastName ?lastName." +
				"}";
		ResultSet results = executeQuery(queryString);
		
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution() ;
			
			String id = solution.getResource("id").getLocalName();
			String username = solution.getLiteral("username").getString();
			String password = solution.getLiteral("password").getString();
			String firstName = solution.getLiteral("firstName").getString();
			String lastName = solution.getLiteral("lastName").getString();
			
			Doctor doctor = new Doctor(id, username, password, firstName, lastName);
			doctors.add(doctor);
		}
		return doctors;
	}

	@Override
	public Doctor save(Doctor entity) {
		entity.setId(UUID.randomUUID());
		String insertString = 
				"INSERT DATA {" + 
				"	eok:" + entity.getId() + " a eok:Doctor;" + 
				"		eok:username \"" + entity.getUsername() + "\"^^xsd:string;" + 
				"		eok:password \"" + entity.getPassword() + "\"^^xsd:string;" + 
				"   	eok:firstName \"" + entity.getFirstName() + "\"^^xsd:string;" + 
				"		eok:lastName \"" + entity.getLastName() + "\"^^xsd:string." + 
				"}";
		
		executeUpdate(insertString);		
        
		return entity;
	}

	@Override
	public Doctor update(Doctor entity) {
		
		delete(entity.getId().toString());
		
		return save(entity);
	}
	
	public void addPatient(Doctor doctor, Patient patient) {
		String insertString = 
				"INSERT DATA {" + 
				"	eok:" + doctor.getId() + " eok:heals eok:" + patient.getId() + 
				"}";
		
		executeUpdate(insertString);
	}
	
	public List<Patient> getPatients(Doctor entity) {
		List<Patient> patients = new ArrayList<Patient>();
		String queryString = 
				"SELECT ?id ?firstName ?lastName ?gender ?age ?race " + 
				"WHERE {" + 
				"	eok:" + entity.getId()  + " eok:heals ?id." + 
				"	?id eok:firstName ?firstName;" + 
				"  		eok:lastName ?lastName;" + 
				"  		eok:gender ?gender;" + 
				"  		eok:age ?age;" + 
				"  		eok:race ?race." + 
				"}";
		ResultSet results = executeQuery(queryString);
		
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution() ;
			
			String id = solution.getResource("id").getURI().split("#")[1];
			String firstName = solution.getLiteral("firstName").getString();
			String lastName = solution.getLiteral("lastName").getString();
			String gender = solution.getLiteral("gender").getString();
			int age = Integer.parseInt(solution.getLiteral("age").getString());						
			String race = solution.getLiteral("race").getString();
			
			String examinationQueryString =
					"SELECT ?examination " +
							"WHERE {" +
							  "?examination eok:patient eok:" + id + "." +
							"}";
			ResultSet examinationsResults = executeQuery(examinationQueryString);
			ExaminationRepository examinationRepository = new ExaminationRepository();
			Patient patient = new Patient(id, firstName, lastName, gender, age, race, new ArrayList<>());
			List<Examination> examinations = new ArrayList<>();
			while (examinationsResults.hasNext()){
				solution = examinationsResults.nextSolution();
				String ExaminationId = solution.getResource("examination").getURI().split("#")[1];
				Examination examination = examinationRepository.findOne(ExaminationId);
				examination.setPatient(patient);
				examinations.add(examination);
			}
			patient.getExaminations().addAll(examinations);
			patients.add(patient);
		}
		
		return patients;
	}

}
