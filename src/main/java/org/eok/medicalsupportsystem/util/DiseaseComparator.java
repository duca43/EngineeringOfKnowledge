package org.eok.medicalsupportsystem.util;

import java.util.Comparator;

import org.eok.medicalsupportsystem.model.Disease;

public class DiseaseComparator implements Comparator<Disease> {

	@Override
	public int compare(Disease o1, Disease o2) {
		return o2.compareTo(o1);
	}
}
