package org.eok.medicalsupportsystem;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.eok.medicalsupportsystem.prolog.PrologConsultationApi;
import org.eok.medicalsupportsystem.view.AppFrame;
import org.eok.medicalsupportsystem.view.AppLoginDialog;

public class App {
	public static void main(String[] args)  {
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("Label.font", new Font("Comic Sans MS", Font.PLAIN, 13));
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { e.printStackTrace(); }
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				AppLoginDialog loginDialog = new AppLoginDialog();
				loginDialog.showLoginDialog();
				AppFrame appFrame = new AppFrame();
				AppSingleton.getInstance().setAppFrame(appFrame);
				AppSingleton.getInstance().setPrologConsultationApi(new PrologConsultationApi());
				appFrame.setVisible(true);
			}
		});		
	}
}