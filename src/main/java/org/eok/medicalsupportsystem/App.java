package org.eok.medicalsupportsystem;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eok.medicalsupportsystem.cbr.CbReasonerApi;
import org.eok.medicalsupportsystem.prolog.PrologConsultationApi;
import org.eok.medicalsupportsystem.view.AppFrame;
import org.eok.medicalsupportsystem.view.AppLoginDialog;

public class App {
	public static void main(String[] args)  {
		try 
		{
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				AppLoginDialog loginDialog = new AppLoginDialog();
				loginDialog.showLoginDialog();
				AppFrame appFrame = new AppFrame();
				AppSingleton.getInstance().setAppFrame(appFrame);
				AppSingleton.getInstance().setPrologConsultationApi(new PrologConsultationApi());
				AppSingleton.getInstance().setTherapyCbReasonerApi(new CbReasonerApi("resources/data/therapy.csv"));
				AppSingleton.getInstance().setProcedureCbReasonerApi(new CbReasonerApi("resources/data/procedure.csv"));
				appFrame.setVisible(true);
			}
		});		
	}
}