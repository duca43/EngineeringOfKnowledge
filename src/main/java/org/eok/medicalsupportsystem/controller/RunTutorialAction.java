package org.eok.medicalsupportsystem.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class RunTutorialAction extends AbstractAction {

	private static final long serialVersionUID = -6940599921843360006L;
	private static final File TUTORIAL_FILE = new File("medial-support-system-tutorial.avi");
	
	public RunTutorialAction() {
		putValue(NAME, "Run tutorial");
		putValue(SHORT_DESCRIPTION, "Click to run application tutorial.");
		putValue(SMALL_ICON, new ImageIcon("resources/images/tutorial-icon.png"));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Desktop desktop = Desktop.getDesktop();
		if (TUTORIAL_FILE.exists()) {
			try {
				desktop.open(TUTORIAL_FILE);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}