package profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Properties;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.github.lgooddatepicker.tableeditors.DateTableEditor;

@SuppressWarnings("serial")
public class ServiceRecordFrame extends JFrame {

	ServiceRecordData record;
	JFrame frame = new JFrame();

	JFrame formFrame;
	JPanel btnPanel;
	JButton addBtn, saveBtn;
	DefaultTableModel tableModel;
	JTable serviceTable;
	JScrollPane scrollPane;

	UtilDateModel yearField = new UtilDateModel();
	Properties p = new Properties();
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker;

	ServiceRecordFrame(ServiceRecordData record, JFrame formFrame) {
		this.record = record;
		this.formFrame = formFrame;

		frameBase();

		addButton();
		addTable();
		monitorClose();

	}

	public void frameBase() {
		frame.setTitle("Add Service Record Data");
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		formFrame.setEnabled(false);
		frame.setLayout(new BorderLayout());
	}

	public void addButton() {
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnPanel.setPreferredSize(new Dimension(600, 50));
		
		saveBtn = new JButton("Save");
		btnPanel.add(saveBtn);
		
		addBtn = new JButton("Add Row");
		btnPanel.add(addBtn);
		frame.add(btnPanel, BorderLayout.PAGE_START);

		addBtn.addActionListener(e -> {
			addNewRow();
		});
		
		saveBtn.addActionListener(e ->{
			saveRecords();
		});
		
	}

	private void saveRecords() {
		
	    for (int i = 0; i < serviceTable.getRowCount(); i++) {  // Loop through the rows
	    	
	    	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	LocalDate datetime = null;
	    	try {
	    	    datetime = LocalDate.parse(serviceTable.getValueAt(i,0).toString(), pattern); 
	    	} catch (DateTimeParseException e) {
	    	    System.out.println(e);
	    	}
	    	
	    	Date selectedDate = java.util.Date.from(datetime.atStartOfDay()
	                .atZone(ZoneId.systemDefault())
	                .toInstant());
			SimpleDateFormat myFormat = new SimpleDateFormat("dd MMMM yyyy");
		
	    	record.setRecord(myFormat.format(selectedDate), serviceTable.getValueAt(i,1).toString() , serviceTable.getValueAt(i,2).toString());
	     }
	     
	    JOptionPane.showMessageDialog(frame, "Data was saved successfully",
                "INFORMATION",
                JOptionPane.INFORMATION_MESSAGE);
	    
	    formFrame.setEnabled(true);
		frame.setVisible(true);
		frame.dispose();

	 
		
	}

	public void addTable() {
		tableModel = new DefaultTableModel();
		serviceTable = new JTable(tableModel);
		tableModel.addColumn("Date");
		tableModel.addColumn("Total Miles");
		tableModel.addColumn("Engine Check");
		serviceTable.setBackground(Color.WHITE);
		serviceTable.setAutoCreateRowSorter(true);
		
		addNewRow();
		serviceTable.setRowHeight(serviceTable.getRowHeight() + 10);
		serviceTable.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(serviceTable);
		frame.add(scrollPane, BorderLayout.CENTER);
	}

	public void addNewRow() {
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Yes");
		comboBox.addItem("No");

		tableModel.insertRow(tableModel.getRowCount(), new Object[] { "", "", "" });
		TableColumn column1 = serviceTable.getColumnModel().getColumn(0);
		column1.setCellEditor(new DateTableEditor());
		
		
		TableColumn column3 = serviceTable.getColumnModel().getColumn(2);
		column3.setCellEditor(new DefaultCellEditor(comboBox));
	}

	public JFrame getFrame() {
		return frame;
	}

	public void monitorClose() {
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				formFrame.setEnabled(true);
				formFrame.setVisible(true);
				frame.dispose();
			}
		});
	}

}
