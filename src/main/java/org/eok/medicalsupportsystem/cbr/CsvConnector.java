package org.eok.medicalsupportsystem.cbr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import org.eok.medicalsupportsystem.model.CbReasonerData;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

public class CsvConnector implements Connector {

	private String FILE_NAME;

	public CsvConnector(String fileName) {
		this.FILE_NAME = fileName;
	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile(FILE_NAME)));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(",");

				CBRCase cbrCase = new CBRCase();

				CbReasonerData stDescription = new CbReasonerData(values[0], values[4], values[1],
						Integer.parseInt(values[3]), values[2]);

				cbrCase.setDescription(stDescription);
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
	}

	@Override
	public void close() {
	}

	@Override
	public void deleteCases(Collection<CBRCase> arg0) {
	}

	@Override
	public void initFromXMLfile(URL arg0) throws InitializingException {
	}

}