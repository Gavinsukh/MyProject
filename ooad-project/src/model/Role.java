package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.Connect;

public class Role {
	private int ID;
	private String name;
	
	public Role() {
		// TODO Auto-generated constructor stub
	}
	
	public Role(int iD, String name) {
		ID = iD;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole(int roleID)
	{
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM role WHERE ID=?");
			ps.setInt(1, roleID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				int id = rs.getInt("ID");
				String name = rs.getString("name");
				
				return new Role(id, name);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Role> getAllRole()
	{
		List<Role> roleList = new ArrayList<>();
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM role");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				int id = rs.getInt("ID");
				String name = rs.getString("name");
				
				roleList.add(new Role(id, name));
			}
			
			return roleList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
