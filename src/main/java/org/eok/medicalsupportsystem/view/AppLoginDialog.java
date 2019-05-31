package org.eok.medicalsupportsystem.view;

import org.eok.medicalsupportsystem.controller.AppLoginService;
import org.jdesktop.swingx.JXLoginPane;

public class AppLoginDialog extends JXLoginPane {	
	private static final long serialVersionUID = -3380368826727847755L;
	private static final String WELCOME_MSG = "Welcome to Medical support system. Please log in.";
	
	public AppLoginDialog() {
		super(new AppLoginService());
		this.setMessage(WELCOME_MSG);
	}
	
	public void showLoginDialog() {
		JXLoginPane.Status status = JXLoginPane.showLoginDialog(null, this);
		if (JXLoginPane.Status.CANCELLED == status || 
			JXLoginPane.Status.FAILED == status ||
			JXLoginPane.Status.NOT_STARTED == status) {
			Runtime.getRuntime().exit(0);
		}
	}
}