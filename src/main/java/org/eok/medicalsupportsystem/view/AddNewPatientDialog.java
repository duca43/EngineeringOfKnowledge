package org.eok.medicalsupportsystem.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eok.medicalsupportsystem.AppSingleton;
import org.eok.medicalsupportsystem.controller.AddNewPatientAction;
import org.eok.medicalsupportsystem.model.Patient.GenderEnum;
import org.eok.medicalsupportsystem.model.Patient.RaceEnum;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTextField;

import net.miginfocom.swing.MigLayout;

public class AddNewPatientDialog extends JDialog {

	private static final long serialVersionUID = -8743827561899585962L;
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JXLabel lblFirstName;
	private JXLabel lblLastName;
	private JXLabel lblGender;
	private JXLabel lblAge;
	private JXLabel lblRace;
	private JXTextField textFieldFirstName;
	private JXTextField textFieldLastName;
	private JXTextField textFieldAge;
	private JComboBox comboBoxGender;
	private JComboBox comboBoxRace;

	/**
	 * Create the dialog.
	 */
	public AddNewPatientDialog() {
		setTitle("Add new patient");
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(AppSingleton.getInstance().getAppFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[grow][grow][grow][grow][grow]"));
		{
			lblFirstName = new JXLabel();
			lblFirstName.setText("First name:");
			contentPanel.add(lblFirstName, "cell 0 0,alignx trailing");
		}
		{
			textFieldFirstName = new JXTextField();
			contentPanel.add(textFieldFirstName, "cell 1 0,growx");
		}
		{
			lblLastName = new JXLabel();
			lblLastName.setText("Last name:");
			contentPanel.add(lblLastName, "cell 0 1,alignx trailing");
		}
		{
			textFieldLastName = new JXTextField();
			contentPanel.add(textFieldLastName, "cell 1 1,growx");
		}
		{
			lblGender = new JXLabel();
			lblGender.setText("Gender:");
			contentPanel.add(lblGender, "cell 0 2,alignx trailing");
		}
		{
			comboBoxGender = new JComboBox();
			comboBoxGender.setModel(new DefaultComboBoxModel(GenderEnum.values()));
			contentPanel.add(comboBoxGender, "cell 1 2,growx");
		}
		{
			lblAge = new JXLabel();
			lblAge.setText("Age:");
			contentPanel.add(lblAge, "cell 0 3,alignx trailing");
		}
		{
			textFieldAge = new JXTextField();
			contentPanel.add(textFieldAge, "cell 1 3,growx");
		}
		{
			lblRace = new JXLabel();
			lblRace.setText("Race:");
			contentPanel.add(lblRace, "cell 0 4,alignx trailing");
		}
		{
			comboBoxRace = new JComboBox<Object>();
			comboBoxRace.setModel(new DefaultComboBoxModel(RaceEnum.values()));
			contentPanel.add(comboBoxRace, "cell 1 4,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton(new AddNewPatientAction(this));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						AddNewPatientDialog.this.dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getFirstName() {
		return textFieldFirstName.getText();
	}

	public String getLastName() {
		return textFieldLastName.getText();
	}

	public String getAge() {
		return textFieldAge.getText();
	}

	public GenderEnum getGender() {
		return (GenderEnum)comboBoxGender.getSelectedItem();
	}

	public RaceEnum getRace() {
		return (RaceEnum)comboBoxRace.getSelectedItem();
	}
}
