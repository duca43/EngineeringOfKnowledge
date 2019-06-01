package org.eok.medicalsupportsystem.repository;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

public abstract class AbstractRepository<T> implements RdfRepository<T> {

	private final String QUERY_URL = "http://localhost:3030/eok/sparql";
	private final String UPDATE_URL = "http://localhost:3030/eok/update";
	
	private final String prefix = "BASE <http://www.medical-support-system.eok.org>" + 
								  "PREFIX eok: <http://www.medical-support-system.eok.org#>" + 
								  "PREFIX xsd: <http://w3.org/2001/XMLSchema#> ";

	protected void executeUpdate(String updateString) {
		UpdateRequest updateRequest = UpdateFactory.create(prefix + updateString);
		
		UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, UPDATE_URL);
		
		updateProcessor.execute();
	}

	protected ResultSet executeQuery(String queryString) {
		Query query = QueryFactory.create(prefix + queryString);
		
		QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_URL, query);

		ResultSet results = qexec.execSelect();

		return results;
	}
	
	@Override
	public void delete(String id) {
		String deleteString = 
				"DELETE" + 
				"WHERE {" + 
				"	eok:" + id + " ?a ?b." + 
				"}";

		System.out.println("Deleting entity with id: " + id);
		
		executeUpdate(deleteString);
	}
}
