package org.eok.medicalsupportsystem.cbr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eok.medicalsupportsystem.model.CbReasonerData;

import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CBRCaseBase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

public class CbReasonerApi implements StandardCBRApplication {

	private Connector connector;
	private CBRCaseBase caseBase;
	private NNConfig simConfig;
	private List<String> results;
	private String FILE_NAME;
	
	
	public CbReasonerApi(String fileName) {
		this.FILE_NAME = fileName;
	}

	public void configure() throws ExecutionException {
		results = new ArrayList<>();
		connector = new CsvConnector(FILE_NAME);
		caseBase = new LinealCaseBase(); 
		simConfig = new NNConfig();
		simConfig.setDescriptionSimFunction(new Average()); 

		simConfig.addMapping(new Attribute("disease", CbReasonerData.class), new Equal());
		simConfig.addMapping(new Attribute("gender", CbReasonerData.class), new Equal());
		simConfig.addMapping(new Attribute("age", CbReasonerData.class), new Interval(15));
		simConfig.addMapping(new Attribute("race", CbReasonerData.class), new Equal());
	}

	public CBRCaseBase preCycle() throws ExecutionException {
		caseBase.init(connector);
//		Collection<CBRCase> cases = caseBase.getCases();
//		for (CBRCase c : cases)
//			System.out.println(c.getDescription());
		return caseBase;
	}

	public void cycle(CBRQuery query) throws ExecutionException {
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(caseBase.getCases(), query, simConfig);
		eval = SelectCases.selectTopKRR(eval, 5);
		for (RetrievalResult nse : eval) {
			CbReasonerData cbReasonerResult = (CbReasonerData) nse.get_case().getDescription();
			if(!this.results.contains(cbReasonerResult.getResult())) {
				this.results.add(cbReasonerResult.getResult());				
			}
		}
	}

	public void postCycle() throws ExecutionException {

	}

	/**
	 * @param example for finding similar cases in case-based reasoning
	 * @return List of 5 found most similar cases 
	 */
	public List<String> getResults(CbReasonerData description) {
		try {
			this.configure();
			this.preCycle();
			CBRQuery query = new CBRQuery();

			query.setDescription(description);
			this.cycle(query);
			
			this.postCycle();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.results;
	}

}