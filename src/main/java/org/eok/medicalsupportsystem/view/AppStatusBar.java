package org.eok.medicalsupportsystem.view;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.model.Doctor;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXStatusBar;

public class AppStatusBar extends JXStatusBar {

	private static final long serialVersionUID = -5237566348621661682L;
	private JXStatusBar.Constraint fillConstraint;
	private DateLabel dateLabel;
	private JXLabel userInfoLabel;
	private JXLabel patientInfoLabel;
	
	public AppStatusBar() {
		this.fillConstraint = new JXStatusBar.Constraint(JXStatusBar.Constraint.ResizeBehavior.FILL);
		initUserInfoLabel();
		initDateLabel();
	}
	
	public void initDateLabel()
	{	
		this.dateLabel = new DateLabel();
		this.add(this.dateLabel, this.fillConstraint);	
	}
	
	public void initUserInfoLabel()
	{	
		this.userInfoLabel = new JXLabel();
		Doctor doctor = AppSingleton.getInstance().getDoctor();
		StringBuilder msgBuilder = new StringBuilder();
		msgBuilder.append("User: ");
		msgBuilder.append(doctor.getFirstName());
		msgBuilder.append(" ");
		msgBuilder.append(doctor.getLastName());
		this.userInfoLabel.setText(msgBuilder.toString());
		this.add(this.userInfoLabel, this.fillConstraint);
	}
	
//	public void addPatientInfoLabel() {
//		this.patientInfoLabel = new JXLabel();
//		Patient patient = AppSingleton.getInstance().getPatient();
//		StringBuilder msgBuilder = new StringBuilder();
//		msgBuilder.append("Current patient: ");
//		msgBuilder.append(patient.getFirstName());
//		msgBuilder.append(" ");
//		msgBuilder.append(patient.getLastName());
//		this.patientInfoLabel.setText(msgBuilder.toString());
//		this.add(this.patientInfoLabel, this.fillConstraint);
//	}
}