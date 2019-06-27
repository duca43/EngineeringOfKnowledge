package org.eok.medicalsupportsystem.bayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Influence;
import org.eok.medicalsupportsystem.model.Patient;
import org.eok.medicalsupportsystem.model.Symptom;
import org.eok.medicalsupportsystem.util.Util;

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
	private Map<String, ProbabilisticNetwork> networks = new HashMap<>();
	private IInferenceAlgorithm junctionTreeAlgorithm = new JunctionTreeAlgorithm();
 
	public BayesReasonerApi(Patient patient){
		this.junctionTreeAlgorithm = new JunctionTreeAlgorithm();
		List<Disease> diseases = AppSingleton.getInstance().getPrologConsultationApi().getDiseasesFromPrologBase(patient);
		for(int i = 0; i < diseases.size(); i++){
			ProbabilisticNetwork net = new ProbabilisticNetwork("disease-network" + (i+1));
			ProbabilisticNode diseaseNode = this.createDiseaseNode(diseases.get(i).getName(),diseases.get(i).getProb());
			net.addNode(diseaseNode);
			for(Influence influence : AppSingleton.getInstance().getPrologConsultationApi().getInfluencesFromPrologBase()){
				if (influence.getDiseaseName().equals(diseases.get(i).getName())){
					for(Symptom symptom : AppSingleton.getInstance().getPrologConsultationApi().getSymptomsFromPrologBase().values()){
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
			
			junctionTreeAlgorithm.setNetwork(net);
			junctionTreeAlgorithm.run();
			this.networks.put(diseases.get(i).getName(), net);
		}
	}
	
	public ProbabilisticNode createSymptomNode(String symptomName, ProbabilisticNode diseaseNode, 
			  								   float probSymptomHasDisease, float probSymptom,
			  								   ProbabilisticNetwork network) throws InvalidParentException {
		
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
	
	public ProbabilisticNode createDiseaseNode(String diseaseName, float prob) {	
		
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
	
	public List<Disease> getDiseaseProbability(List<Symptom> symptoms, List<Symptom> symptomsPatientDontHave){
		
		List<Disease> diseases = new ArrayList<>();
		for(Entry<String, ProbabilisticNetwork> network : this.networks.entrySet()){
			
			junctionTreeAlgorithm.setNetwork(network.getValue());
			junctionTreeAlgorithm.reset();
			
			boolean flag = false;
			for(Symptom symptom : symptoms){
				ProbabilisticNode node = (ProbabilisticNode)network.getValue().getNode(symptom.getName());
				if(node != null){
					node.addFinding(0);
					flag = true;
				}
			}
			
			if(flag){
				
				for(Symptom symptom : symptomsPatientDontHave){
					ProbabilisticNode node = (ProbabilisticNode)network.getValue().getNode(symptom.getName());
					if(node != null){
						node.addFinding(1);
					}
				}
				try {
					network.getValue().updateEvidences();
					for (Node node: network.getValue().getNodes()){
						if(node.getStateAt(0).equals(STATE_POSITIVE)){
							Disease disease = new Disease(node.getName(),((ProbabilisticNode)node).getMarginalAt(0), Util.filterNames(node.getName()));
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
	
	public ProbabilisticNetwork getNetworkByDisease(Disease disease) {
		
		if (disease == null) {
			return null;
		}
		
		return this.networks.get(disease.getName());
	}
}