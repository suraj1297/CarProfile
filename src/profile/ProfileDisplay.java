package profile;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ProfileDisplay extends JFrame{
	
	ProfileDisplay(Profile profile, JFrame homeFrame){
		
		if(profile.getBrand() == null || profile.getBrand().length() == 0) {
			 homeFrame.add(ProfileDisplayPanel(), BorderLayout.CENTER);
		}
		else {
			homeFrame.add(ProfileDisplayTable(profile), BorderLayout.CENTER);
			
		}
		
		
	}
	
	public JScrollPane ProfileDisplayTable(Profile profile){
		
		//Table
		
		 // Data to be displayed in the JTable
        String[][] data = {
            { "Brand", profile.getBrand()},
            { "Model", profile.getModel()},
            { "Color", profile.getColor()},
            { "Year", profile.getYear()},
            { "Engine Number", profile.getEngineNo()},
            { "Seats no", profile.getSeatsNo()},
            { "License Plates", profile.getLicencePlate()},
            { "Owner Name", profile.getOwnerName()},
            { "Owner Telephone Numbers", profile.getTelephoneNo()},
            { "Owner Email addresses", profile.getEmail()},
            { "Owner Driver License", profile.getLicenseNo()},
            { "Owner Social Security Number", profile.getSocialSecurityNumber()},
            { "Owner Address", profile.getAddress()},
            { "Service Records", profile.getServiceRecords()},
            { "Warranty Year", profile.getWarrantyYear()},
            { "Photo", profile.getPhoto()}
        };
        
			 
		        
        String[] columnNames = { "Field", "Detail"};
        
	    JTable table = new JTable(data, columnNames);
	    table.setBackground(Color.WHITE);
	    table.setRowHeight(table.getRowHeight() + 20);	       
	    JScrollPane scrollPane = new JScrollPane(table);
	    table.setFillsViewportHeight(true);
			
	
	
		return scrollPane;
	}

	public JPanel ProfileDisplayPanel() {
		JPanel panel = new JPanel();
		panel.setBounds(0,0, 800,560);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(Color.WHITE);
		JLabel formData = new JLabel();
		formData.setBounds(0,0, 800,560);
		formData.setText("No Data to display! Please click on 'Create Car Profile'!");
		panel.add(formData);
		
		return panel;
	}
}
