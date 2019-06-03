package org.eok.medicalsupportsystem.view;

import javax.swing.JPanel;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXLabel;

public class CalculatedDiseasesPanel extends JPanel {

	private static final long serialVersionUID = -2456120838136377788L;

	/**
	 * Create the panel.
	 */
	public CalculatedDiseasesPanel() {
		setBackground(new Color(255, 228, 196));
		setLayout(new MigLayout("", "[grow][grow]", "[grow][][][][grow]"));
		
		JXLabel firstDiseaseName = new JXLabel();
		add(firstDiseaseName, "cell 0 1");
		
		JXLabel firstDiseasePercent = new JXLabel();
		add(firstDiseasePercent, "cell 1 1");
		
		JXLabel secondDiseaseName = new JXLabel();
		add(secondDiseaseName, "cell 0 2");
		
		JXLabel secondDiseasePercent = new JXLabel();
		add(secondDiseasePercent, "cell 1 2");
		
		JXLabel thirdDiseaseName = new JXLabel();
		add(thirdDiseaseName, "cell 0 3");
		
		JXLabel thirdDiseasePercent = new JXLabel();
		add(thirdDiseasePercent, "cell 1 3");

	}
}