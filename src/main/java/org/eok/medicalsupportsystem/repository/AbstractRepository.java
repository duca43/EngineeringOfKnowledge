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

	protected void executeUpdate(String updateString) {
		UpdateRequest updateRequest = UpdateFactory.create(updateString);
		
		UpdateProcessor updateProcessor = UpdateExecutionFactory.createRemote(updateRequest, UPDATE_URL);
		
		updateProcessor.execute();
	}

	protected ResultSet executeQuery(String queryString) {
		Query query = QueryFactory.create(queryString);
		
		QueryExecution qexec = QueryExecutionFactory.sparqlService(QUERY_URL, query);

		ResultSet results = qexec.execSelect();

		return results;
	}
}
