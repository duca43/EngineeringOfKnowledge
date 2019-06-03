package org.eok.medicalsupportsystem.bayes;

import java.util.ArrayList;
import java.util.List;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Influence;
import org.eok.medicalsupportsystem.model.Symptom;

import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

public class BayesReasonerApi {

	private static final String STATE_POSITIVE = "da";
	private static final String STATE_NEGATIVE = "ne";
	private static final String STATE_HAS = "ima";
	private static final String STATE_HAS_NOT = "nema";
	private List<ProbabilisticNetwork> networks = new ArrayList<ProbabilisticNetwork>();
 
	public BayesReasonerApi(){
		List<Disease> diseases = AppSingleton.getInstance().getPrologConsultationApi().getDiseases();
		for(int i = 0; i < diseases.size(); i++){
			ProbabilisticNetwork net = new ProbabilisticNetwork("disease-network" + (i+1));
			ProbabilisticNode diseaseNode = this.createDiseaseNode(diseases.get(i).getName(),diseases.get(i).getProb());
			net.addNode(diseaseNode);
			for(Influence influence : AppSingleton.getInstance().getPrologConsultationApi().getInfluences()){
				if (influence.getDiseaseName().equals(diseases.get(i).getName())){
					for(Symptom symptom : AppSingleton.getInstance().getPrologConsultationApi().getSymptoms()){
						if (influence.getSymptomName().equals(symptom.getName())){
							try {
								ProbabilisticNode symptomNode = this.createSymptomNode(symptom.getName(), diseaseNode, influence.getProb(), symptom.getProb(), net);
								net.addNode(symptomNode);
								break;
							} catch (InvalidParentException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
			algorithm.setNetwork(net);
			algorithm.run();
			this.networks.add(net);
		}
	}
	
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
	
	public List<Disease> getDiseaseProbability(List<Symptom> symptoms){
		List<Disease> diseases = new ArrayList<>();
		for(ProbabilisticNetwork network: this.networks){
			Boolean flag = false;
			for(Symptom symptom : symptoms){
				ProbabilisticNode node = (ProbabilisticNode)network.getNode(symptom.getName());
				if(node != null){
					node.addFinding(0);
					flag = true;
				}
			}
			if(flag){
				try {
					network.updateEvidences();
					for (Node node: network.getNodes()){
						if(node.getStateAt(0).equals(STATE_POSITIVE)){
							Disease disease = new Disease(node.getName(),((ProbabilisticNode)node).getMarginalAt(0));
							diseases.add(disease);
							break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return diseases;
	}
}