package org.eok.medicalsupportsystem.util;

import java.util.Comparator;

import org.eok.medicalsupportsystem.model.Examination;

public class ExaminationComparator implements Comparator<Examination> {

	@Override
	public int compare(Examination o1, Examination o2) {
		return o2.compareTo(o1);
	}
}