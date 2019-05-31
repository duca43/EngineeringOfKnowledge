package org.eok.medicalsupportsystem;

import java.awt.EventQueue;

import org.eok.medicalsupportsystem.view.AppFrame;
import org.eok.medicalsupportsystem.view.AppLoginDialog;

public class App {
	public static void main(String[] args)  {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				AppLoginDialog loginDialog = new AppLoginDialog();
				loginDialog.showLoginDialog();
				AppFrame appFrame = new AppFrame();
				AppSingleton.getInstance().setAppFrame(appFrame);
				appFrame.setVisible(true);
			}
		});		
	}
}