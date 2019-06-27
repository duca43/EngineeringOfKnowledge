package org.eok.medicalsupportsystem.util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
	
	private static String PROCEDURES_CSV = "resources/data/procedure.csv";
	private static String THERAPY_CSV = "resources/data/therapy.csv";
	
	public static String filterNames(String nameToFilter) {
		return nameToFilter.substring(0, 1).toUpperCase() + nameToFilter.substring(1).replace('_', ' ');
	}
	
	public static void addProcedureCase(List<String[]> newCases) {
		addToCSVFile(newCases, PROCEDURES_CSV);
	}
	
	public static void addTherapyCase(List<String[]> newCases) {
		addToCSVFile(newCases, THERAPY_CSV);
	}
	
	private static void addToCSVFile(List<String[]> newCases, String filename) { 
		File csvOutputFile = new File(filename);
	    try (PrintWriter printWriter = new PrintWriter(new FileWriter(csvOutputFile, true))) {
	    	newCases.stream().forEach(newCase -> {
	    		printWriter.println(convertToCSV(newCase));	    		
	    	});
	    } catch(Exception e) { }
	}
	
	private static String convertToCSV(String[] data) {
		return Stream.of(data).collect(Collectors.joining(","));
	}
}