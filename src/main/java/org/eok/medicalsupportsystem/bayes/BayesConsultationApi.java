package org.eok.medicalsupportsystem.bayes;

import unbbayes.prs.Edge;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.exception.InvalidParentException;

public class BayesConsultationApi {

	private static final String STATE_POSITIVE = "da";
	private static final String STATE_NEGATIVE = "ne";
	private static final String STATE_HAS = "ima";
	private static final String STATE_HAS_NOT = "nema";
 
	
	public ProbabilisticNode createSymptomNode(String symptomName, ProbabilisticNode diseaseNode, 
			  								   float probSymptomHasDisease, float probSymptom,
			  								   ProbabilisticNetwork network) throws InvalidParentException 
	{	
		ProbabilisticNode symptomNode = new ProbabilisticNode();
		symptomNode.setName(symptomName);
		symptomNode.appendState(STATE_HAS);
		symptomNode.appendState(STATE_HAS_NOT);
		PotentialTable probTable = symptomNode.getProbabilityFunction();
		probTable.addVariable(symptomNode);
		
		network.addEdge(new Edge(diseaseNode, symptomNode));
		
		probTable.setValue(0, probSymptomHasDisease);
		probTable.setValue(1, 1.0f - probSymptomHasDisease);		
		
		probTable.setValue(2, probSymptom);
		probTable.setValue(3, 1.0f - probSymptom);
		return symptomNode;
	}
	
	public ProbabilisticNode createDiseaseNode(String diseaseName, float prob) 
	{	
		ProbabilisticNode node = new ProbabilisticNode();
		node.setName(diseaseName);
		node.appendState(STATE_POSITIVE);
		node.appendState(STATE_NEGATIVE);
		PotentialTable probTable = node.getProbabilityFunction();
		probTable.addVariable(node);
		probTable.setValue(0, prob);
		probTable.setValue(1, 1.0f - prob);
		return node;
	}
}