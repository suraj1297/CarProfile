package profile;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ProfileDisplay extends JFrame{
	
	JLabel heading, brand, model, color, year, engineNo, seatsNo, licencePlate, ownerName, telephoneNo, email,
	licenseNo, socialSecurityNumber, address, serviceRecords, warrantyYear, Photo, imageName;

	JTextField brandField, modelField, colorField, engineNoField, seatsNoField, licencePlateField, ownerNameField,
	telephoneNoField, emailField, licenseNoField, socialSecurityNumberField, addressField, serviceRecordsField,
	warrantyYearField, yearField;
	
	JPanel mainPanel = new JPanel();
	
	Profile profile;
	
	ProfileDisplay(Profile profile, JFrame homeFrame){
		this.profile = profile;
		
		if(profile.getYear() == null || profile.getYear().length() == 0) {
			 homeFrame.add(ProfileDisplayPanel(), BorderLayout.CENTER);
		}
		else {
			homeFrame.add(ProfileDisplayTable(profile, homeFrame), BorderLayout.CENTER);
			
		}
		
		
	}
	
	public JPanel ProfileDisplayTable(Profile profile, JFrame frameHome){
		
		
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(237,245,225));
		
		// adding all form input fields
		addFormElements();
		
			
	    BufferedImage image = null;
        try
        {
          image = ImageIO.read(new File(profile.getPhoto()));
        }
        catch (Exception e)
        {
          e.printStackTrace();
          System.exit(1);
        }
        ImageIcon imageIcon = new ImageIcon(fitimage(image , 200, 200));
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(frameHome.getWidth()-250, 10 ,200, 200);
        mainPanel.add(imageLabel);
	
	
		return mainPanel;
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
	
	private Image fitimage(Image img , int w , int h)
	{
	    BufferedImage resizedimage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	    Graphics2D g2 = resizedimage.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(img, 0, 0,w,h,null);
	    g2.dispose();
	    return resizedimage;
	}

	public void addFormElements() {
		
		String[][] serviceData = {
            { "Brand", profile.getBrand()},
            { "Model", profile.getModel()},
            { "Color", profile.getColor()},
		};
		
		String[] serviceColumns = {"Date", "Service Detail"};
		
		JLabel car = new JLabel();
		car.setBounds(10, 0, 150, 50);
		
		car.setText("<html> <h1> Car Details : </h1></html>");
		car.setForeground(new Color(20,30,97));
		mainPanel.add(car);

		getLabel(brand, "Brand", 30, 50, 50, 30);
		brandField = new JTextField();
		getField(brandField, 90, 50, 200, 30);
		
		getLabel(model, "Model", 310, 50, 50, 30);
		modelField = new JTextField();
		getField(modelField, 370, 50, 200, 30);
		
		getLabel(color, "Color", 590, 50, 50, 30);
		colorField = new JTextField();
		getField(colorField, 650, 50, 200, 30);
		
		getLabel(year, "Year", 30, 110, 50, 30);
		yearField = new JTextField();
		getField(yearField, 90, 110, 200, 30);
		
		getLabel(engineNo, "Engine No", 310, 110, 90, 30);
		engineNoField = new JTextField();
		getField(engineNoField, 390, 110, 200, 30);
		
		getLabel(seatsNo, "Seats Number", 610, 110, 110, 30);
		seatsNoField = new JTextField();
		getField(seatsNoField, 710, 110, 200, 30);
		
		getLabel(warrantyYear, "Warranty Year", 30, 170, 100, 30);
		warrantyYearField = new JTextField();
		getField(warrantyYearField, 140, 170, 200, 30);

		getLabel(licencePlate, "License Plate", 360, 170, 110, 30);
		licencePlateField = new JTextField();
		getField(licencePlateField, 480, 170, 200, 30);
		
		getLabel(serviceRecords, "Service Records", 30, 230, 110, 30);
		JTable serviceTable = new JTable(serviceData, serviceColumns);
		serviceTable.setBackground(Color.WHITE);
		serviceTable.setRowHeight(serviceTable.getRowHeight() + 10);
		serviceTable.setFillsViewportHeight(true);
	    JScrollPane servicePane = new JScrollPane(serviceTable);
	    servicePane.setBounds(150, 230, 370, 101);
	    serviceTable.getColumnModel().getColumn(0).setPreferredWidth(70);
	    serviceTable.getColumnModel().getColumn(1).setPreferredWidth(300);
	    serviceTable.setEnabled(false);
	    mainPanel.add(servicePane);
	    
		JLabel owner = new JLabel();
		owner.setBounds(10, 340, 200, 50);
		
		owner.setText("<html> <h1> Owner Details : </h1></html>");
		owner.setForeground(new Color(20,30,97));
		mainPanel.add(owner);
		
		getLabel(ownerName, "Name ", 30, 400, 50, 30);
		ownerNameField = new JTextField();
		getField(ownerNameField, 90, 400, 200, 30);
		
		getLabel(telephoneNo, "Telephone Number", 310, 400, 130, 30);
		telephoneNoField = new JTextField();
		getField(telephoneNoField, 450, 400, 200, 30);
		
		getLabel(email, "Email Address ", 680, 400, 110, 30);
		emailField = new JTextField();
		getField(emailField, 790, 400, 200, 30);
		
		getLabel(licenseNo, "Driver License", 30, 460, 110, 30);
		licenseNoField = new JTextField();
		getField(licenseNoField, 150, 460, 200, 30);
		
		getLabel(socialSecurityNumber, "Social Security Number", 380, 460, 160, 30);
		socialSecurityNumberField = new JTextField();
		getField(socialSecurityNumberField, 540, 460, 200, 30);
		
		getLabel(address, "Address", 770, 460, 160, 30);
		addressField = new JTextField();
		getField(addressField, 840, 460, 250, 100);
		
	}

	private void getLabel(JLabel labelObject, String labeltext, int x, int y, int width,
			int height) {
		labelObject = new JLabel(labeltext + " : ");
		labelObject.setForeground(new Color(247,110,17));
		labelObject.setBounds(x, y, width, height);
		mainPanel.add(labelObject);

	}

	private void getField(JTextField fieldObject, int x, int y, int width, int height) {
		fieldObject.setBorder(BorderFactory.createEmptyBorder());
		fieldObject.setBounds(x, y, width, height);
		fieldObject.setText(profile.getBrand());
		fieldObject.setEditable(false);
		fieldObject.setBackground(Color.WHITE);
		mainPanel.add(fieldObject);

	}
}
