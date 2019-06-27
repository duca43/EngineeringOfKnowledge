package org.eok.medicalsupportsystem.view;

import java.awt.Font;
import java.awt.Rectangle;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.controller.BackAction;
import org.eok.medicalsupportsystem.model.Disease;
import org.eok.medicalsupportsystem.model.Symptom;
import org.jdesktop.swingx.JXButton;

import net.miginfocom.swing.MigLayout;
import unbbayes.draw.UShape;
import unbbayes.draw.UShapeLine;
import unbbayes.gui.NetworkWindow;
import unbbayes.prs.bn.ProbabilisticNetwork;

public class ArgumentationGraphPanel extends JPanel {

	private static final long serialVersionUID = -4972257410366488472L;
	private JScrollPane graphScrollPane;

	/**
	 * Create the panel.
	 */
	public ArgumentationGraphPanel() {
		setLayout(new MigLayout("", "[grow]", "[grow][]"));
		
		graphScrollPane = new JScrollPane();
		add(graphScrollPane, "cell 0 0,grow");
		
		JXButton btnBack = new JXButton(new BackAction());
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		add(btnBack, "cell 0 1");
	}

	public void generateGraph(Disease disease) {

		ProbabilisticNetwork network = AppSingleton.getInstance().getBayesReasonerApi().getNetworkByDisease(disease);
		
		if (network == null) {
			return;
		}
		
		NetworkWindow window = new NetworkWindow(network);
		window.changeToPNCompilationPane();
		
		UShape diseaseNodeUShape = window.getGraphPane().getNodeUShape(window.getGraphPane().nodeList.get(0));
		diseaseNodeUShape.setBounds(new Rectangle(80, 80, diseaseNodeUShape.getWidth(), diseaseNodeUShape.getHeight()));
		int row = 1;
		int col = 0;
		int nonRemovedCounter = 0;
		int removedCounter = 0;
		Map<String, Symptom> choosenSymptoms = AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel()
																		.getAdditionalSymptomCheckerPanel().getChoosenSymptomsMap();
		Map<String, Symptom> symptomsPatientDontHave = AppSingleton.getInstance().getAppFrame().getPatientDashboardPanel()
				.getAdditionalSymptomCheckerPanel().getSymptomsPatientDontHaveMap();
		for(int i = 1; i < window.getGraphPane().nodeList.size(); i++) {
			String nodeName = window.getGraphPane().nodeList.get(i).getName();
			if (!choosenSymptoms.containsKey(nodeName) && !symptomsPatientDontHave.containsKey(nodeName)) {
				window.getGraphPane().remove(window.getGraphPane().nodeList.size() - removedCounter + i - 1);
				window.getGraphPane().remove(window.getGraphPane().getNodeUShape(window.getGraphPane().nodeList.get(i)));
				removedCounter++; removedCounter++;
				continue;
			}
			UShape uShape = window.getGraphPane().getNodeUShape(window.getGraphPane().nodeList.get(i));
			int width = uShape.getWidth();
			int height = uShape.getHeight();
			
			uShape.setBounds(new Rectangle((col * (width + 40)) + 80, (row * (height + 40)) + 80, width, height));
			nonRemovedCounter++;
			if (nonRemovedCounter % 3 == 0) {
				row++;
				col = 0;
			} else {
				col++;
			}
			
		}
		window.getGraphPane().setToUseSelectionForLines(true);
		diseaseNodeUShape.setUseSelection(true);
		window.getGraphPane().selectedShapes.add(diseaseNodeUShape);
		for (int i = 0; i < window.getGraphPane().getComponents().length; i++) {
			if (window.getGraphPane().getComponent(i) instanceof UShapeLine) {
				window.getGraphPane().connectLinesForAllSelectedShape(diseaseNodeUShape, ((UShapeLine)window.getGraphPane().getComponent(i)));
			}
		}
		
		for (int i = 0; i < window.getGraphPane().getComponents().length; i++) {
			if (window.getGraphPane().getComponent(i) instanceof UShapeLine) {
				((UShapeLine)window.getGraphPane().getComponent(i)).update();
			}
		}
		
		this.graphScrollPane.setViewportView(window.getGraphPane());
	}
}
