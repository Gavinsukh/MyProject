package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connect.Connect;
import controller.EmployeeHandler;
import controller.TransactionHandler;
import model.Employee;
import model.Transaction;

public class ManageEmployeeForm extends JInternalFrame implements MouseListener, ActionListener{
	JPanel mainPanel, northPanel, centerPanel, centerNorthPanel, centerCenterPanel, centerSouthPanel;
	JLabel roleLabel, listLabel, dummyLabel;

	//Button
	JLabel spacingLabel1, spacingLabel2;
	JButton insertButton, updateButton, fireButton;

	//Table
	JTable table;
	DefaultTableModel dtm;
	JScrollPane scrollpane;
	JLabel idLabel, employeeRoleLabel, nameLabel, usernameLabel, statusLabel, salaryLabel;
	
	//TextArea
	JLabel NameLabel,UsernameLabel,SalaryLabel,RoleLabel,IDLabel,PasswordLabel,StatusLabel;
	JTextField FieldName,FieldUsername,FieldSalary,FieldRole,FieldID,FieldPassword,FieldStatus;
	
	Connect con;
	Vector<Object> rowData;

	public ManageEmployeeForm() {
		mainPanel = new JPanel(new BorderLayout());
		northPanel =  new JPanel(new GridLayout(1,2,0,0));
		centerPanel =  new JPanel(new BorderLayout());
		centerCenterPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		centerNorthPanel = new JPanel();
		centerSouthPanel = new JPanel(new GridLayout(7,2));

		//northPanel
		northPanel.setBackground(new Color(59,66,83));
		dummyLabel = new JLabel("");
		roleLabel = new JLabel("Manage Employee Form"); 
		roleLabel.setHorizontalAlignment(JLabel.LEFT);
		roleLabel.setFont(new Font("Roboto", Font.BOLD, 16));
		roleLabel.setForeground(new Color(229,233,240));

		northPanel.setBorder(BorderFactory.createEmptyBorder(15,30,15,30));

		northPanel.add(roleLabel);
		northPanel.add(dummyLabel);

		//Center centerPanel
		centerCenterPanel.setBackground(new Color(230,230,230));
		
		insertButton = new JButton("Insert");
		insertButton.setBackground(new Color(0, 204, 0));
		insertButton.setForeground(Color.WHITE);
		centerCenterPanel.add(insertButton);
		insertButton.addActionListener(this);

		updateButton = new JButton("Update");
		updateButton.setBackground(new Color(239, 191, 0));
		updateButton.setForeground(Color.WHITE);
		centerCenterPanel.add(updateButton);
		updateButton.addActionListener(this);

		fireButton = new JButton("Fire");
		fireButton.setBackground(new Color(204, 0, 0));
		fireButton.setForeground(Color.WHITE);
		centerCenterPanel.add(fireButton);
		fireButton.addActionListener(this);

		centerCenterPanel.setBorder(BorderFactory.createEmptyBorder(0,440,30,440));
		
		//Center northPanel
		centerNorthPanel.setBackground(new Color(230,230,230));
		idLabel = new JLabel("ID");
		employeeRoleLabel = new JLabel("Role");
		nameLabel = new JLabel("Name");
		usernameLabel = new JLabel("Username");
		statusLabel = new JLabel("Status");
		salaryLabel = new JLabel("Salary");
		
		
		Object[] columns = {"ID", "Role", "Name", "Username","Status","Salary"};
		dtm = new DefaultTableModel(columns, 0);
		
		
		table = new JTable(dtm);
		refreshTable();
		scrollpane = new JScrollPane(table);
		centerNorthPanel.add(scrollpane);
		centerNorthPanel.setBorder(BorderFactory.createEmptyBorder(15,0,0,0));
		
		//Center southPanel
		centerSouthPanel.setBackground(new Color(230,230,230));
		
		NameLabel =  new JLabel("Name");
		FieldName = new JTextField();
		
		UsernameLabel =  new JLabel("Username");
		FieldUsername = new JTextField();
		
		SalaryLabel =  new JLabel("Salary");
		FieldSalary = new JTextField();
		
		RoleLabel =  new JLabel("Role");
		FieldRole = new JTextField();
		
		IDLabel = new JLabel("ID");
		FieldID = new JTextField();
		
		PasswordLabel = new JLabel("Password");
		FieldPassword = new JTextField();
//		FieldPassword.setEditable(false);
		
		StatusLabel = new JLabel("Status");
		FieldStatus = new JTextField();
		
		//ID
		centerSouthPanel.add(IDLabel);
		centerSouthPanel.add(FieldID);
//		FieldID.setEditable(false);
		
		//name
		
		centerSouthPanel.add(NameLabel);
		centerSouthPanel.add(FieldName);
		//username
		
		centerSouthPanel.add(UsernameLabel);
		centerSouthPanel.add(FieldUsername);
		
		//Salary
		
		centerSouthPanel.add(SalaryLabel);
		centerSouthPanel.add(FieldSalary);
		//Role
		
		centerSouthPanel.add(RoleLabel);
		centerSouthPanel.add(FieldRole);
		
		//Status
		centerSouthPanel.add(StatusLabel);
		centerSouthPanel.add(FieldStatus);
		FieldStatus.setEditable(false);
		
		//password
		centerSouthPanel.add(PasswordLabel);
		centerSouthPanel.add(FieldPassword);
	
		centerSouthPanel.setBorder(BorderFactory.createEmptyBorder(0,440,0,440));
		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
//				System.out.println("table clicked");
				
				String id = table.getValueAt(table.getSelectedRow(),0).toString();
				FieldID.setText(id);
				FieldName.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				FieldUsername.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				FieldSalary.setText(table.getValueAt(table.getSelectedRow(),5).toString());
				FieldRole.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				FieldStatus.setText(table.getValueAt(table.getSelectedRow(),4).toString());
			}
		});
		
		//Center Panel
		centerPanel.setBackground(new Color(230,230,230));
		centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
		centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
		centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);

		//Main Panel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		add(mainPanel);

		showFrame();
	}

	public void showFrame() {	
		this.setClosable(true);
		this.setTitle("Employee");
		this.setVisible(true);
	}

	private void refreshTable() {
		Object[] columns = {"ID","Role","Name","Username","Status","Salary"};
		
		dtm = new DefaultTableModel(columns,0);
		
		List<Employee> allEmployee = EmployeeHandler.getAllEmployee();
		
		for (Employee employee : allEmployee) {
			rowData = new Vector<>();
			
			rowData.add(employee.getID());
			rowData.add(employee.getRoleID());
			rowData.add(employee.getName());
			rowData.add(employee.getUsername());
			rowData.add(employee.getStatus());
			rowData.add(employee.getSalary());
			
			dtm.addRow(rowData);
		}
		table.setModel(dtm);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == insertButton) {
			
			String Name = FieldName.getText();
			String Username = FieldUsername.getText();
//			int RoleID = Integer.parseInt(FieldRole.getText());
			String RoleID = FieldRole.getText();
//			int Salary = Integer.parseInt(FieldSalary.getText());
			String Salary = FieldSalary.getText();

			
//			Employee employee = EmployeeHandler.addEmployee(RoleID, Name, Username, Salary,"Active", Username);
			
			if(EmployeeHandler.addEmployee(RoleID, Name, Username, Salary,"Active", Username) == null) {
				String errorMessage = EmployeeHandler.errorMessage;
				JOptionPane.showMessageDialog(null, errorMessage, "errorMessage", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Insert Success");
				refreshTable();	
				FieldID.setText("");
				FieldName.setText("");
				FieldUsername.setText("");
				FieldSalary.setText("");
				FieldRole.setText("");
				FieldPassword.setText("");
				FieldStatus.setText("");
			}
			
//			JOptionPane.showMessageDialog(null, "INSERTED");
//			refreshTable();	
//			FieldID.setText("");
//			FieldName.setText("");
//			FieldUsername.setText("");
//			FieldSalary.setText("");
//			FieldRole.setText("");
//			FieldPassword.setText("");
//			FieldStatus.setText("");

		}else if(e.getSource()== updateButton) {
			System.out.println("Update Button clicked");
			String ID = FieldID.getText();
			String Name = FieldName.getText();	
			String Salary = FieldSalary.getText();
			String Password = FieldPassword.getText();
			
			if(EmployeeHandler.updateEmployee(ID, Name, Salary, Password) == null) {
				String errorMessage = EmployeeHandler.errorMessage;
				JOptionPane.showMessageDialog(null, errorMessage, "errorMessage", JOptionPane.ERROR_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "Update Success");
				refreshTable();	
				FieldID.setText("");
				FieldName.setText("");
				FieldUsername.setText("");
				FieldSalary.setText("");
				FieldRole.setText("");
				FieldPassword.setText("");
				FieldStatus.setText("");
			}
			
//			Employee employee2 = EmployeeHandler.updateEmployee(ID, Name, Salary, Password);
//			JOptionPane.showMessageDialog(null, "UPDATED");
//			refreshTable();	
//			FieldID.setText("");
//			FieldName.setText("");
//			FieldUsername.setText("");
//			FieldSalary.setText("");
//			FieldRole.setText("");
//			FieldPassword.setText("");
//			FieldStatus.setText("");
			
			refreshTable();	
		}else if(e.getSource()== fireButton) {
//			System.out.println("Fire Button clicked");
//			int ID = Integer.parseInt(FieldID.getText());
			String ID = FieldID.getText();
			String Status = FieldStatus.getText();
			
			if(EmployeeHandler.fireEmployee(ID, Status) == null) {
				String errorMessage = EmployeeHandler.errorMessage;
				JOptionPane.showMessageDialog(null, errorMessage, "errorMessage", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Employee Fired");
				FieldID.setText("");
				FieldName.setText("");
				FieldUsername.setText("");
				FieldSalary.setText("");
				FieldRole.setText("");
				FieldPassword.setText("");
				FieldStatus.setText("");
				refreshTable();	
			}
//			Employee employee3 = EmployeeHandler.fireEmployee(ID, Status);
//			JOptionPane.showMessageDialog(null, "FIRED");
//			FieldID.setText("");
//			FieldName.setText("");
//			FieldUsername.setText("");
//			FieldSalary.setText("");
//			FieldRole.setText("");
//			FieldPassword.setText("");
//			FieldStatus.setText("");
//			refreshTable();	
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
