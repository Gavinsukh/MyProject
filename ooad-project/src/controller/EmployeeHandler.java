package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import connect.Connect;
import model.Employee;
import model.Role;
import model.Transaction;
import view.CashierView;
import view.HRDView;
import view.LoginView;
import view.ManageEmployeeForm;
import view.ManagerView;
import view.ProductManage;

public class EmployeeHandler {
	
	public static String errorMessage;
	
	private static Employee loginEmployee;
	
	public EmployeeHandler() {
		// TODO Auto-generated constructor stub
	}
	
	static Vector<Object> usernameList;
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	public static int login(String loginUsername, String loginPassword) {
		if(loginUsername.isEmpty() || loginPassword.isEmpty()) {
			errorMessage="Please input the username and the password!";
			return 0;
		}
		
		loginEmployee = new Employee();
		loginEmployee = loginEmployee.getEmployee(loginUsername);
		
		if(loginEmployee==null || !(loginPassword.equals(loginEmployee.getPassword()))) {
			errorMessage = "Invalid username or password!";
			return 0;
		}else {
			Role checkRole = new Role();
			checkRole = RoleHandler.getRole(loginEmployee.getRoleID());
			
			return checkRole.getID();
		}
	}
	
	public static void viewerChooser(int role) {
		switch (role) {
		case 1:
			viewManagerView();
			break;
		case 2:
			viewCashierView();
			break;		
		case 3:
			viewProductView();
			break;
		case 4:
			viewHRDView();
			break;
		
		default:
			break;
		}
	}
	
	public static ManagerView viewManagerView() {
		return new ManagerView();
	}
	
	public static HRDView viewHRDView() {
		return new HRDView();
	}
	
	public static CashierView viewCashierView() {
		return new CashierView();
	}
	
	public static LoginView viewLoginView() {
		return new LoginView();
	}
	
	public static ProductManage viewProductView() {
		return new ProductManage();
	}
	
	public static ManageEmployeeForm viewManageEmployeeForm() {
		return new ManageEmployeeForm();
	}
	
	public static List<Employee> getAllEmployee(){
		Employee employee = new Employee();
		List<Employee> allEmployee = employee.getAllEmployee();
		
		if(allEmployee.isEmpty()) {
			errorMessage = "Data is not found!";
			return null;
		}
		
		return allEmployee;
	}
	
	public static Employee addEmployee(String RoleID,String Name, String Username, String Salary,String Status, String Password) {
		Employee employee = new Employee();
		int parseRoleID;
		int parseSalary;
		boolean found = false;
		
		Employee employeeUsername = new Employee();
		
		employeeUsername = employeeUsername.getEmployee(Username);
		
		if(isNumeric(RoleID)==false) {
			errorMessage = "Role has to be numeric";
			return null;
		}else {
			found = RoleHandler.validateRole(Integer.parseInt(RoleID));
		}
		
			if(Name.trim().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "Name cannot be blank");
				errorMessage = "Name cannot be blank";
				return null;
			}else if(RoleID.trim().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "RoleID cannot be blank");
				errorMessage = "RoleID cannot be blank";
				return null;
			}else if(!found) {
//				JOptionPane.showMessageDialog(null, "RoleID is either 1,2,3 or 4");
				errorMessage = "RoleID has to be either 1,2,3 or 4";
				return null;
			}else if(Username.trim().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "Username cannot be blank");
				errorMessage = "Username cannot be blank";
				return null;
			}else if(!(employeeUsername==null)) {
				errorMessage = "Username Must Be unique";
				return null;
			}else if(Salary.trim().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "Salary cannot be blank");
				errorMessage = "Salary cannot be blank";
				return null;
			}else  if(isNumeric(Salary) == false) {
//				JOptionPane.showMessageDialog(null, "Salary has to be numeric");
				errorMessage = "Salary has to be numeric";
				return null;
			}else if(Integer.valueOf(Salary) <= 0) {
//				JOptionPane.showMessageDialog(null, "Salary has to be bigger than 0");
				errorMessage = "Salary has to be bigger than 0";
				return null;
			}else {
				parseRoleID = Integer.parseInt(RoleID);
				parseSalary = Integer.parseInt(Salary);
				employee.setRoleID(parseRoleID);
				employee.setName(Name);
				employee.setUsername(Username);
				employee.setSalary(parseSalary);
				employee.setStatus(Status);
				employee.setPassword(Password);
				
//				JOptionPane.showMessageDialog(null, "INSERTED");
				return employee.insertEmployee();
			}
	}
	
	
	public static Employee updateEmployee(String ID,String Name, String Salary, String Password) {
		Employee employee2 = new Employee();
		int parseID;
		int parseSalary;
		
		Employee employeeID = new Employee();
		employeeID = employeeID.getEmployee(Integer.parseInt(ID));
		
		if(ID.trim().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "ID cant be empty");
			errorMessage = "ID cant be empty";
			return null;
		}else if(employeeID == null) {
			errorMessage = "ID does not exist in Database";
			return null;
		}else if(Salary.trim().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "Salary cannot be blank");
			errorMessage = "Salary cannot be blank";
			return null;
		}else  if(isNumeric(Salary) == false) {
//			JOptionPane.showMessageDialog(null, "Salary has to be numeric");
			errorMessage = "Salary has to be numeric";
			return null;
		}
		else if(Integer.valueOf(Salary) <= 0) {
//			JOptionPane.showMessageDialog(null, "Salary has to be bigger than 0");
			errorMessage = "Salary has to be bigger than 0";
			return null;
		}else if(Name.trim().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "Name cannot be blank");
			errorMessage = "Name cannot be blank";
			return null;
		}else if(Password.trim().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "Password cannot be blank");
			errorMessage = "Password cannot be blank";
			return null;
		}else if(isNumeric(ID)==false) {
//			JOptionPane.showMessageDialog(null, "ID has to be numeric");
			errorMessage = "ID has to be numeric";
			return null;
		}else {
			parseID = Integer.parseInt(ID);
			parseSalary = Integer.parseInt(Salary);
			employee2.setID(parseID);
			employee2.setName(Name);
			employee2.setSalary(parseSalary);
			employee2.setPassword(Password);
//			JOptionPane.showMessageDialog(null, "UPDATED");
			return employee2.updateEmployee();
		}
	}
	
	public static Employee fireEmployee(String ID,String Status) {
		Employee employee3 = new Employee();
		
		int parseID;
		Employee employeeStatus = new Employee();
		 String newStatus = employeeStatus.getStatus(Integer.parseInt(ID));
		
		if(Status.equalsIgnoreCase("Not Active")) {
//			JOptionPane.showMessageDialog(null, "The Employee is Not Active");
			errorMessage = "The Employee is Not Active";
			return null;
		}else if(ID.trim().isEmpty()) {
//			JOptionPane.showMessageDialog(null, "ID cant be empty");
			errorMessage = "ID cant be empty";
			return null;
		}else if(newStatus.equals("Not Active")) {
			errorMessage = "The Employee is Not Active";
			return null;
		} else if(isNumeric(ID)==false) {
//			JOptionPane.showMessageDialog(null, "ID has to be numeric");
			errorMessage = "ID has to be numeric";
			return null;
		}else  {
			parseID = Integer.parseInt(ID);
			employee3.setID(parseID);
			employee3.setStatus("Not Active");
//			JOptionPane.showMessageDialog(null, "FIRED");
			
			return employee3.fireEmployee();
		}
		
	}
	
	public static Employee getLoginEmployee()
	{
		return loginEmployee;
	}
}
