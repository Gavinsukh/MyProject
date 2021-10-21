package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;

public class Employee {
	private int ID;
	private int roleID;
	private String name;
	private String username;
	private int salary;
	private String status;
	private String password;

	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int iD, int roleID, String name, String username, int salary, String status, String password) {
		ID = iD;
		this.roleID = roleID;
		this.name = name;
		this.username = username;
		this.salary = salary;
		this.status = status;
		this.password = password;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee getEmployee(String reqUsername) {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM employee WHERE username=?");
			ps.setString(1,reqUsername);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int newID = rs.getInt("ID");
				int newRoleID = rs.getInt("roleID");
				String newName = rs.getString("name");
				String newUsername = rs.getString("username");
				int newSalary = rs.getInt("salary");
				String newStatus = rs.getString("status");
				String newPassword = rs.getString("password");
				
				Employee searchedEmployee = new Employee(newID, newRoleID, newName, newUsername, newSalary, newStatus, newPassword);
				return searchedEmployee;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public String getStatus(int ID) {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM employee WHERE ID=?");
			ps.setInt(1,ID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int newID = rs.getInt("ID");
				int newRoleID = rs.getInt("roleID");
				String newName = rs.getString("name");
				String newUsername = rs.getString("username");
				int newSalary = rs.getInt("salary");
				String newStatus = rs.getString("status");
				String newPassword = rs.getString("password");
				
				Employee searchedEmployee = new Employee(newID, newRoleID, newName, newUsername, newSalary, newStatus, newPassword);
				return newStatus;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Employee getEmployee(int ID) {
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM employee WHERE ID=?");
			ps.setInt(1,ID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int newID = rs.getInt("ID");
				int newRoleID = rs.getInt("roleID");
				String newName = rs.getString("name");
				String newUsername = rs.getString("username");
				int newSalary = rs.getInt("salary");
				String newStatus = rs.getString("status");
				String newPassword = rs.getString("password");
				
				Employee searchedEmployee = new Employee(newID, newRoleID, newName, newUsername, newSalary, newStatus, newPassword);
				return searchedEmployee;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	public List<Employee> getAllEmployee() {
		List<Employee> allEmployee = new ArrayList<Employee>();
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM employee");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int newID = rs.getInt("ID");
				int newRoleID = rs.getInt("roleID");
				String newName = rs.getString("name");
				String newUsername = rs.getString("username");
				int newSalary = rs.getInt("salary");
				String newStatus = rs.getString("status");
				String newPassword = rs.getString("password");
				
				Employee searchedEmployee = new Employee(newID, newRoleID, newName, newUsername, newSalary, newStatus, newPassword);
				allEmployee.add(searchedEmployee);
			}
			
			return allEmployee;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public Employee insertEmployee() {

//		Employee emp = new Employee();

		try {
			
			PreparedStatement ps = Connect.getInstance().prepareStatement("INSERT INTO employee" + 
			"(name,username,salary,status,roleID,password) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, name);
			ps.setString(2, username);
			ps.setInt(3, salary);
			ps.setString(4,status);
			ps.setInt(5, roleID);
			ps.setString(6, password);
			
			if(ps.executeUpdate() == 1)
			{
				ResultSet rs = ps.getGeneratedKeys();
				int lastInsertedID = -1;
				
				if(rs.next())
				{
					lastInsertedID = rs.getInt(1);
				}
				
				return new Employee(lastInsertedID, roleID, name, username, salary, status, password);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
	
	public Employee updateEmployee() {
		Employee emp2 = new Employee();
		
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("UPDATE employee SET name=?,salary=?,password=? WHERE ID =?");
			ps.setString(1, name);
			ps.setInt(2, salary);
			ps.setString(3, password);
			ps.setInt(4, ID);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return emp2;
	}
	
	public Employee fireEmployee() {
		Employee emp3 = new Employee();
		
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("UPDATE employee SET status=? WHERE ID =?");
			ps.setString(1, status);
			ps.setInt(2, ID);
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return emp3;
	}
	
}
