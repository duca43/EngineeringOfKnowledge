package org.eok.medicalsupportsystem.bayes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Influence;
import org.eok.medicalsupportsystem.model.Symptom;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;

import unbbayes.io.NetIO;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

public class Bayes 
{
	private static final String STATE_POSITIVE = "da";
	private static final String STATE_NEGATIVE = "ne";
	private static final String STATE_HAS = "ima";
	private static final String STATE_HAS_NOT = "nema";
	
	private static final ProbabilisticNetwork NET1 = new ProbabilisticNetwork("medical-support-system");
	private static final ProbabilisticNetwork NET2 = new ProbabilisticNetwork("medical-support-system");

	public static void main(String[] args) throws Exception 
	{
		JIPEngine engine = new JIPEngine();
		engine.consultFile("baza_bolesti_novija.pl");
		
		JIPQuery diseaseQuery = engine.openSynchronousQuery("bolest(Naziv, Verovatnoca)");
		JIPTerm solution;
		List<Disease> diseases = new ArrayList<Disease>();
		while ((solution = diseaseQuery.nextSolution()) != null)
		{
			String name = solution.getVariables()[0].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[1].getValue().toString());
			
			diseases.add(new Disease(name, prob));
		}
		
		JIPQuery symptomQuery = engine.openSynchronousQuery("simptom(Naziv, Verovatnoca)");
		List<Symptom> symptoms = new ArrayList<Symptom>();
		while ((solution = symptomQuery.nextSolution()) != null)
		{
			String name = solution.getVariables()[0].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[1].getValue().toString());
			
			symptoms.add(new Symptom(name, prob));
		}

		JIPQuery symptomDiseaseQuery = engine.openSynchronousQuery("pojava_simptoma_za_bolest(NazivBolesti, NazivSimptoma, Verovatnoca)");
		List<Influence> influences = new ArrayList<Influence>();
		while ((solution = symptomDiseaseQuery.nextSolution()) != null)
		{
			String diseaseName = solution.getVariables()[0].getValue().toString();
			String symptomName = solution.getVariables()[1].getValue().toString();
			float prob = Float.parseFloat(solution.getVariables()[2].getValue().toString());
			
			influences.add(new Influence(symptomName, diseaseName, prob));
		}

		ProbabilisticNode diseaseNode1 = createDiseaseNode(diseases.get(0).getName(), diseases.get(0).getProb());
		NET1.addNode(diseaseNode1);
		for(Influence influence : influences)
		{
			if (influence.getDiseaseName().equals(diseases.get(0).getName()))
			{
				for(Symptom symptom : symptoms)
				{
					if (influence.getSymptomName().equals(symptom.getName()))
					{
						ProbabilisticNode symptomNode = Bayes.createSymptomNode(symptom.getName(), diseaseNode1, influence.getProb(), symptom.getProb());
						NET1.addNode(symptomNode);
						break;
					}
				}
			}
		}
		
		
		ProbabilisticNode diseaseNode2 = createDiseaseNode(diseases.get(1).getName(), diseases.get(1).getProb());
		NET2.addNode(diseaseNode2);
		for(Influence influence : influences)
		{
			if (influence.getDiseaseName().equals(diseases.get(1).getName()))
			{
				for(Symptom symptom : symptoms)
				{
					if (influence.getSymptomName().equals(symptom.getName()))
					{
						ProbabilisticNode symptomNode = Bayes.createSymptomNode(symptom.getName(), diseaseNode2, influence.getProb(), symptom.getProb());
						NET2.addNode(symptomNode);
						break;
					}
				}
			}
		}
		
		// compiling
		IInferenceAlgorithm algorithm1 = new JunctionTreeAlgorithm();
		algorithm1.setNetwork(NET1);
		algorithm1.run();
		
		// states overview
		List<Node> nodeList = NET1.getNodes();
		for (Node node: nodeList) {
			System.out.println(node.getName());
			for (int i = 0; i < node.getStatesSize(); i++) {
				System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
			}
		}
		
		// compiling
		IInferenceAlgorithm algorithm2 = new JunctionTreeAlgorithm();
		algorithm2.setNetwork(NET2);
		algorithm2.run();
		
		
//		// adding an evidence
//		ProbabilisticNode factNode = (ProbabilisticNode)NET.getNode("witness");
//		int stateIndex = 1; // index of state "green"
//		factNode.addFinding(stateIndex);
//		
//		// propagation
//		try {
//        	NET.updateEvidences();
//        } catch (Exception e) {
//        	System.out.println(e.getMessage());               	
//        }
//        
//        // states overview after propagation
//		for (Node node : nodeList) {
//			System.out.println(node.getName());
//			for (int i = 0; i < node.getStatesSize(); i++) {
//				System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
//			}
//		}
//		// saving to file
		new NetIO().save(new File("medical-support-system1.net"), NET1);
		new NetIO().save(new File("medical-support-system2.net"), NET2);
	}	

	
	public static ProbabilisticNode createSymptomNode(String symptomName, ProbabilisticNode diseaseNode, 
													  float probSymptomHasDisease, float probSymptom) throws InvalidParentException 
	{	
		ProbabilisticNode symptomNode = new ProbabilisticNode();
		symptomNode.setName(symptomName);
		symptomNode.appendState(STATE_HAS);
		symptomNode.appendState(STATE_HAS_NOT);
		PotentialTable probTable = symptomNode.getProbabilityFunction();
		probTable.addVariable(symptomNode);

		NET1.addEdge(new Edge(diseaseNode, symptomNode));
		
		probTable.setValue(0, probSymptomHasDisease);
		probTable.setValue(1, 1.0f - probSymptomHasDisease);		
		
		probTable.setValue(2, probSymptom);
		probTable.setValue(3, 1.0f - probSymptom);
		return symptomNode;
	}
	
	public static ProbabilisticNode createDiseaseNode(String diseaseName, float prob) 
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