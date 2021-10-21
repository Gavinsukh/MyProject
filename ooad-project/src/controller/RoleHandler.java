package controller;

import java.util.List;

import model.Role;

public class RoleHandler {

	public RoleHandler() {
		
	}

	public static Role getRole(int roleID)
	{
		Role role = new Role();
		
		return role.getRole(roleID);
	}
	
	public static List<Role> getAllRole()
	{
		Role role = new Role();
		
		return role.getAllRole();
	}
	
	public static boolean validateRole(int checkRole)
	{
		Role role = new Role();
		List<Role> allRoles = getAllRole();
		
		for (Role role1 : allRoles) {
			if(checkRole == role1.getID()) {
				return true;
			}
		}
		
		return false;
	}
}
