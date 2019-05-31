package org.eok.medicalsupportsystem.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Timer;

import org.jdesktop.swingx.JXLabel;

public class DateLabel extends JXLabel
{
	private static final long serialVersionUID = -2649920807839849176L;
	
	public DateLabel() 
	{
		this.refreshTime();
		Timer timer = new Timer(1000, new ActionListener()
		{	
			public void actionPerformed(ActionEvent e) 
			{
				DateLabel.this.refreshTime();
			}
		});
		
		timer.start();
	}
	
	public void refreshTime()
	{
		this.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
	}
}